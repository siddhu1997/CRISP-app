package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Person;
import modelTemp.ObjectCast;
import modelTemp.PersonTemp;

public class PersonClient {

	static HttpURLConnection conn;
	final static String path = ConnectMeToWeb.url+"person/";
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//read all
	//http://localhost:8080/crisp-app/rest/person/
	public List<Person> readPerson() throws IOException{
		List<Person> result = new ArrayList<Person>(); 	
		BufferedReader br = ConnectMeToWeb.callWebService("GET",path,"");
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		PersonTemp[] personTemp = gson.fromJson(output,PersonTemp[].class);
		for(PersonTemp p:personTemp) {
			result.add(ObjectCast.toPerson(p));
		}
		return result;
	}

	//read by Person Id
	//http://localhost:8080/crisp-app/rest/person/{personId}
	public Person readPersonById(Integer personId) throws IOException {
		Person result = new Person();
		BufferedReader br = ConnectMeToWeb.callWebService("GET",path,personId.toString());
		String output;
		output = br.readLine();
		//System.out.println(output);
		Gson gson=new Gson();
		PersonTemp personTemp = gson.fromJson(output, PersonTemp.class);
		result = ObjectCast.toPerson(personTemp);
		return result;
	}

	//read by person's phone number
	//http://localhost:8080/crisp-app/rest/person/phonenumber/{phoneNumber}
	public Person readPersonByPhoneNumber(String phoneNumber) throws IOException {
		Person result = new Person();
		String url1 = path+"phonenumber/";
		BufferedReader br = ConnectMeToWeb.callWebService("GET",url1,phoneNumber);
		String output;
		output = br.readLine();
		//System.out.println(output);
		Gson gson=new Gson();
		PersonTemp personTemp = gson.fromJson(output, PersonTemp.class);
		result = ObjectCast.toPerson(personTemp);
		return result;
	}

	//create a new person
	//http://localhost:8080/crisp-app/rest/person/createperson
	public Integer createPerson(Person person) throws IOException {
		String operation = "person/createperson";
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

		String output = ConnectMeToWeb.dmlClient(body,operation,"POST");
		return Integer.parseInt(output);
	}

	//update an existing record.
	//http://localhost:8080/crisp-app/rest/person/update
	public Integer  updatePerson(Person person) throws IOException {
		String operation = "person/update";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"phoneNumber\":\"%s\","
				+ "\"location\":\"%s\""
				+ "}", person.getPersonId(),person.getPhoneNumber(),person.getLocation());	
		String output = ConnectMeToWeb.dmlClient(body,operation,"PUT");
		return Integer.parseInt(output);
	}

	//delete a record
	//http://localhost:8080/crisp-app/rest/person/delete/personId
	public Integer deletePerson(Integer personId) throws IOException {
		String operation = "person/delete/"+personId;
		String body = "";	
		String output = ConnectMeToWeb.dmlClient(body,operation,"DELETE");
		return Integer.parseInt(output);
	}
}
