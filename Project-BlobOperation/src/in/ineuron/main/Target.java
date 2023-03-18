package in.ineuron.main;

import java.io.FileInputStream;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.Util;
public class Target {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner scan=null;
		
		String name=null;
		String imageloc=null;
		try {
			connection= Util.getJdbcConnection();
			String sqlInsertQuery="insert into person(`Name`,`Image`)values(?,?)";
			 pstmt = connection.prepareStatement(sqlInsertQuery);
			 System.out.println("Statement Created Successfully...");
			 System.out.println("*****************************");
			if(pstmt!=null) {
				scan= new Scanner(System.in);
				if(scan!=null) {
					System.out.println("ENTER YOUR NAME::");
					name=scan.next();
					System.out.println("ENTER LOCATION OF IMAGE::");
					imageloc=scan.next();
				}
				pstmt.setString(1, name);
				pstmt.setBinaryStream(2, new FileInputStream(new File(imageloc)));
				int	rowAffected=pstmt.executeUpdate();
				System.out.println("No of rows affected="+rowAffected);
				System.out.println("*********Thank You************");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {

			try {
				Util.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scan.close();
		}

	}

}
