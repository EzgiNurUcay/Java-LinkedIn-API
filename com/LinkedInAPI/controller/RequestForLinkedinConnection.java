package com.linkedinAPI.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.linkedinAPI.model.LinkedinConnectionStrings;

public class RequestForLinkedinConnection {

	// HTTP POST request
  // First post request get access-token 
	public static String sendPost(String code) throws Exception {

		String url = "https://www.linkedin.com/oauth/v2/accessToken";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Host", "www.linkedin.com");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		String urlParameters = "grant_type=authorization_code&code=" + code + "&redirect_uri="
				+ LinkedinConnectionStrings.getRedirect_url() + "&client_id=" + LinkedinConnectionStrings.getClient_id()
				+ "&client_secret=" + LinkedinConnectionStrings.getClient_secret() + "";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject jsonObj = new JSONObject(response.toString());
		String access_token = jsonObj.getString("access_token");

		return printUserInformation(access_token);

	}

  // If you want to show informations in html you can define your dynamic type object.
  // You should check JSTL..
	private static  String printUserInformation(String access_token) {
		// To general Information.
		// First of all we have to send get request to take user's informations.
		// Return type will be json.

		String url = "https://api.linkedin.com/v2/me?projection=(id,localizedFirstName,"
				+ "localizedLastName,profilePicture(displayImage~:playableStreams))";

		try {
			URL obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			// Add request header.
			con.setRequestProperty("Host", "api.linkedin.com");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Authorization", "Bearer " + access_token);
			con.setRequestProperty("Accept-Charset", "UTF-8");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject jsonObj = new JSONObject(response.toString());
			String id = jsonObj.getString("id");
			System.out.println("Linkedin Id:" + id);

			String locallizedFirstName = jsonObj.getString("localizedFirstName");
			System.out.println(" First Name:" + locallizedFirstName);

			String locallizedLastName = jsonObj.getString("localizedLastName");
			System.out.println(" Last Name:" + locallizedLastName);

			// To profile picture we should parse profile picture json.

			JSONObject profilePicture = jsonObj.getJSONObject("profilePicture").getJSONObject("displayImage~");
			JSONArray elements = profilePicture.getJSONArray("elements");
			JSONObject obj1 = elements.getJSONObject(0);
			JSONArray identifiers = obj1.getJSONArray("identifiers");
			JSONObject identifier = identifiers.getJSONObject(0);
			String identifierUrl = identifier.getString("identifier");

			System.out.println(" Profile picture url:" + identifierUrl);

			// To access email we should send another get request

			System.out.println(" Email: " + getEmail(access_token));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;

	}

	private static String getEmail(String access_token) throws IOException, JSONException {
		String url = "https://api.linkedin.com/v2/clientAwareMemberHandles?q=members&projection=(elements*(primary,type,handle~))";

		URL obj;

		obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Host", "api.linkedin.com");
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Authorization", "Bearer " + access_token);

		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject jsonObj = new JSONObject(response.toString());
		JSONObject handle = jsonObj.getJSONArray("elements").getJSONObject(0);
		String email = handle.getJSONObject("handle~").getString("emailAddress");
		return email;

	}

}
