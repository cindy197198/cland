package vn.edu.vinaenter.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.daos.CatDAO;
import vn.edu.vinaenter.daos.UserDAO;
import vn.edu.vinaenter.models.User;
import vn.edu.vinaenter.utils.StringUtil;

@Controller
@RequestMapping("cpanel")
public class AuthController {
	@Autowired 
	private UserDAO userDAO;
	@GetMapping("login")
	public String login(){
		return "auth.login";
	}
	@GetMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		if (session.getAttribute("userLogin")!=null) {
			//xoá session
			session.removeAttribute("userLogin");
			return "redirect:/cpanel/login";
		}		
		return "redirect:/admin";
	}
	@PostMapping("login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						RedirectAttributes ra,
						HttpServletRequest request){
		HttpSession session=request.getSession();
		if ("".equals(username)||"".equals(password)) {
			ra.addFlashAttribute("msg", "Vui lòng nhập đầy đủ"); 	
			return "redirect:/cpanel/login";
		}
		password=StringUtil.md5(password);
		User objUser=userDAO.checkLogin(username, password);
		if (objUser!=null) {
			session.setAttribute("userLogin", objUser);
			return "redirect:/admin";
		}else {
			ra.addFlashAttribute("err", "Sai tên đăng nhập hoặc mật khẩu"); 	
			return "redirect:/cpanel/login";
		}
	}
}
