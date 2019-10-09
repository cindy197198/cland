package vn.edu.vinaenter.controller.admins;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.vinaenter.utils.AuthUtil;

@Controller
@RequestMapping("admin")
public class AdminIndexController {
		
	@GetMapping
	public String contact(ModelMap modelMap,HttpSession session) throws IOException {
		if (!AuthUtil.checkLogin(session)) {
			return "redirect:/cpanel/login";
		}
		return "admin.index.index";
	}
}
