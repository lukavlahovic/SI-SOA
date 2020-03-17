package database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import appFramework.ConfigComponent;

public class ConfigImplementation implements ConfigComponent {
	
	private static String ip = "64.225.110.65";
	private static String username = "tim_402_1_si2019";
	private static String password = "PYzqSrMC";
	private static String database = "tim_402_1_si2019";
	
	private static Connection connection;
	
	public ConfigImplementation()
	{
		configurationDB();
	}
	
	
	@Override
	public void configurationDB() {
		connect(ip, username, password, database);
		
	}
	
	public static Connection getConnection() 
	{
		return connection;
	}
	
	public static boolean connect (String ip, String username, String password,String database) 
	{
	
		try 
		{
			String query = "jdbc:mysql://" + ip + "/" + database;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(query, username,password);
			System.out.println("Uspesna konekcija");
			return true;
		}
		catch (SQLException | ClassNotFoundException e) 
		{
			
			return false;
		}
	}
	
	public static void disconnect() 
	{

		connection = null;
	}

	

}
