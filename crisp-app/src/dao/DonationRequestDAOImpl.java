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

public class DonationRequestDAOImpl {
	private Connection getConn() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Class.forName(rb.getString("driver"));
		Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
		return con;
	}

	//tested OK
	public int addDonationRequest(DonationRequest dr) throws ClassNotFoundException, SQLException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("INSERT INTO donation_request(person_id,donor_remarks,admin_remarks,status) VALUES(?,?,?,?)");
		st.setInt(1, dr.getPersonId());
		st.setString(2, dr.getDonorRemarks());
		st.setString(3, dr.getAdminRemarks());
		st.setString(4,dr.getStatus());	
		int no=st.executeUpdate();
		return no;
	}

	//tested OK
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

	//tested OK
	public List<DonationRequest> readDonationRequestStatusByPerson(Integer personId) throws ClassNotFoundException, SQLException {
		DonationRequest result = null;
		List<DonationRequest> donationRequestList = new ArrayList<>();
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM donation_request WHERE person_id=?");
		st.setInt(1, personId);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			result = new DonationRequest(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
			System.out.println(result);
			donationRequestList.add(result);
		}
		return donationRequestList;
	}

	//tested OK
	public DonationRequest readDonationRequestById(Integer requestId) throws ClassNotFoundException, SQLException {
		DonationRequest result = null;
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM donation_request WHERE req_id=?");
		st.setInt(1, requestId);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			result = new DonationRequest(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
		}
		return result;
	}

	//tested OK
	public int updateStatus(Integer personId,String status,String adminRemarks,Integer requestId) throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE donation_request SET status=?,admin_remarks=? WHERE person_id=? AND req_id=?");
		st.setString(1,status);
		st.setString(2, adminRemarks);
		st.setInt(3,personId);
		st.setInt(4,requestId);
		int num=st.executeUpdate();
		return num;
	}

	//tested OK
	public int deleteDonationRequest(Integer requestId) throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("DELETE FROM donation_request WHERE req_id=?");
		st.setInt(1,requestId);
		int num=st.executeUpdate();
		return num;
	}

	//tested OK
	public int deleteDonationRequest() throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("DELETE FROM donation_request");
		int num=st.executeUpdate();
		st = con.prepareStatement("ALTER TABLE donation_request AUTO_INCREMENT = 1");
		Boolean res = st.execute();
		System.out.println(res);
		return num;
	}

}
