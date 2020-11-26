package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import model.TestingHistory;

public class TestingHistoryDAOImpl {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private Connection getConn() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
		return con;
	}

	//tested OK
	public int createTestingHistory(Integer personId, String hospital, Date testingDate, String result) throws ClassNotFoundException, SQLException {
		Connection con= getConn();

		//inserting formal parameters to table testing_history
		PreparedStatement ps = con.prepareStatement("INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(?,?,?,?)");
		ps.setInt(1, personId);
		ps.setString(2, hospital);
		ps.setString(3, sdf.format(testingDate));
		ps.setString(4, result);	

		int i=ps.executeUpdate();

		//returning number rows updated
		return i;

	}

	//tested OK
	public List<TestingHistory> readTestingHistoryByPersonId(Integer personId) throws ClassNotFoundException, SQLException, ParseException {

		Connection con= getConn();

		//reading testing_history of given person_id
		PreparedStatement ps = con.prepareStatement("SELECT * FROM testing_history WHERE person_id=?;");
		ps.setInt(1,personId);
		ResultSet res=ps.executeQuery();

		List<TestingHistory>list_test_history=new ArrayList<TestingHistory>();

		while(res.next()) {
			Integer testingId=res.getInt(1);
			Integer personId1 = res.getInt(2);
			String hospital=res.getString(3);
			String testingDate = res.getString(4);
			String result=res.getString(5);
			TestingHistory test=new TestingHistory(testingId,personId1,hospital,sdf.parse(testingDate),result);
			list_test_history.add(test);
		}
		return list_test_history;
	}

	//tested OK
	public TestingHistory readTestingHistoryByTestingId(Integer testingId) throws ClassNotFoundException, SQLException, ParseException {

		Connection con= getConn();

		//reading testing_history of given person_id
		PreparedStatement ps = con.prepareStatement("SELECT * FROM testing_history WHERE testing_id=?;");
		ps.setInt(1,testingId);
		ResultSet res=ps.executeQuery();

		TestingHistory test = null;

		while(res.next()) {
			Integer testingId1=res.getInt(1);
			Integer personId1 = res.getInt(2);
			String hospital=res.getString(3);
			String testingDate = res.getString(4);
			String result=res.getString(5);
			test=new TestingHistory(testingId1,personId1,hospital,sdf.parse(testingDate),result);
		}
		return test;
	}

	//tested OK
	public List<TestingHistory> readTestingHistory() throws SQLException, ClassNotFoundException, ParseException {

		Connection con= getConn();

		//bulk read
		PreparedStatement ps = con.prepareStatement("SELECT * FROM testing_history");
		ResultSet res=ps.executeQuery();

		List<TestingHistory>list_test_history=new ArrayList<TestingHistory>();

		while(res.next()) {
			Integer testingId=res.getInt(1);
			Integer personId1 = res.getInt(2);
			String hospital=res.getString(3);
			String testingDate = res.getString(4);
			String result=res.getString(5);
			TestingHistory test=new TestingHistory(testingId,personId1,hospital,sdf.parse(testingDate),result);
			list_test_history.add(test);
		}
		return list_test_history;
	}

	//tested OK
	public int updateTestingHistory(Integer testingId,Integer personId, Date testingDate, String result) throws ClassNotFoundException, SQLException {

		Connection con = getConn();

		//creating prepared statement to update testingDate and result

		//A person can have multiple test results, we need update for a particular testingId also
		PreparedStatement ps=con.prepareStatement("UPDATE testing_history SET testing_date=?,result=? WHERE person_id=? AND testing_id=?");
		ps.setString(1,sdf.format(testingDate));
		ps.setString(2,result);
		ps.setInt(3, personId);
		ps.setInt(4, testingId);

		//executing prepared statement	
		int stat=ps.executeUpdate();
		return stat;
	}
	//tested OK
	public int deleteTestingHistory(Integer testingId) throws ClassNotFoundException, SQLException {

		Connection con = getConn();

		//deleting testing history based on testingId
		PreparedStatement ps=con.prepareStatement("DELETE from testing_history WHERE testing_id=?;");
		ps.setInt(1, testingId);
		int stat=ps.executeUpdate();
		return stat; 
	}

	//tested OK
	public int deleteTestingHistory() throws SQLException, ClassNotFoundException {

		Connection con = getConn();
		PreparedStatement ps=con.prepareStatement("DELETE FROM testing_history;");
		int stat=ps.executeUpdate();
		ps = con.prepareStatement("ALTER TABLE testing_history AUTO_INCREMENT = 1");
		Boolean res = ps.execute();
		System.out.println(res);
		return stat;
	}

}
