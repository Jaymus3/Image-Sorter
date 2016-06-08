package org.sturtevantauto.io;

import java.io.IOException;
import java.sql.*;

public class MakeModelInterface {
	public static boolean foundmake = false;
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306?autoReconnect=true&useSSL=false";
	private static String dbUsername = "imagesorter";
	private static String dbPassword = "4vSmbst4Q#uhL#3%";
	public static void CheckMakeModelIndex(String model) throws IOException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
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
	    		System.out.println("It's a " + CarDefinitions.getMake());
	    		foundmake = true;
	    	}
	    }
	    use.close();
	    rs.close();
	    statement.close();
	    connection.close();
		
		/*
		String line;
		File makemodelindex = new File("/Users/sturtevantauto/Documents/workspace/MakeModelIndex.txt");
			BufferedReader reader = new BufferedReader(new FileReader(makemodelindex));
			while ((line = reader.readLine()) != null)
		    {
				if(line.contains(model))
				{
					//System.out.println("We had the make indexed!");
					line = line.replace(model, "");
					line = line.replace(" ", "");
					CarDefinitions.setMake(line);
					System.out.println("It's a " + CarDefinitions.getMake());
					foundmake = true;
				}
		    }
			reader.close();
			*/
	}
	public static void WriteMakeModelIndex(String model, String make) throws IOException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	    Statement statement = connection.createStatement();
		ResultSet use = statement.executeQuery("USE car_parts");
		statement.executeUpdate("INSERT INTO `Make_Model_Index` (`Make`, `Model`) VALUES ('" + make + "', '" + model + "')");
		/*
		File makemodelindex = new File("/Users/sturtevantauto/Documents/workspace/MakeModelIndex.txt");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(makemodelindex, true)));
	    out.println(make + " " + model);
		out.close();
		*/
		use.close();
		statement.close();
		connection.close();
	}

}
