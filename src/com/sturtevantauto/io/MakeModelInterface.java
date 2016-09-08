package com.sturtevantauto.io;

import java.io.IOException;
import java.sql.*;

public class MakeModelInterface {
    static Car car = new Car();
    public static boolean foundmake = false;

    /**
     * Checks make model index to see if we have a match stored for the given
     * model
     * 
     * @param model
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void CheckMakeModelIndex(String model) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = car.getConnection();
        Statement statement = connection.createStatement();
        ResultSet use = statement.executeQuery("USE car_parts");
        ResultSet rs = statement.executeQuery("SELECT * FROM Make_Model_Index");
        while (rs.next()) {
            String make = rs.getString("Make");
            String modelsql = rs.getString("Model");
            if (modelsql.equals(model)) {
                car.setMake(make);
                System.out.println(
                        "It's a " + car.getMake() + " with stock number " + car.getStock());
                foundmake = true;
            }
        }
        use.close();
        rs.close();
        statement.close();
    }

    /**
     * Writes make model matchup to the index when given a model and make
     * 
     * @param model
     * @param make
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void WriteMakeModelIndex(String model, String make)
            throws IOException, ClassNotFoundException, SQLException {
        Connection connection = car.getConnection();
        Statement statement = connection.createStatement();
        ResultSet use = statement.executeQuery("USE car_parts");
        statement.executeUpdate(
                "INSERT INTO `Make_Model_Index` (`Make`, `Model`) VALUES ('" + make + "', '" + model + "')");
        use.close();
        statement.close();
    }

}
