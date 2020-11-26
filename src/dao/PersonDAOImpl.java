package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Person;

	public class PersonDAOImpl {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		private Connection getConnection() throws ClassNotFoundException, SQLException
		{
			ResourceBundle rb = ResourceBundle.getBundle("db");
			String url=rb.getString("url");
			String username=rb.getString("user");
			String password=rb.getString("password");
			return DriverManager.getConnection(url,username,password);
		}
		
		//tested OK
		 public int createPerson(Person person) throws ClassNotFoundException, SQLException {
			 Connection connection=getConnection();
			 PreparedStatement st=connection.prepareStatement("INSERT INTO person(name,dob,phone_number,blood_group,location,password) VALUES(?,?,?,?,?,?)");
			 st.setString(1, person.getName());
			 st.setString(2,sdf.format(person.getDob()));
			 st.setString(3, person.getPhoneNumber());
			 st.setString(4, person.getBloodGroup());
			 st.setString(5, person.getLocation());
			 st.setString(6, person.getPassword());
			 int no=st.executeUpdate();
			return no;
		}
		
		 //tested OK
		 public Person readPersonById(Integer personId) throws ClassNotFoundException, SQLException, ParseException {
			 Connection connection=getConnection();
				
				PreparedStatement st=connection.prepareStatement("SELECT * FROM person WHERE person_id=?");
				st.setInt(1, personId);
				ResultSet rs=st.executeQuery();	
				Person person = null;
				
				while(rs.next()) {
					person = new Person(rs.getInt(1),rs.getString(2),sdf.parse(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				}				
				return person;		
			}
	
		 //tested OK
		public Person readPersonByPhoneNumber(String phoneNumber) throws ClassNotFoundException, SQLException, ParseException {
			Connection connection=getConnection();
			
			PreparedStatement st=connection.prepareStatement("SELECT * FROM person WHERE phone_number=?");
			st.setString(1, phoneNumber);
			ResultSet rs=st.executeQuery();	
			Person person = null;
			
			while(rs.next()) {
				person = new Person(rs.getInt(1),rs.getString(2),sdf.parse(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}				
			return person;		
		}
		
		//tested OK
		public List<Person> readPerson() throws ClassNotFoundException, SQLException, ParseException {
			
			Connection connection=getConnection();
			PreparedStatement st=connection.prepareStatement("SELECT * FROM person");
			ResultSet rs = st.executeQuery();
			
			Person person;
			List<Person> personList=new ArrayList<>();
			
			while(rs.next())
			{
				person = new Person(rs.getInt(1),rs.getString(2),sdf.parse(rs.getString(3)),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				personList.add(person);
			}
		
			return personList;
		}
		
			
		//tested OK
		public int updatePerson(Integer personId, String phoneNumber, String location) throws SQLException, ClassNotFoundException {
			
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement("UPDATE person SET phone_number =?,location=? WHERE person_id =?");
			st.setString(1,phoneNumber);
			st.setString(2, location);
			st.setInt(3, personId);
			int no=st.executeUpdate();
			return no;		
		}
		
		//tested OK
		public int deletePerson(Integer personId) throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement("DELETE FROM person WHERE person_id=?");
			st.setInt(1, personId);
			int no=st.executeUpdate();	
			return no;		
		}
		
		//tested OK
		public int deletePerson() throws SQLException, ClassNotFoundException {
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement("DELETE FROM person");
			int no=st.executeUpdate();
			st = connection.prepareStatement("ALTER TABLE person AUTO_INCREMENT = 1");
			Boolean res = st.execute();
			System.out.println(res);
			return no;
		}

		

		
}
