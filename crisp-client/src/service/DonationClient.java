package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.DonationRequest;

public class DonationClient {

	static HttpURLConnection conn;
	
	//url = "http://localhost:8080/crisp-app/rest/";
	final static String path = ConnectMeToWeb.url+"donationrequest/";

	//status - pending,rejected,approved;
	//http://localhost:8080/crisp-app/rest/donationrequest/status/{statusString}
	public List<DonationRequest> readDonationRequest(String statusString) throws IOException{

		List<DonationRequest> donationRequestList = new ArrayList<>();
		String endpoint = path+"status/"+statusString;
		BufferedReader br = ConnectMeToWeb.callWebService("GET",endpoint,"");
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		DonationRequest[] donationArray = gson.fromJson(output,DonationRequest[].class);
		for(DonationRequest d:donationArray) {
			donationRequestList.add(d);
		}
		return donationRequestList;		
	}

	//read statuses by personId
	//http://localhost:8080/crisp-app/rest/donationrequest/person/{personId}
	public List<DonationRequest> readDonationRequestStatusByPerson(Integer personId) throws IOException{

		List<DonationRequest> donationRequestList = new ArrayList<>();
		String endpoint =path+"person/";
		BufferedReader br = ConnectMeToWeb.callWebService("GET",endpoint,personId.toString());
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		DonationRequest[] donationArray = gson.fromJson(output,DonationRequest[].class);
		for(DonationRequest d:donationArray) {
			donationRequestList.add(d);
		}
		return donationRequestList;		
	}
	
	//read status by requestId
	//http://localhost:8080/crisp-app/rest/donationrequest/request/{requestId}
	public DonationRequest readDonationRequestById(Integer requestId) throws IOException {
		String endpoint = path+"request/";
		BufferedReader br = ConnectMeToWeb.callWebService("GET",endpoint,requestId.toString());
		String output;
		output = br.readLine();
		Gson gson=new Gson();
		DonationRequest result = gson.fromJson(output, DonationRequest.class);
		return result;
	}

	//update status and admin remarks by requestId and personId
	// http://localhost:8080/crisp-app/rest/donationrequest/update
	public Integer updateStatus(DonationRequest donationRequest) throws IOException {
		String operation = "donationrequest/update";
		String body = String.format("{"
				+ "\"reqId\":%d,"
				+ "\"personId\":%d,"
				+ "\"adminRemarks\":\"%s\","
				+ "\"status\":\"%s\""
				+ "}", donationRequest.getReqId(),donationRequest.getPersonId(),
				donationRequest.getAdminRemarks(),donationRequest.getStatus());
		String output = ConnectMeToWeb.dmlClient(body, operation, "PUT");
		return Integer.parseInt(output);
	}
	
	//delete donation request by requestId
	// http://localhost:8080/crisp-app/rest/donationrequest/delete/{requestId}
	public Integer deleteDonationRequest(Integer requestId) throws IOException{
		String operation = "donationrequest/delete/"+requestId;
		String body = "";	
		String output = ConnectMeToWeb.dmlClient(body,operation,"DELETE");
		return Integer.parseInt(output);
	}
	
	//create a new request
	//http://localhost:8080/crisp-app/rest/donationrequest/createdonationrequest
	public Integer addDonationRequest(DonationRequest dr) throws IOException {
		String operation = "donationrequest/createdonationrequest";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"donorRemarks\":\"%s\","
				+ "\"adminRemarks\":\"%s\","
				+ "\"status\":\"%s\""
				+ "}", dr.getPersonId(),dr.getDonorRemarks(),dr.getAdminRemarks(),dr.getStatus());
		String output = ConnectMeToWeb.dmlClient(body,operation,"POST");
		return Integer.parseInt(output);
	}
	
}
