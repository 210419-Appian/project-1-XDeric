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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.models.Account;
import com.revature.services.AccountService;

public class AccountServlet extends HttpServlet {

	private AccountService aService = new AccountService();
	private ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

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

	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// accountPatch(req, resp);
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line = reader.readLine(); // this will read the first line
		while (line != null) {
			sb.append(line);
			// advance to next line
			line = reader.readLine();
		}
		String body = new String(sb);
		Account act = om.readValue(body, Account.class);

		if (aService.deposit(act, act.getBalance())) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line = reader.readLine(); // this will read the first line
		while (line != null) {
			sb.append(line);
			// advance to next line
			line = reader.readLine();
		}
		String body = new String(sb);
		Account act = om.readValue(body, Account.class);
		
		if (aService.withdraw(act, act.getBalance())) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}
	
	protected void doLink(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line = reader.readLine(); // this will read the first line
		while (line != null) {
			sb.append(line);
			// advance to next line
			line = reader.readLine();
		}
		String body = new String(sb);
		Account[] acts = om.readValue(body, Account[].class);
		
		if (aService.transfer(acts[0], acts[1], acts[0].getBalance())) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

	protected void doView(int id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Account actt = aService.findById(id);

		String json = om.writeValueAsString(actt);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("PATCH")) {
			doPatch(req, resp);
		} else {
			super.service(req, resp);
		}
		if (req.getMethod().equals("LINK")) {
			doLink(req, resp);
		} else {
			super.service(req, resp);
		}
		if (req.getMethod().equals("VIEW")) {

			String path = req.getServletPath();

			StringBuilder sb = new StringBuilder();
			BufferedReader reader = req.getReader();

			String line = reader.readLine(); // this will read the first line
			while (line != null) {
				sb.append(line);
				// advance to next line
				line = reader.readLine();
			}
			String body = new String(sb);
			Account act = om.readValue(body, Account.class);

			doView(act.getAccountId(), req, resp);
		} else {
			super.service(req, resp);
		}

	}
	
	
	
	
	
	
	
	

	private void accountPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line = reader.readLine(); // this will read the first line
		while (line != null) {
			sb.append(line);
			// advance to next line
			line = reader.readLine();
		}
		String body = new String(sb);
		Account act = om.readValue(body, Account.class);

		switch (path) {
		case "account/deposit":
			if (aService.deposit(act, act.getBalance())) {
				resp.setStatus(200);
			} else {
				resp.setStatus(400);
			}
			break;
		case "account/withdraw":
			if (aService.withdraw(act, act.getBalance())) {
				resp.setStatus(200);
			} else {
				resp.setStatus(400);
			}
			break;
		case "account/transfer":

			break;
		default:
			resp.setStatus(400);
			break;
		}
	}

}
