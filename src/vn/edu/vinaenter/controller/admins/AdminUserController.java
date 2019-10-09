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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.daos.UserDAO;
import vn.edu.vinaenter.defines.MessageDefine;
import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Contact;
import vn.edu.vinaenter.models.User;
import vn.edu.vinaenter.utils.AuthUtil;

@Controller
@RequestMapping("admin/user")
public class AdminUserController {
	@Autowired 
	private UserDAO userDAO;
	@GetMapping({"{page}",""})
	public String index(ModelMap modelMap,@PathVariable(value="page",required=false) Integer page,HttpSession session) throws IOException {
		if (!AuthUtil.checkLogin(session)) {
			return "redirect:/cpanel/login";
		}
		if (page==null) page=1;
		int totalRow=userDAO.countItems();
		int sumPage=(int) Math.ceil((float)totalRow/PageDefine.ADMIN_ROW_COUNT);
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("page",page);		
		int offset= (page-1)* PageDefine.ADMIN_ROW_COUNT;
		List<User> listUser= userDAO.getItemsPagination(offset);
		modelMap.addAttribute("listUsers",listUser);
		return "admin.user.index";
	}
	@GetMapping("add")
	public String add() {
		return "admin.user.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("objUser") User objUser,BindingResult br,RedirectAttributes ra) {
		if (br.hasErrors()) {
			return "admin.user.add";
		}else {
				if (!userDAO.checkItem(objUser)) {
					ra.addFlashAttribute("msg", "Username đã tồn tại"); 	
					return "redirect:/admin/user/add";
				}
				if (userDAO.addItem(objUser)>0) {
					ra.addFlashAttribute("msg", MessageDefine.MSG_ADD_SUCCESS);
					return "redirect:/admin/user";
				}
				ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
				return "redirect:/admin/user";
		}
	}
	@GetMapping("del/{id_user}")
	public String del(@PathVariable int id_user,RedirectAttributes ra) {
		if (userDAO.delItem(id_user)>0) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_DEL_SUCCESS);
			return "redirect:/admin/user";
		}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/user";
	}
	@GetMapping("edit/{id_user}")
	public String edit(@PathVariable int id_user,ModelMap modelMap,RedirectAttributes ra) {
		User objUser= userDAO.getItem(id_user);
		if (objUser==null) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
			return "redirect:/admin/user";
		}
		modelMap.addAttribute("objUser", objUser);
		return "admin.user.edit";
	}
	@PostMapping("edit/{id_user}")
	public String edit(@Valid @ModelAttribute("objUser") User objUser,BindingResult br,@PathVariable int id_user,RedirectAttributes ra) {
		if (br.hasErrors()) {
			return "admin.user.edit";
		}else {
			User itemUser= userDAO.getItem(id_user);
			if (itemUser!=null) {
				itemUser.setFullname(objUser.getFullname());;		
				itemUser.setPassword(objUser.getPassword());
				if (userDAO.editItem(itemUser)>0) {
					ra.addFlashAttribute("msg", MessageDefine.MSG_EDIT_SUCCESS);
					return "redirect:/admin/user";
				}
			}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/user";
		}
	}
}
