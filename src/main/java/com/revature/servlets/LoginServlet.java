package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class LoginServlet extends HttpServlet {
	
	private static UserDAO userDao = new UserDAOImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User usr = new User();

		usr.setUsername(req.getParameter("username"));
		usr.setPassword(req.getParameter("password"));

		RequestDispatcher rd = null;
		PrintWriter out = resp.getWriter();
		
		User serverUser = userDao.getUser(usr.getUsername());
		
		if (usr.getUsername().equals(serverUser.getUsername()) && usr.getPassword().equals(serverUser.getPassword())) { // TODO check server to validate
			// create a session so we remember our user/client in the future
			HttpSession ses = req.getSession(); // (creates a cookie) and put in the response
			ses.setAttribute("username", usr.getUsername());

			rd = req.getRequestDispatcher("success"); // where to redirect when login is successful
			rd.forward(req, resp);
		} else {
			rd = req.getRequestDispatcher("index.html"); // send them back to the webpage to login again
			rd.include(req, resp);
			out.print("<span style='color:red; text-align:center'>Invalid Username/Password</span>");
		}

	}

}
