package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.TreatmentHistory;
import modelTemp.ObjectCast;
import modelTemp.TreatmentHistoryTemp;

public class TreatmentHistoryClient {

	static HttpURLConnection conn;
	final static String path = ConnectMeToWeb.url+"treatmenthistory/";
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//create a new record
	//http://localhost:8080/crisp-app/rest/treatmenthistory/createtreatmenthistory
	public Integer createTreatmentHistory(TreatmentHistory th) throws IOException {
		String operation = "treatmenthistory/createtreatmenthistory";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"admissionDate\":\"%s\""
				+ "}", th.getPersonId(),sdf.format(th.getAdmissionDate()));
		String output = ConnectMeToWeb.dmlClient(body,operation,"POST");
		return Integer.parseInt(output);
	}

	//read all records
	//http://localhost:8080/crisp-app/rest/treatmenthistory/
	public List<TreatmentHistory> readTreatmentHistory() throws IOException{
		List<TreatmentHistory> result = new ArrayList<>();
		BufferedReader br = ConnectMeToWeb.callWebService("GET",path,"");
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		TreatmentHistoryTemp[] temp = gson.fromJson(output,TreatmentHistoryTemp[].class); 
		for(TreatmentHistoryTemp t1:temp) {
			result.add(ObjectCast.toTreatmentHistory(t1));
		}
		return result;		
	}

	//read treatment history of a particular person
	//http://localhost:8080/crisp-app/rest/treatmenthistory/{personId}
	public TreatmentHistory readTreatmentHistoryByPersonId(Integer personId) throws IOException{
		TreatmentHistory result = null;
		BufferedReader br = ConnectMeToWeb.callWebService("GET",path,personId.toString());
		String output;
		output = br.readLine();
		Gson gson = new Gson();
		TreatmentHistoryTemp temp = gson.fromJson(output,TreatmentHistoryTemp.class);
		result = ObjectCast.toTreatmentHistory(temp);
		return result;
	}

	//update admission date
	//http://localhost:8080/crisp-app/rest/treatmenthistory/updateadmission
	public Integer updateAdmissionDate(TreatmentHistory th) throws IOException {
		String operation = "treatmenthistory/updateadmission";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"treatmentDetails\":\"%s\","
				+ "\"admissionDate\":\"%s\""
				+ "}", th.getPersonId(),th.getTreatmentDetails(),sdf.format(th.getAdmissionDate()));
		String output = ConnectMeToWeb.dmlClient(body,operation,"PUT");
		return Integer.parseInt(output);
	}

	//update recovered date
	//http://localhost:8080/crisp-app/rest/treatmenthistory/updaterecovered
	public Integer updateRecoveredDate(TreatmentHistory th) throws IOException {
		String operation = "treatmenthistory/updaterecovered";
		String body = "{"
				+ "\"personId\":"+th.getPersonId()+","
				+ "\"treatmentDetails\":\""+th.getTreatmentDetails()+"\","
				+ "\"recoveredDate\":"+(th.getRecoveredDate()==null?null:"\""+sdf.format(th.getRecoveredDate())+"\"")+""
				+ "}";
		String output = ConnectMeToWeb.dmlClient(body,operation,"PUT");
		return Integer.parseInt(output);
	}
	
	//update death date
	//http://localhost:8080/crisp-app/rest/treatmenthistory/updatedeath
	public Integer updateDeathDate(TreatmentHistory th) throws IOException {
		String operation = "treatmenthistory/updatedeath";
		String body = String.format("{"
				+ "\"personId\":%d,"
				+ "\"treatmentDetails\":\"%s\","
				+ "\"deathDate\":\"%s\""
				+ "}", th.getPersonId(),th.getTreatmentDetails(),sdf.format(th.getDeathDate()));
		String output = ConnectMeToWeb.dmlClient(body,operation,"PUT");
		return Integer.parseInt(output);
	}
	
	//delete a record
	// http://localhost:8080/crisp-app/rest/treatmenthistory/delete/{personId}
	public Integer deleteTreatmentHistory(Integer personId) throws IOException {
		String operation = "treatmenthistory/delete/"+personId;
		String body = "";
		String output = ConnectMeToWeb.dmlClient(body,operation,"DELETE");
		return Integer.parseInt(output);
	}
	
	
}
