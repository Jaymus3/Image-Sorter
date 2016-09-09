package com.sturtevantauto.io;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class PricingToolHandler {
    static Car car = new Car();
    public static void getYears(JComboBox<String> yearBox) {
        long start = System.currentTimeMillis();
        try {
            Connection connection = car.getConnection();
            long connect = System.currentTimeMillis();
            System.out.println("Connect time: " + (connect - start));
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("show tables;");
            long statementt = System.currentTimeMillis();
            System.out.println("Statement time: " + (statementt - connect));
            while (rs.next()) {
                if (!car.getYearState())
                    yearBox.addItem(rs.getString("Tables_in_car_weight").replace("cars_", ""));
            }
            long adding = System.currentTimeMillis();
            System.out.println("Item adding time: " + (adding - statementt));
            Car.setYearState(true);
            rs.close();
            statement.close();
            long closingtime = System.currentTimeMillis();
            System.out.println("Closing time: " + (closingtime - adding));
            System.out.println("Total time: " + (closingtime - start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMakesByYear(String year, JComboBox<String> makeBox) {
        long start = System.currentTimeMillis();
        try {
            String previousmake = "A make that no vehicle could possibly ever be so the first make works every time";
            Connection connection = car.getConnection();
            long connect = System.currentTimeMillis();
            System.out.println("Connect time: " + (connect - start));
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            long statementt = System.currentTimeMillis();
            System.out.println("Statement time: " + (statementt - connect));
            while (rs.next()) {
                String make = rs.getString("Make");
                if (!make.equals(previousmake)) {
                    if (!make.contains("MAKE")) {
                        makeBox.addItem(make);
                        previousmake = make;
                    }
                }
            }
            long adding = System.currentTimeMillis();
            System.out.println("Item adding time: " + (adding - statementt));
            rs.close();
            statement.close();
            long closingtime = System.currentTimeMillis();
            System.out.println("Closing time: " + (closingtime - adding));
            System.out.println("Total time: " + (closingtime - start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getModelsByMake(String make, String year, JComboBox<String> modelBox)
    {
        long start = System.currentTimeMillis();
        try {
            Connection connection = car.getConnection();
            long connect = System.currentTimeMillis();
            System.out.println("Connect time: " + (connect - start));
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            long statementt = System.currentTimeMillis();
            System.out.println("Statement time: " + (statementt - connect));
            while (rs.next()) {
                String mak = rs.getString("Make");
                if (mak.equals(make)) {
                    String model = rs.getString("Model");
                    modelBox.addItem(model);
                } //TODO: $100 per 2000
            }
            long adding = System.currentTimeMillis();
            System.out.println("Item adding time: " + (adding - statementt));
            rs.close();
            statement.close();
            long closingtime = System.currentTimeMillis();
            System.out.println("Closing time: " + (closingtime - adding));
            System.out.println("Total time: " + (closingtime - start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int getWeightByCar(String make, String model, String year)
    {
        int weight = 0;
        long start = System.currentTimeMillis();
        try {
            Connection connection = car.getConnection();
            long connect = System.currentTimeMillis();
            System.out.println("Connect time: " + (connect - start));
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            long statementt = System.currentTimeMillis();
            System.out.println("Statement time: " + (statementt - connect));
            while (rs.next()) {
                String maka = rs.getString("Make");
                if (maka.equals(make)) {
                    String modele = rs.getString("Model");
                    if(modele.equals(model))
                        weight = Integer.parseInt(rs.getString("Weight"));
                } //TODO: $100 per 2000
            }
            long adding = System.currentTimeMillis();
            System.out.println("Weight lookup time: " + (adding - statementt));
            rs.close();
            statement.close();
            long closingtime = System.currentTimeMillis();
            System.out.println("Closing time: " + (closingtime - adding));
            System.out.println("Total time: " + (closingtime - start));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weight;
    }
    
    public static double convertToStandard(int metricweight)
    {
        double standard = 0;
        standard = metricweight / 0.45359237;
        return standard;
    }
    
    public static double getPrice(double standardweight)
    {
        double price = 0;
        price = standardweight * 0.05;
        return price;
    }
}
