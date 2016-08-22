package org.sturtevantauto.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class PricingToolHandler 
{
	public static void getYears(JComboBox<String> yearBox)
	{
	    try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(CarDefinitions.getDBUrl(), CarDefinitions.getSQLUsername(), CarDefinitions.getSQLPassword());
            Statement statement = connection.createStatement();
            ResultSet use = statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("show tables;");
            while(rs.next())
            {
                if(CarDefinitions.getYearState() == false)
                if(!rs.getString("Tables_in_car_weight").replace("cars_", "").equals("1971"))
                yearBox.addItem(rs.getString("Tables_in_car_weight").replace("cars_", ""));
            }
            CarDefinitions.setYearState(true);
            use.close();
            rs.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public static void getMakesByYear(String year, JComboBox<String> makeBox)
	{
	    try {
	        String previousmake = "A make that no vehicle could possibly ever be so the first make works every time";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(CarDefinitions.getDBUrl(), CarDefinitions.getSQLUsername(), CarDefinitions.getSQLPassword());
            Statement statement = connection.createStatement();
            ResultSet use = statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            while(rs.next())
            {
               String make = rs.getString("Make");
               if(!make.equals(previousmake))
               {
                   if(!make.contains("MAKE"))
                   {
                   makeBox.addItem(make);
                   previousmake = make;
                   }
               }
            }
            use.close();
            rs.close();
            statement.close();
            connection.close();
	    } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
