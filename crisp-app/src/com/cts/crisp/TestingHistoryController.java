package com.cts.crisp;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.TestingHistoryDAOImpl;
import model.TestingHistory;

@Path("/testinghistory")
public class TestingHistoryController {

	TestingHistoryDAOImpl testingDAO = new TestingHistoryDAOImpl();
	
	//create a new record - tested
	@POST
	@Path("/createtestinghistory")
	@Produces(MediaType.APPLICATION_JSON)
	public int createTestingHistory(TestingHistory testingHistory) throws ClassNotFoundException, SQLException {
		return testingDAO.createTestingHistory(testingHistory.getPersonId(), testingHistory.getHospital(), testingHistory.getTestingDate(), testingHistory.getResult());
	}
	
	//read testing history by testingId - tested
	@GET
	@Path("/{testingId}")
	@Produces(MediaType.APPLICATION_JSON)
	public TestingHistory readTestingHistoryByTestingId(@PathParam("testingId") Integer testingId) throws ClassNotFoundException, SQLException, ParseException {
		return testingDAO.readTestingHistoryByTestingId(testingId);
	}

	//read all records - tested
	@GET
	@Path("/person")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TestingHistory> readTestingHistory() throws SQLException, ClassNotFoundException, ParseException {
		return testingDAO.readTestingHistory();
	}
	
	//read testing history of a particular person - tested
	@GET
	@Path("/person/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TestingHistory> readTestingHistoryByPersonId(@PathParam("personId") Integer personId) throws ClassNotFoundException, SQLException, ParseException {
		return testingDAO.readTestingHistoryByPersonId(personId);
	}
	
	//update testing history. testing date and result is changed using this end-point - tested
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public int updateTestingHistory(TestingHistory testingHistory) throws ClassNotFoundException, SQLException {
		return testingDAO.updateTestingHistory(testingHistory.getTestingId(), testingHistory.getPersonId(), testingHistory.getTestingDate(), testingHistory.getResult());		
	}
	
	//delete a particular testing history (by testing id) - tested
	@DELETE
	@Path("/delete/{testingId}")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteTestingHistory(@PathParam("testingId") Integer testingId) throws ClassNotFoundException, SQLException {
		return testingDAO.deleteTestingHistory(testingId);
	}
}
