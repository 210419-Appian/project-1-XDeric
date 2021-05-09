package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet {

	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> ulist = uService.getAllUsers();

		String json = om.writeValueAsString(ulist);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();

		// the request object has a built in method to turn an object that can read the
		// body line by line
		BufferedReader reader = req.getReader();

		String line = reader.readLine(); // this will read the first line
		while (line != null) {
			sb.append(line);

			// advance to next line
			line = reader.readLine();
		}

		String body = new String(sb);
		// the above is all so that the object mapper can read the body

		// jackson will convert JSON that is in the body to a java object we tell it
		User u = om.readValue(body, User.class);

		if (uService.addUser(u)) {
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = req.getReader();

			String line = reader.readLine(); // this will read the first line
			while (line != null) {
				sb.append(line);
				// advance to next line
				line = reader.readLine();
			}
			String body = new String(sb);
			User u = om.readValue(body, User.class);
			
//			int id = Integer.parseInt(mark);
			if(uService.deleteUser(u.getUserId())) {
				resp.setStatus(204);
			}else {
				resp.setStatus(400);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.setStatus(418);
		}
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		User user = om.readValue(body, User.class);
		
		if(uService.patchUser(user)) {
			resp.setStatus(200);
		}else {
			resp.setStatus(400);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("PATCH")) {
			doPatch(req, resp);
		} else {
			super.service(req, resp);
		}
	}
	
	
}
