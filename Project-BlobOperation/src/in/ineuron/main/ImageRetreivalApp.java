package in.ineuron.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import in.ineuron.util.Util;

public class ImageRetreivalApp {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet resultset=null;
		int id=1;
		try {
			connection=Util.getJdbcConnection();
			String sqlSelectQuery="select id,Name,image from person where id=?";
			if(connection!=null) 
				 pstmt = connection.prepareStatement(sqlSelectQuery);
			if(pstmt!=null) {
				pstmt.setInt(1,id);
				 resultset = pstmt.executeQuery();
			}
			if(resultset!=null) {
				if(resultset.next()) {
					int sid = resultset.getInt(1);
					String sname = resultset.getString(2);
					InputStream is = resultset.getBinaryStream(3);
					
					File file = new File("copied.jpg");
					FileOutputStream fos = new FileOutputStream(file);

					// copying the data from is to fos
					IOUtils.copy(is, fos);

					fos.close();

					
					System.out.println(sid + "\t" + sname + "\t" + file.getAbsolutePath());
				} else {
					System.out.println("Record not available for the given id :: " + id);
				}
				
			}
			
			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		}

	}

}
