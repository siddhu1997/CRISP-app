package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.DonationRequest;

public class DonationRequestDAO {
	private Connection getConn() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		//Class.forName(rb.getString("driver"));
		Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
		return con;
	}
	
	public List<DonationRequest> readDonationRequest() throws ClassNotFoundException, SQLException{
		List<DonationRequest> donationRequestsList=new ArrayList<DonationRequest>();
		Connection con = getConn();
		String statusString="pending";
		PreparedStatement st = con.prepareStatement("SELECT * FROM donation_request WHERE status=?");
		st.setString(1,statusString);
		ResultSet rs = st.executeQuery();
		DonationRequest dr=null;
		while(rs.next())
		{
			dr=new DonationRequest(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5));
			donationRequestsList.add(dr);
		}
		return donationRequestsList;
		
	}

	public String readDonationRequestStatus(Integer personId) throws ClassNotFoundException, SQLException {
		String result = null;
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT status FROM donation_request WHERE person_id=?");
		st.setInt(1, personId);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}
	public int updateStatus(Integer personId,String status) throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE donation_request SET status=? WHERE person_id=?");
		st.setString(1, status);
		st.setInt(2,personId);
		int num=st.executeUpdate();
		return num;
	}
	public int deleteDonationRequest(Integer personId) throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("DELETE FROM donation_request WHERE person_id=?");
		st.setInt(1,personId);
		int num=st.executeUpdate();
		return num;
	}
	
	public int deleteDonationRequest() throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("TRUNCATE TABLE donation_request");
		int num=st.executeUpdate();
		return num;
	}

}
