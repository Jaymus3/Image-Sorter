package org.sturtevantauto.io;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Logger {
	
	/**
	 * Checks if the car is indexed in the SQL database
	 * @param stock
	 * @return boolean isIndexed
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean CheckIfCarIndexed(String stock) throws IOException, ClassNotFoundException, SQLException
	{
		boolean thebool = false;
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(CarDefinitions.getDBUrl(), CarDefinitions.getSQLUsername(), CarDefinitions.getSQLPassword());
	    Statement statement = connection.createStatement();
	    ResultSet use = statement.executeQuery("USE car_parts");
        ResultSet rs = statement.executeQuery("SELECT * FROM Indexed_Cars");
	    while(rs.next())
	    {
	         String stocksql = rs.getString("StockNumber");
	         if(stocksql.equals(stock))
	         {
	        	 System.err.println("Car already indexed!");
	        	 thebool = true;
	         }
	      }
	    	rs.close();
	    	use.close();
	    	statement.close();
	    	connection.close();
			return thebool;
	}
	
	/**
	 * Logs the car from the given stock number in the database as sorted
	 * @param stock
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void LogCar(String stock) throws IOException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(CarDefinitions.getDBUrl(), CarDefinitions.getSQLUsername(), CarDefinitions.getSQLPassword());
	    Statement statement = connection.createStatement();
		ResultSet use = statement.executeQuery("USE car_parts");
	    statement.executeUpdate("INSERT INTO `Indexed_Cars` (`StockNumber`) VALUES ('" + stock + "')");
    	use.close();
    	statement.close();
    	connection.close();
	}

}
