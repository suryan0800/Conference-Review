import java.sql.*;

//create table author(email varchar(20),fname varchar(20), lname varchar(20),phone varchar(20));

// create table reviewer(email varchar(20),fname varchar(20), lname varchar(20),phone varchar(20),affiliation varchar(20),topics varchar(20));

//create table paper(no varchar(20),title varchar(20),abstract varchar(20),file varchar(20),email varchar(20),email2 varchar(20),email3 varchar(20),email4 varchar(20));

// create table ratings(merit int,readability int,origin int,relav int,recom int,comment1 varchar(20),comment2 varchar(20),paperno varchar(20),email varchar(20));


public class Table {
	
	public static void main(String args[])
	{
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examples?useSSL=false", "root", "root");
			
			Statement cs = con.createStatement();
			
			cs.executeUpdate("create table author(email varchar(20),fname varchar(20), lname varchar(20),phone varchar(20));");
			
			cs.executeUpdate("create table reviewer(email varchar(20),fname varchar(20), lname varchar(20),phone varchar(20),affiliation varchar(20),topics varchar(20));");
			
			cs.executeUpdate("create table paper(no varchar(20),title varchar(20),abstract varchar(20),file varchar(20),email varchar(20),email2 varchar(20),email3 varchar(20),email4 varchar(20));");
			
			cs.executeUpdate("create table ratings(merit int,readability int,origin int,relav int,recom int,comment1 varchar(20),comment2 varchar(20),paperno varchar(20),email varchar(20));");

			
			con.close();
			

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
