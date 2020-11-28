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

import dao.PersonDAOImpl;
import model.Person;

@Path("/person")
public class PersonController {
	
	PersonDAOImpl personDAO = new PersonDAOImpl();
	//read all - tested
	@GET
	@Path("/")  
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> readPerson() throws ClassNotFoundException, SQLException, ParseException {
		return personDAO.readPerson();
	}
	
	//read by personId - tested
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person readPersonById(@PathParam("personId") Integer personId) throws ClassNotFoundException, SQLException, ParseException {
		return personDAO.readPersonById(personId);
	}
	
	//read by phoneNumber - tested
	@GET
	@Path("/phonenumber/{phoneNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person readPersonByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) throws ClassNotFoundException, SQLException, ParseException {
		return personDAO.readPersonByPhoneNumber(phoneNumber);
	}
	
	//create a new record - tested
	@POST
	@Path("/createperson")
	@Produces(MediaType.APPLICATION_JSON)
	public int createPerson(Person person) throws ClassNotFoundException, SQLException {
		return personDAO.createPerson(person);
	}
	
	//update a record - tested
	@PUT
	@Path("/update/")
    @Produces(MediaType.APPLICATION_JSON)
	public int updatePerson(Person person) throws ClassNotFoundException, SQLException {
		return personDAO.updatePerson(person.getPersonId(), person.getPhoneNumber(),person.getLocation());
	}
	
	//delete a record by id - tested
	@DELETE
	@Path("/delete/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public int deletePerson(@PathParam("personId") Integer personId) throws SQLException, ClassNotFoundException {
		return personDAO.deletePerson(personId);
	}
	
}
