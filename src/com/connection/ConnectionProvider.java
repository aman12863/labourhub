package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionProvider {

	public static Connection connection=null;
	
	private ConnectionProvider()
	{
		
	}
	public static Connection getConn()
	{
		try {
			if(connection==null)
			{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","deepak");
			return connection;
			}
			else
			{
				return connection;
			}
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

		
	}
	

	
}
