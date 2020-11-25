package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

	public class PersonDAOImpl {
		
		private Connection getConnection() throws ClassNotFoundException, SQLException
		{
			ResourceBundle rb = ResourceBundle.getBundle("db");
			String driver=rb.getString("driver");
			String url=rb.getString("url");
			String username=rb.getString("username");
			String password=rb.getString("password");
			Class.forName(driver);
			return DriverManager.getConnection(url,username,password);
		}
		
		 public int createPerson(Person person) throws ClassNotFoundException, SQLException {
			 Connection connection=getConnection();
			 PreparedStatement st=connection.prepareStatement("INSERT INTO person VALUES(?,?,?,?,?,?)");
			// st.setInt(1, person.getPersonId());
			 st.setString(2, person.getName());
			 st.setDate(3, (Date) person.getDob());
			 st.setString(4, person.getPhoneNumber());
			 st.setString(5, person.getBloodGroup());
			 st.setString(6, person.getLocation());
			 st.setString(7, person.getPassword());
			 int no=st.executeUpdate();
			return no;
		}
		
		public Person readPersonByPhoneNumber(String phoneNumber) throws ClassNotFoundException, SQLException {
			Connection connection=getConnection();
			PreparedStatement st=connection.prepareStatement("SELECT * FROM person WHERE phone_number=?");
			st.setString(1, phoneNumber);
			ResultSet rs=st.executeQuery();
			
				Person person=new Person(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				
			return person;
			
		}
		
		public List<Person> readPerson() throws ClassNotFoundException, SQLException {
			Connection connection=getConnection();
			PreparedStatement st=connection.prepareStatement("SELECT * FROM person");
			ResultSet rs = st.executeQuery();
			List<Person> personList=new ArrayList<>();
			while(rs.next())
			{
				Person person=new Person(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				personList.add(person);
			}
		
			return personList;
			//return null;
		}
		
			
		
		public int updatePerson(Integer personId, String phoneNumber, String location) throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement("UPDATE person SET phone_number =?,location=? WHERE person_id =?");
			st.setString(4,phoneNumber );
			st.setString(6, location);
			st.setInt(1, personId);

			int no=st.executeUpdate();
			
			return no;
			
		}
		
		public int deletePerson(Integer personId) throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement("DELETE FROM person WHERE person_id=?");
			st.setInt(1, personId);

			int no=st.executeUpdate();
			
			return no;
			
		}
		
		public int deletePerson() throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement("DELETE FROM person");
			//st.setInt(1, personId);

			int no=st.executeUpdate();
			
			return no;
		}

		

		
}