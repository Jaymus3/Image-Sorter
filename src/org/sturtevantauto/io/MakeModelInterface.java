package org.sturtevantauto.io;

import java.io.IOException;
import java.sql.*;

public class MakeModelInterface {
	public static boolean foundmake = false;
	
	
	/**
	 * Checks make model index to see if we have a match stored for the given model
	 * @param model
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void CheckMakeModelIndex(String model) throws IOException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(CarDefinitions.getDBUrl(), CarDefinitions.getSQLUsername(), CarDefinitions.getSQLPassword());
	    Statement statement = connection.createStatement();
		ResultSet use = statement.executeQuery("USE car_parts");
	    ResultSet rs = statement.executeQuery("SELECT * FROM Make_Model_Index");
	    while(rs.next())
	    {
	    	String make = rs.getString("Make");
	    	String modelsql = rs.getString("Model");
	    	if(modelsql.equals(model))
	    	{
	    		CarDefinitions.setMake(make);
	    		System.out.println("It's a " + CarDefinitions.getMake() + " with stock number " + CarDefinitions.getStock());
	    		foundmake = true;
	    	}
	    }
	    use.close();
	    rs.close();
	    statement.close();
	    connection.close();
	}
	/**
	 * Writes make model matchup to the index when given a model and make
	 * @param model
	 * @param make
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void WriteMakeModelIndex(String model, String make) throws IOException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(CarDefinitions.getDBUrl(), CarDefinitions.getSQLUsername(), CarDefinitions.getSQLPassword());
	    Statement statement = connection.createStatement();
		ResultSet use = statement.executeQuery("USE car_parts");
		statement.executeUpdate("INSERT INTO `Make_Model_Index` (`Make`, `Model`) VALUES ('" + make + "', '" + model + "')");
		use.close();
		statement.close();
		connection.close();
	}

}
