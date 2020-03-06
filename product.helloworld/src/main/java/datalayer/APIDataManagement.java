package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import database.DatabaseConnection;

public class APIDataManagement {



	public int Db_TEST_Insert(String name, String value) {

		try {
			Connection con = DatabaseConnection.initializeDatabase(); 
			PreparedStatement st = con 
					.prepareStatement("insert into TEST (NAME,VAL) values(?, ?)"); 
			st.setString(1, name); 
			st.setString(2, value); 
			st.executeUpdate(); 
			st.close(); 
			con.close(); 
			return 0;

		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
			return 1;
		}

	}

	public HashMap<Integer, String> Db_TEST_Select() {

		try {
			Connection con = DatabaseConnection.initializeDatabase(); 

			String query = "SELECT * FROM TEST";

			// create the java statement
			Statement st = con.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);


			HashMap<Integer, String> map = new HashMap<Integer, String>();			 

			int j=1;
			while (rs.next()){
				String name = rs.getString("name");
		        String val = rs.getString("val");
				map.put(j,"Name="+name + ","+ "Val="+val);
				j++;
			}
			
			st.close(); 
			con.close(); 
			return map;

		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
			return null;
		}

	}



}
