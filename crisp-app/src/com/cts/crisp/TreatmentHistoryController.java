package com.cts.crisp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.TreatmentHistoryDAOImpl;
import model.TreatmentHistory;

@Path("/treatmenthistory")
public class TreatmentHistoryController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	TreatmentHistoryDAOImpl treatmentDAO = new TreatmentHistoryDAOImpl();
	
	//create a record - tested
	@POST
	@Path("/createtreatmenthistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer createTreatmentHistory(TreatmentHistory th) throws SQLException, ClassNotFoundException {
		return treatmentDAO.createTreatmentHistory(th.getPersonId(), th.getAdmissionDate());
	}
	
	//read all records - tested
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TreatmentHistory> readTreatmentHistory() throws ClassNotFoundException, SQLException, ParseException{
		return treatmentDAO.readTreatmentHistory();
	}
	
	//read particular record by personId - tested
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public TreatmentHistory readTreatmentHistoryByPersonId(@PathParam("personId") Integer personId) throws ClassNotFoundException, SQLException, ParseException{
		return treatmentDAO.readTreatmentHistoryByPersonId(personId);
	}
	
	
	//update treatment details and admissionDate of personID - tested
	@PUT
	@Path("/updateadmission")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer updateAdmissionDate(TreatmentHistory th) throws ClassNotFoundException, SQLException {
		//Integer personId,String treatmentDetails,Date admissionDate
		return treatmentDAO.updateAdmissionDate(th.getPersonId(), th.getTreatmentDetails(), th.getAdmissionDate());
	}
	
	//update treatment details and recoveredDate of personID - tested
	@PUT
	@Path("/updaterecovered")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer updateRecoveredDate(TreatmentHistory th) throws SQLException, ClassNotFoundException{
		return treatmentDAO.updateRecoveredDate(th.getPersonId(), th.getTreatmentDetails(), th.getRecoveredDate());
	}
	
	//update treatment details and deathDate of personID - tested
	@PUT
	@Path("/updatedeath")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer updateDeathDate(TreatmentHistory th) throws SQLException, ClassNotFoundException{
		return treatmentDAO.updateDeathDate(th.getPersonId(), th.getTreatmentDetails(), th.getDeathDate());
	}
	
	//delete a record by personId - tested
	@DELETE
	@Path("/delete/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteTreatmentHistory(@PathParam("personId") Integer personId) throws SQLException, ClassNotFoundException{
		return treatmentDAO.deleteTreatmentHistory(personId);
	}
	
}
