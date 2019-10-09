package vn.edu.vinaenter.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {
	
	public static boolean checkLogin(HttpSession session ) throws IOException{
		if (session.getAttribute("userLogin") == null) {
			return false;
		}
		return true;
	}
}
