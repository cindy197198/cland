package vn.edu.vinaenter.controller.admins;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.daos.ContactDAO;
import vn.edu.vinaenter.defines.MessageDefine;
import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Contact;
import vn.edu.vinaenter.utils.AuthUtil;

@Controller
@RequestMapping("admin/contact")
public class AdminContactController {
	@Autowired 
	private ContactDAO contactDAO;
	@GetMapping({"{page}",""})
	public String index(ModelMap modelMap,@PathVariable(value="page",required=false) Integer page,HttpSession session) throws IOException {
		if (!AuthUtil.checkLogin(session)) {
			return "redirect:/cpanel/login";
		}
		if (page==null) page=1;
		int totalRow=contactDAO.countItems();
		int sumPage=(int) Math.ceil((float)totalRow/PageDefine.ADMIN_ROW_COUNT);
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("page",page);		
		int offset= (page-1)* PageDefine.ADMIN_ROW_COUNT;
		List<Contact> listContact= contactDAO.getItemsPagination(offset);
		modelMap.addAttribute("listContact",listContact);
		return "admin.contact.index";
	}
	
	@GetMapping("del/{id_contact}")
	public String del(@PathVariable int id_contact,RedirectAttributes ra) {
		if (contactDAO.delItem(id_contact)>0) {
			ra.addFlashAttribute("msg", MessageDefine.MSG_DEL_SUCCESS);
			return "redirect:/admin/contact";
		}
		ra.addFlashAttribute("msg", MessageDefine.MSG_ERROR);
		return "redirect:/admin/contact";
	}

}
