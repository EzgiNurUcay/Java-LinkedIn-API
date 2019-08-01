package com.LinkedInAPI.model;

public class LinkedinConnectionStrings {
	private final static String client_id = "xxxxxxx"; // Your Client ID at LinkedIn Developer Page
	private final static String redirect_url = "http://localhost:8080/PROJECTNAME/SERVLETPATH";
	private final static String client_secret = "xxxxxxxx"; // Your Client Secret at LinkedIn Developer Page

	public static String getClient_id() {
		return client_id;
	}

	public static String getRedirect_url() {
		return redirect_url;
	}

	public static String getClient_secret() {
		return client_secret;
	}
}
