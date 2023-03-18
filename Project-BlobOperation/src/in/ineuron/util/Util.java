package in.ineuron.util;

import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Util {
	
private Util() {}
static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded successfully...");
		System.out.println("*****************************");
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
}

public static Connection getJdbcConnection() throws IOException,SQLException {
	
	FileInputStream fis = new FileInputStream("F:\\javaProjects\\BLOBOPERATION\\appilication.properties");
	Properties properties = new Properties();
	properties.load(fis);
	
	 Connection connection= DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
	System.out.println("Connection object Created Successfully...");
	System.out.println("*****************************");
	 return connection;
}
public static void cleanUp(Connection con, PreparedStatement pstmt, Object object) throws SQLException {
	if(con!=null) {
		con.close();
		pstmt.close();
		
	}
}

}
