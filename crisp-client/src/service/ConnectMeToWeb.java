package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectMeToWeb {
	
	static HttpURLConnection conn;
	static String url = "http://localhost:8080/crisp-app/rest/";
	
	public static String dmlClient(String object, String operation,String method) throws IOException
	{			
		URL url_ = new URL(url+operation);
		//System.out.println(object);
		conn = (HttpURLConnection) url_.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream out = conn.getOutputStream();
		out.write(object.getBytes());
		out.flush();
		out.close();
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP Error code : "
					+ conn.getResponseCode());
		}
		InputStreamReader in = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(in);
		String output;
		output = br.readLine();
		return output;

	}

	public static BufferedReader callWebService(String httpMethod, String path, String params) throws IOException
	{
		URL url = new URL(path+params);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(httpMethod);
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP Error code : "
					+ conn.getResponseCode());
		}
		InputStreamReader in = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(in);
		return br;
	}
}
