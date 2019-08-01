package com.linkedinAPI.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectServlet")
public class RedirectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RedirectedServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding( "UTF-8" );
		response.setCharacterEncoding("UTF-8");
		String code = (String) request.getParameter("code");
		String state = (String) request.getParameter("state");
	
		try {
			System.out.println(RequestForLinkedinConnection.sendPost(code));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
