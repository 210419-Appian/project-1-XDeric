package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Role;
import com.revature.models.User;


public class SignUpServlet extends HttpServlet{
	
	private String signupURL = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		signupURL = config.getInitParameter("signupURL");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("signup.html");
		User usr = new User();
		
		usr.setUsername(req.getParameter("username"));
		usr.setPassword(req.getParameter("password"));
		usr.setFirstName(req.getParameter("first_name"));
		usr.setLastName(req.getParameter("last_name"));
		usr.setEmail(req.getParameter("email"));
		
		Role role = new Role(req.getParameter("role_name"));
		usr.setRole(role);
		
		RequestDispatcher rd = null;
		PrintWriter out = resp.getWriter();
		
		if(Objects.nonNull(usr)) {//if usr is made with no nulls then ... //TODO change to dao to insert into server table and check if it worked
			//create a session so we remember our user/client in the future
			HttpSession ses = req.getSession(); //(creates a cookie) and put in the response
			ses.setAttribute("username", usr.getUsername());
			
			rd = req.getRequestDispatcher("login"); //where to redirect when user creation is successful
			rd.forward(req, resp);
		} else {
			rd = req.getRequestDispatcher("signUp.html"); //send them back to the webpage to signUp again
			rd.include(req, resp);
			out.print("<span style='color:red; text-align:center'>Username already taken</span>");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
