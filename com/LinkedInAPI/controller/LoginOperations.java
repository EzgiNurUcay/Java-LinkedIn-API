package com.linkedinAPI.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linkedinAPI.model.LinkedinConnectionStrings;

@WebServlet("/loginOperations")
public class LoginOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// To good development you should define a config file to stora Connection String.
	public static String URL = "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id="
			+ LinkedinConnectionStrings.getClient_id() + "&redirect_uri=" + LinkedinConnectionStrings.getRedirect_url()
			+ "&state=fdfdfdfd&scope=r_liteprofile%20r_emailaddress";

	public LoginOperations() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String linkedinButton = request.getParameter("linkedinButton");
		if (linkedinButton == null) {
			response.sendRedirect("view/anotherLogin.jsp");
		} else {
			response.sendRedirect(URL);

		}
	}
}
