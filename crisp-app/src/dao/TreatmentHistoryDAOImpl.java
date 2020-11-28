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

import model.TreatmentHistory;

public class TreatmentHistoryDAOImpl {

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

	private Connection getConn() throws ClassNotFoundException, SQLException {

		ResourceBundle rb = ResourceBundle.getBundle("db");
		Class.forName(rb.getString("driver"));
		Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
		return con;
	}
	
	//tested OK
	public Integer createTreatmentHistory(Integer personId,Date admissionDate) throws SQLException, ClassNotFoundException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("INSERT INTO treatment_history VALUES(?,'Patient infected with COVID-19',?,null,null)");
		st.setInt(1, personId);
		st.setString(2,sdf.format(admissionDate));
		Integer result = 0;
		result = st.executeUpdate();
		if(result == 0)
			return 0;
		return result;
	}

	//tested OK
	public List<TreatmentHistory> readTreatmentHistory() throws ClassNotFoundException, SQLException, ParseException
	{
		//Constructor -> Integer personId, String treatmentDetails, Date admissionDate, Date recoveredDate,Date deathDate
		List<TreatmentHistory> treatmentHistoryList=new ArrayList<TreatmentHistory>();
		TreatmentHistory treathistory = null;
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM treatment_history ");
		ResultSet rs = st.executeQuery();	
		while(rs.next())
		{
			treathistory = null;
			treathistory=new TreatmentHistory(rs.getInt(1),rs.getString(2), rs.getString(3) == null? null:sdf.parse(rs.getString(3)),
					rs.getString(4) == null ? null:sdf.parse(rs.getString(4)),
							rs.getString(5) == null ? null:sdf.parse(rs.getString(5)));
			if(treathistory != null)
				treatmentHistoryList.add(treathistory);
		}
		return treatmentHistoryList;
	}

	//tested OK
	public TreatmentHistory readTreatmentHistoryByPersonId(Integer personId) throws ClassNotFoundException, SQLException, ParseException
	{
		TreatmentHistory treatment = null;
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM treatment_history WHERE person_id=?");
		st.setInt(1,personId);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			treatment = new TreatmentHistory(rs.getInt(1),rs.getString(2), rs.getString(3) == null? null:sdf.parse(rs.getString(3)),
					rs.getString(4) == null ? null:sdf.parse(rs.getString(4)),
							rs.getString(5) == null ? null:sdf.parse(rs.getString(5)));
		}
		if(treatment == null)
			return new TreatmentHistory();
		return treatment;
	}

	//tested OK
	public Integer updateAdmissionDate(Integer personId,String treatmentDetails,Date admissionDate) throws ClassNotFoundException, SQLException {
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE treatment_history SET admission_date=?,treatment_details=? WHERE person_id=?");
		st.setInt(3, personId);
		st.setString(2, treatmentDetails);
		st.setString(1, sdf.format(admissionDate));
		Integer result = 0;
		result = st.executeUpdate();
		if(result == 0)
			return 0;
		return result;
	}

	//tested OK
	public int updateRecoveredDate(Integer personId,String treatmentDetails,Date recoveredDate) throws SQLException, ClassNotFoundException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE treatment_history SET recovered_date=?,treatment_details=? WHERE person_id=?");
		st.setInt(3,personId);
		st.setString(2, treatmentDetails);
		st.setString(1,recoveredDate == null ?null:sdf.format(recoveredDate));
		Integer result = 0;
		result = st.executeUpdate();
		if(result == 0)
			return 0;
		return result;
	}

	//tested OK
	public int updateDeathDate(Integer personId,String treatmentDetails, Date deathDate) throws SQLException, ClassNotFoundException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE treatment_history SET death_date=?,treatment_details=? WHERE person_id=?");
		st.setInt(3,personId);
		st.setString(2, treatmentDetails);
		st.setString(1,deathDate == null ?null:sdf.format(deathDate));
		Integer result = 0;
		result = st.executeUpdate();
		if(result == 0)
			return 0;
		return result;

	}

	//tested OK
	public int deleteTreatmentHistory(Integer personId) throws SQLException, ClassNotFoundException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("DELETE FROM treatment_history WHERE person_id=?");
		st.setInt(1,personId);
		Integer result = 0;
		result = st.executeUpdate();
		if(result == 0)
			return 0;
		return result;
	}
	//tested OK
	public int deleteTreatmentHistory() throws ClassNotFoundException, SQLException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("DELETE FROM treatment_history");
		Integer result = 0;
		result = st.executeUpdate();
		if(result == 0)
			return 0;
		return result;
	}
}
