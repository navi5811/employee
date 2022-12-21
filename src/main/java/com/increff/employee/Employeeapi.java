
package com.increff.employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.*;
import java.util.logging.Logger;

public class Employeeapi {
	
	private static final Logger dlog = Logger.getLogger(Employeeapi.class.getName());
	private Connection con;
	public static void main(String[] args) throws ClassNotFoundException, SQLException,IOException {
		
	}
	public Employeeapi() throws Exception {
		Properties props=new Properties();
		InputStream inStream=new FileInputStream("employee.properties");
		props.load(inStream);
		Class.forName(props.getProperty("jdbc.driver"));
		con =DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));//connection is created
		
	}
	
	public void insert() throws SQLException{
		dlog.info("inserting employees");
		Statement stmt=con.createStatement();
		for (int i=0;i<3;i++)
		{
			int id =i;
			int age =30+i;
			String name ="name"+i;
			String query="insert into employee values(" + id + ", '" + name + "' , " + age + ")";
			dlog.info(query);
			stmt.executeUpdate(query);
		}
		stmt.close();
	}
	public ResultSet select() throws SQLException{
		dlog.info("selecting employees");
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery("Select * from employee"); 
		return rs;
		//the result of the query executed is stored in rs whole table is stored in the rs(pointer) whole table means it also includes the heading of the column so to skip the column heading we will do the rs next
		//while(rs.next()) {
		//dlog.info(rs.getInt(1)+ " "+rs.getString(2)+" "+rs.getInt(3));
		//}
		//stmt.close();
	}
	public void delete() throws SQLException{
		dlog.info("deleting employees");
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery("Select * from employee");//he rs(pointer) whole table means it also includes the heading of the column so to skip the column heading we will do the rs next
		List<Integer> idList = new ArrayList<Integer>();
		while(rs.next()) {
			idList.add(rs.getInt(1));
		}
		for(int i=0;i<idList.size();i++)
		{
			stmt.executeUpdate("delete from employee where id =" + idList.get(i));
		}
		stmt.close();
	}
	

	

}


