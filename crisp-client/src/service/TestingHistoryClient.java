package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.TestingHistory;
import modelTemp.ObjectCast;
import modelTemp.TestingHistoryTemp;

public class TestingHistoryClient {

	static HttpURLConnection conn;
	final static String path = ConnectMeToWeb.url+"testinghistory/";
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//create a new record
	//http://localhost:8080/crisp-app/rest/testinghistory/createtestinghistory
	public Integer createTestingHistory(TestingHistory testingHistory) throws IOException {
		String operation = "testinghistory/createtestinghistory";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"hospital\":\"%s\","
				+ "\"testingDate\":\"%s\","
				+ "\"result\":\"%s\""
				+ "}", testingHistory.getPersonId(),testingHistory.getHospital(),sdf.format(testingHistory.getTestingDate()),testingHistory.getResult());
		String output = ConnectMeToWeb.dmlClient(body,operation,"POST");
		return Integer.parseInt(output);
	}
	
	//read testing history by testingId
	//http://localhost:8080/crisp-app/rest/testinghistory/{testingId}
	public TestingHistory readTestingHistoryByTestingId(Integer testingId) throws IOException {
		TestingHistory result = null;
		String endpoint = path;
		BufferedReader br = ConnectMeToWeb.callWebService("GET",endpoint,testingId.toString());
		String output;
		output = br.readLine();
		Gson gson=new Gson();
		TestingHistoryTemp resultTemp = gson.fromJson(output, TestingHistoryTemp.class);
		result = ObjectCast.toTestingHistory(resultTemp);
		return result;
	}
	
	//read all records
	//http://localhost:8080/crisp-app/rest/testinghistory/person
	public List<TestingHistory> readTestingHistory() throws IOException{
		List<TestingHistory> result = new ArrayList<>();
		BufferedReader br = ConnectMeToWeb.callWebService("GET",path+"person/","");
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		TestingHistoryTemp[] temp = gson.fromJson(output, TestingHistoryTemp[].class);
		for(TestingHistoryTemp t:temp) {
			result.add(ObjectCast.toTestingHistory(t));
		}
		return result;		
	}
	
	//read record of a particular person
	//http://localhost:8080/crisp-app/rest/testinghistory/person/{personId}
	public List<TestingHistory> readTestingHistoryByPersonId(Integer personId) throws IOException{
		List<TestingHistory> result = new ArrayList<>();
		BufferedReader br = ConnectMeToWeb.callWebService("GET",path+"person/",personId.toString());
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		TestingHistoryTemp[] temp = gson.fromJson(output, TestingHistoryTemp[].class);
		for(TestingHistoryTemp t:temp) {
			result.add(ObjectCast.toTestingHistory(t));
		}
		return result;	
	}
	
	//update testing history; date and result is changed
	//http://localhost:8080/crisp-app/rest/testinghistory/update
	public Integer updateTestingHistory(TestingHistory testingHistory) throws IOException {
		String operation = "testinghistory/update";
		String body = String.format("{"
				+ "\"testingId\":%d,"
				+ "\"personId\":%d,"
				+ "\"testingDate\":\"%s\","
				+ "\"result\":\"%s\""
				+ "}", testingHistory.getTestingId(),testingHistory.getPersonId(),sdf.format(testingHistory.getTestingDate()),testingHistory.getResult());
		
		String output = ConnectMeToWeb.dmlClient(body,operation,"PUT");
		return Integer.parseInt(output);
	}
	
	//delete a particular testingId
	//http://localhost:8080/crisp-app/rest/testinghistory/delete/{testingId}
	public Integer deleteTestingHistory(Integer requestId) throws IOException {
		String operation = "testinghistory/delete/"+requestId;
		String body = "";
		String output = ConnectMeToWeb.dmlClient(body,operation,"DELETE");
		return Integer.parseInt(output);
	}
}
