package org.sturtevantauto.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class IndexedCarsImporter {
    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306?autoReconnect=true&useSSL=false";
    private static String dbUsername = "imagesorter";
    private static String dbPassword = "4vSmbst4Q#uhL#3%";

    /**
     * Imports the indexed cars with their associated stock numbers to the SQL
     * database
     * 
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet use = statement.executeQuery("USE car_parts");
        File makemodelindex = new File("/Users/sturtevantauto/Documents/workspace/IndexedCars.txt");
        BufferedReader reader = new BufferedReader(new FileReader(makemodelindex));
        String line;
        while ((line = reader.readLine()) != null) {
            statement.executeUpdate("INSERT INTO `Indexed_Cars` (`StockNumber`) VALUES ('" + line + "')");
        }
        reader.close();
        use.close();
        statement.close();
        connection.close();

    }

}
