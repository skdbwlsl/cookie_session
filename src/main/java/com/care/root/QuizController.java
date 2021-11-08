package com.care.root;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("quiz")
public class QuizController {

	@RequestMapping("cookie")
	public String myCookie(HttpServletResponse response, Model model,
			@CookieValue(value="myCookie",required=false) Cookie cook,
			HttpServletRequest request){

		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies )
				System.out.println(c.getName()+" : "+c.getValue());
		}

		if(cook != null )
			model.addAttribute("cook",cook.getValue());
		return "cookie_session_quiz/cookie";
	}
	@RequestMapping("popup")
	public String popup() {
		return "cookie_session_quiz/popup";
	}
	@RequestMapping("cookieChk")
	public void cookieChk(HttpServletResponse response) {
		Cookie cook = new Cookie("myCookie", "나의쿠키");
		cook.setMaxAge(10);
		cook.setPath("/");
		response.addCookie(cook);
		//			return "popup";
	}
	@RequestMapping("main")
	public String main(HttpSession session) {
		if(session.getAttribute("loginId") != null) {
			return "cookie_session_quiz/main";
		}
		return "redirect:login";
	}
	@GetMapping("login")
	public String login() {
		return "cookie_session_quiz/login";
	}
	@RequestMapping("logout")
	public void logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		PrintWriter out = null;
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print("<script> alert('1111로그아웃 되었습니다');"
				+ "location.href='login'; </script>");
		//return "login/logout";
	}
	@RequestMapping(value = "chkUser")
	public String chkUser(HttpServletRequest request) {
		String id="1",pwd="1",nickName="홍길동";
		String reqId = request.getParameter("id");
		String reqPwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		if((id.equals(reqId) && pwd.equals(reqPwd)) || 
					session.getAttribute("loginUser") != null) {
			session.setAttribute("loginUser", nickName);
			return "cookie_session_quiz/main";
		}else {
			return "redirect:login";
		}
	}


}
