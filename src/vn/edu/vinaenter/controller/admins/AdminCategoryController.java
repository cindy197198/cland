package vn.edu.vinaenter.controller.admins;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.daos.CatDAO;
import vn.edu.vinaenter.defines.MessageDefine;
import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Contact;
import vn.edu.vinaenter.utils.AuthUtil;

@Controller
@RequestMapping("admin/cat")
public class AdminCategoryController {
	@Autowired 
	private CatDAO catDAO;
	@GetMapping({"{page}",""})
	public String index(ModelMap modelMap,@PathVariable(value="page",required=false) Integer page,HttpSession session) throws IOException {
		if (!AuthUtil.checkLogin(session)) {
			return "redirect:/cpanel/login";
		}
		if (page==null) page=1;
		int totalRow=catDAO.countItems();
		int sumPage=(int) Math.ceil((float)totalRow/PageDefine.ADMIN_ROW_COUNT);
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("page",page);		
		int offset= (page-1)* PageDefine.ADMIN_ROW_COUNT;
		List<Category> listCat= catDAO.getItemsPagination(offset);
		modelMap.addAttribute("listCat",listCat);
		return "admin.cat.index";
	}
	@GetMapping("add")
	public String add() {
		return "admin.cat.add";
	}
	@PostMapping("add")
	public String add(@RequestParam("cname") String cname,RedirectAttributes ra) {
		if ("".equals(cname)) {
			ra.addFlashAttribute("msg", "Tên danh mục không được rỗng");
			return "redirect:/admin/cat/add";
		}else {	
				Category objCat=new Category(0, cname);
				if (!catDAO.checkItem(objCat)) {
					ra.addFlashAttribute("msg2", "Tên danh mục đã tồn tại"); 	
					return "redirect:/admin/cat";
				}					
				if (catDAO.addItem(objCat)>0) {
					ra.addFlashAttribute("msg", MessageDefine.MSG_ADD_SUCCESS);
					return "redirect:/admin/cat";
				}
				ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
				return "redirect:/admin/cat";
		}
	}
	/*@PostMapping("add")
	public String add(@Valid @ModelAttribute("objCat") Category objCat,BindingResult br,RedirectAttributes ra) {
		if (br.hasErrors()) {
			return "admin.cat.add";
		}else {	
				if (!catDAO.checkItem(objCat)) {
					ra.addFlashAttribute("msg2", "Tên danh mục đã tồn tại"); 	
					return "redirect:/admin/cat";
				}					
				if (catDAO.addItem(objCat)>0) {
					ra.addFlashAttribute("msg", MessageDefine.MSG_ADD_SUCCESS);
					return "redirect:/admin/cat";
				}
				ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
				return "redirect:/admin/cat";
		}
	}*/
	@GetMapping("del/{id_cat}")
	public String del(@PathVariable int id_cat,RedirectAttributes ra) {
		if (catDAO.delItem(id_cat)>0) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_DEL_SUCCESS);
			return "redirect:/admin/cat";
		}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/cat";
	}
	@GetMapping("edit/{id_cat}")
	public String edit(@PathVariable int id_cat,ModelMap modelMap,RedirectAttributes ra) {
		Category objCat= catDAO.getItem(id_cat);
		if (objCat==null) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
			return "redirect:/admin/cat";
		}
		modelMap.addAttribute("objCat", objCat);
		return "admin.cat.edit";
	}
	@PostMapping("edit/{id_cat}")
	public String edit(@RequestParam("cname") String cname,@PathVariable int id_cat,RedirectAttributes ra) {
		if ("".equals(cname)) {
			ra.addFlashAttribute("msg", "Tên danh mục không được rỗng");
			return "redirect:/admin/cat/add";
		}else {
			Category itemCat= catDAO.getItem(id_cat);
			if (itemCat!=null) {
				itemCat.setCname(cname);			
				if (catDAO.editItem(itemCat)>0) {
					ra.addFlashAttribute("msg", MessageDefine.MSG_EDIT_SUCCESS);
					return "redirect:/admin/cat";
				}
			}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/cat";
		}
	}
	/*@PostMapping("edit/{id_cat}")
	public String edit(@Valid @ModelAttribute("objCat") Category objCat,BindingResult br,@PathVariable int id_cat,RedirectAttributes ra) {
		if (br.hasErrors()) {
			return "admin.cat.edit";
		}else {
			Category itemCat= catDAO.getItem(id_cat);
			if (itemCat!=null) {
				itemCat.setCname(objCat.getCname());			
				if (catDAO.editItem(itemCat)>0) {
					ra.addFlashAttribute("msg", MessageDefine.MSG_EDIT_SUCCESS);
					return "redirect:/admin/cat";
				}
			}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/cat";
		}
	}*/
}
