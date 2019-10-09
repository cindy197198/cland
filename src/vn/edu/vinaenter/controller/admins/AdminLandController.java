package vn.edu.vinaenter.controller.admins;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.daos.CatDAO;
import vn.edu.vinaenter.daos.LanDAO;
import vn.edu.vinaenter.defines.FileDefine;
import vn.edu.vinaenter.defines.MessageDefine;
import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Land;
import vn.edu.vinaenter.utils.AuthUtil;

@Controller
@RequestMapping("admin/land")
public class AdminLandController {
	@Autowired
	private ServletContext servletContext;
	@Autowired 
	private LanDAO landDAO;
	@Autowired
	private CatDAO catDAO;
	@GetMapping({"{page}",""})
	public String index(ModelMap modelMap,@PathVariable(value="page",required=false) Integer page,HttpSession session) throws IOException {
		if (!AuthUtil.checkLogin(session)) {
			return "redirect:/cpanel/login";
		}
		if (page==null) page=1;
		int totalRow=landDAO.countItems();
		int sumPage=(int) Math.ceil((float)totalRow/PageDefine.ADMIN_ROW_COUNT);
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("page",page);		
		int offset= (page-1)* PageDefine.ADMIN_ROW_COUNT;
		List<Land> listLand= landDAO.getItemsPagination(offset);
		modelMap.addAttribute("listLand",listLand);
		return "admin.land.index";
	}
	
	@GetMapping("add")
	public String add(ModelMap modelMap) {
		List<Category> listCat=catDAO.getItems();
		modelMap.addAttribute("listCat", listCat);
		return "admin.land.add";
	}
	@PostMapping("add")
	public String add(ModelMap modelMap,@Valid @ModelAttribute("objLand") Land objLand,BindingResult br,
			@RequestParam("pictureReview") MultipartFile cmf,RedirectAttributes ra) throws IllegalStateException, IOException {
		//b1. kiểm tra dữ liệu đầu vào- validation
		if (br.hasErrors()) {
			List<Category> listCat=catDAO.getItems();
			modelMap.addAttribute("listCat", listCat);
			return "admin.land.add";
		}
		//b2. xử lí thêm file và kiểm tra định dạng file jpg,png,jpeg...
			String fileName = cmf.getOriginalFilename();
			//kiểm tra định dạng
			String tmp=FilenameUtils.getExtension(fileName);			
			if(!"".equals(fileName)) 
			{	
				if (!(tmp.equalsIgnoreCase("jpg")||tmp.equalsIgnoreCase("png")||tmp.equalsIgnoreCase("jpeg"))) {
					return "redirect:/admin/land/add?msg=fileError";
				}
				fileName = FileDefine.rename(fileName);
				String dirPath = servletContext.getRealPath("") + FileDefine.DIR_UPLOAD;
				File createDir = new File(dirPath);
				if(!createDir.exists()) {
					createDir.mkdirs();
				}
				cmf.transferTo(new File(dirPath + File.separator + fileName));					
			}
				objLand.setPicture(fileName);			
		//b3. Thêm dữ liệu vào DB
			if (landDAO.addItem(objLand)>0) {
				ra.addFlashAttribute("msg", MessageDefine.MSG_ADD_SUCCESS);
				return "redirect:/admin/land";
			}
			ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
			return "admin.land.add";
		}	
	@GetMapping("del/{id_land}")
	public String del(@PathVariable int id_land,RedirectAttributes ra) {
		if (landDAO.delItem(id_land)>0) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_DEL_SUCCESS);
			return "redirect:/admin/land";
		}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/land";
	}
	@GetMapping("edit/{id_land}")
	public String edit(@PathVariable int id_land,ModelMap modelMap,RedirectAttributes ra) {
		Land objLand= landDAO.getItem(id_land);
		if (objLand==null) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
			return "redirect:/admin/land";
		}
		List<Category> listCat=catDAO.getItems();
		modelMap.addAttribute("listCat", listCat);
		modelMap.addAttribute("objLand", objLand);
		return "admin.land.edit";
	}
	@PostMapping("edit/{id_land}")
	public String edit(ModelMap modelMap,@Valid @ModelAttribute("objLand") Land objLand,BindingResult br,@PathVariable int id_land,RedirectAttributes ra,						
						@RequestParam("pictureReview") MultipartFile cmf) throws IllegalStateException, IOException {
		if (br.hasErrors()) {
			List<Category> listCat=catDAO.getItems();
			modelMap.addAttribute("listCat", listCat);
			return "admin.land.edit";
		}
		Land itemLand= landDAO.getItem(id_land);
		//b2. xử lí thêm file và kiểm tra định dạng file jpg,png,jpeg...
			String fileName = cmf.getOriginalFilename();
				//kiểm tra định dạng
				String tmp=FilenameUtils.getExtension(fileName);
				if(!"".equals(fileName)) 
				{	
						if (!(tmp.equalsIgnoreCase("jpg")||tmp.equalsIgnoreCase("png")||tmp.equalsIgnoreCase("jpeg"))) {
							ra.addFlashAttribute("msg", MessageDefine.MSG_PICTURE_ERROR);
							return "redirect:/admin/land/edit/"+id_land;
						}
						fileName = FileDefine.rename(fileName);
						String dirPath = servletContext.getRealPath("") + FileDefine.DIR_UPLOAD;
						File createDir = new File(dirPath);
						if(!createDir.exists()) {
							createDir.mkdirs();
						}
						cmf.transferTo(new File(dirPath + File.separator + fileName));	
						objLand.setPicture(fileName);				
				}else objLand.setPicture(itemLand.getPicture());
		//b3. Thêm dữ liệu vào DB
			objLand.setLid(id_land);
			if (landDAO.editItem(objLand)>0) {
				ra.addFlashAttribute("msg", MessageDefine.MSG_EDIT_SUCCESS);
				return "redirect:/admin/land";
			}
			ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "admin.land.index";
	}
}
