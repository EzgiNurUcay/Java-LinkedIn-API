package com.linkedinAPI.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.kariyer.model.Applicant;
import com.linkedinAPI.model.LinkedinConnectionStrings;
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
			System.out.println(sendPost(code));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String sendPost(String code) throws Exception {

		String url = "https://www.linkedin.com/oauth/v2/accessToken";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Host", "www.linkedin.com");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		String urlParameters = "grant_type=authorization_code&code=" + code + "&redirect_uri="
				+ LinkedinConnectionStrings.getRedirect_url() + "&client_id=" + LinkedinConnectionStrings.getClient_id() + "&client_secret="
				+ LinkedinConnectionStrings.getClient_secret() + "";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();


		JSONObject jsonObj = new JSONObject(response.toString());
		String access_token = jsonObj.getString("access_token");
		

	}



}
