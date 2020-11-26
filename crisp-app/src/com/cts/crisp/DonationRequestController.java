package com.cts.crisp;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DonationRequestDAOImpl;
import model.DonationRequest;


@Path("/donationrequest")
public class DonationRequestController {

	DonationRequestDAOImpl donationDAO = new DonationRequestDAOImpl();

	//create a new request - tested
	@POST
	@Path("/createdonationrequest")
	@Produces(MediaType.APPLICATION_JSON)
	public int addDonationRequest(DonationRequest dr) throws ClassNotFoundException, SQLException {
		return donationDAO.addDonationRequest(dr);
	}
	//read all pending requests by pending or approved or rejected - tested
	@GET
	@Path("/status/{statusString}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DonationRequest> readDonationRequest(@PathParam("statusString") String statusString) throws ClassNotFoundException, SQLException{
		return donationDAO.readDonationRequest(statusString);
	}

	//read statuses by person id - tested
	@GET
	@Path("/person/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DonationRequest> readDonationRequestStatusByPerson(@PathParam("personId") Integer personId) throws ClassNotFoundException, SQLException{
		return donationDAO.readDonationRequestStatusByPerson(personId);
	}

	//read status by request id - tested
	@GET
	@Path("/request/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public DonationRequest readDonationRequestById(@PathParam("requestId") Integer requestId) throws ClassNotFoundException, SQLException {
		return donationDAO.readDonationRequestById(requestId);
	}

	//update status and admin remarks of a particular reqId - tested
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public int updateStatus(DonationRequest donationRequest) throws SQLException, ClassNotFoundException {
		return donationDAO.updateStatus(donationRequest.getPersonId(), donationRequest.getStatus(), donationRequest.getAdminRemarks(),donationRequest.getReqId());
	}
	
	//delete donation request - tested
	@DELETE
	@Path("/delete/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteDonationRequest(@PathParam("requestId") Integer requestId) throws SQLException, ClassNotFoundException {
		return donationDAO.deleteDonationRequest(requestId);
	}
}
