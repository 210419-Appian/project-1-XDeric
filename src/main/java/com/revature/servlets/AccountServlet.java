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
import com.revature.models.Account;
import com.revature.services.AccountService;

public class AccountServlet extends HttpServlet {

	private AccountService aService = new AccountService();
	private ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Account> alist = aService.getAllAccounts();

		String json = om.writeValueAsString(alist);
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
		Account a = om.readValue(body, Account.class);

		if (aService.addAccount(a)) {
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
		}

	}

}
