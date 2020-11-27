package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.ObjectCast;
import model.Person;
import model.PersonTemp;

public class PersonClient {

	static HttpURLConnection conn;
	final static String url = "http://localhost:8080/crisp-app/rest/person/";
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//read all
	//http://localhost:8080/crisp-app/rest/person/
	public List<Person> readPerson() throws IOException{
		List<Person> result = new ArrayList<Person>(); 	
		BufferedReader br = callWebService("GET",url,"");
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		PersonTemp[] personTemp = gson.fromJson(output,PersonTemp[].class);
		for(PersonTemp p:personTemp) {
			Person p1 = ObjectCast.toPerson(p);
			System.out.println(p1);
		}

		return result;
	}

	//read by Person Id
	//http://localhost:8080/crisp-app/rest/person/{personId}
	public Person readPersonById(Integer personId) throws IOException {
		Person result = new Person();
		BufferedReader br = callWebService("GET",url,personId.toString());
		String output;
		output = br.readLine();
		//System.out.println(output);
		Gson gson=new Gson();
		PersonTemp personTemp = gson.fromJson(output, PersonTemp.class);
		conn.disconnect();
		result = ObjectCast.toPerson(personTemp);
		return result;
	}

	//read by person's phone number
	//http://localhost:8080/crisp-app/rest/person/phonenumber/{phoneNumber}
	public Person readPersonByPhoneNumber(String phoneNumber) throws IOException {
		Person result = new Person();
		String url1 = url+"phonenumber/";
		BufferedReader br = callWebService("GET",url1,phoneNumber);
		String output;
		output = br.readLine();
		//System.out.println(output);
		Gson gson=new Gson();
		PersonTemp personTemp = gson.fromJson(output, PersonTemp.class);
		conn.disconnect();
		result = ObjectCast.toPerson(personTemp);
		return result;
	}

	//create a new person
	//http://localhost:8080/crisp-app/rest/person/createperson
	public Integer createPerson(Person person) throws IOException {
		String operation = "createperson";
		String body = String.format("{"
				+ "\"name\":\"%s\","
				+ "\"dob\":\"%s\","
				+ "\"phoneNumber\":\"%s\","
				+ "\"bloodGroup\":\"%s\","
				+ "\"location\":\"%s\","
				+ "\"password\":\"%s\""
				+ "}", person.getName(),sdf.format(person.getDob()),
				person.getPhoneNumber(),person.getBloodGroup(),
				person.getLocation(),person.getPassword());

		String output = dmlPerson(body,operation,"POST");

		return Integer.parseInt(output);
	}

	//update an existing record.
	//http://localhost:8080/crisp-app/rest/person/update
	public Integer  updatePerson(Person person) throws IOException {
		String operation = "update";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"phoneNumber\":\"%s\","
				+ "\"location\":\"%s\""
				+ "}", person.getPersonId(),person.getPhoneNumber(),person.getLocation());	
		String output = dmlPerson(body,operation,"PUT");
		return Integer.parseInt(output);
	}

	//delete a record
	//http://localhost:8080/crisp-app/rest/person/delete/personId
	public Integer deletePerson(Integer personId) throws IOException {
		String operation = "delete/"+personId;
		String body = "";	
		String output = dmlPerson(body,operation,"DELETE");
		return Integer.parseInt(output);
	}

	public static String dmlPerson(String person, String operation,String method) throws IOException
	{			
		URL url_ = new URL(url+operation);
		conn = (HttpURLConnection) url_.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream out = conn.getOutputStream();
		out.write(person.getBytes());
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

	public BufferedReader callWebService(String httpMethod, String path, String params) throws IOException
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
