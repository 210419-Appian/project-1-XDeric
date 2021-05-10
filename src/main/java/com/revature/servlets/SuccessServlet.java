package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuccessServlet extends HttpServlet{
	
	//this is bad practice but possible
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		HttpSession ses = req.getSession(false); //gets a currently active session if one exists
		
		if (ses == null) {
			out.print("<h1>You are not Logged Int!</h1>");
		} else {
			String name = (String) ses.getAttribute("username"); //or req.getParameter("username")
			out.print("<h2>Welcome " + name + ", you are successfully logged in.</h2>");
			out.print("<a href='logout'>Click here to log out.</a>");
		}
		
		
	}
	
}
