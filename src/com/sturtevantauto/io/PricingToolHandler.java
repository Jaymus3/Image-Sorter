package com.sturtevantauto.io;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

public class PricingToolHandler {
    static Car car = new Car();
    static String[][] makemodels = new String[200][200];
    static String[][] makemodelstrimmed = { { "#MAKE", "#MODEL", "#WEIGHT'", "#PRICE" }, { "#MAKE2", "#MODEL2", "#WEIGHT2'", "#PRICE2" } }; // This is just so WindowBuilder doesn't crash when parsing PricingToolSelectCarPopup

    /**
     * Adds a list of all of the parsed years to the supplied JComboBox
     * @param yearBox
     * @author Aevum Kairos
     */
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
/**
 * When given a particular year and a JComboBox, it populates the JComboBox with all makes associated.
 * @param year
 * @param makeBox
 * @author Aevum Kairos
 */
    public static void getMakesByYear(String year, JComboBox<String> makeBox) {
        try {
            String previousmake = "A make that no vehicle could possibly ever be so the first make works every time";
            Connection connection = car.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            while (rs.next()) {
                String make = rs.getString("Make");
                if (!make.equals(previousmake)) {
                    if (!make.contains("MAKE")) {
                        makeBox.addItem(make);
                        previousmake = make;
                    }
                }
            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * When given a year and a make, this method populates a JComboBox with all of the available models.
 * @param make
 * @param year
 * @param modelBox
 * @author Aevum Kairos
 */
    public static void getModelsByMake(String make, String year, JComboBox<String> modelBox) {
        try {
            Connection connection = car.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            while (rs.next()) {
                String mak = rs.getString("Make");
                if (mak.equals(make)) {
                    String model = rs.getString("Model");
                    modelBox.addItem(model);
                }
            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * Returns the weight of a given car when given the make, model, and year of the car.
 * @param make
 * @param model
 * @param year
 * @return
 * @author Aevum Kairos
 */
    public static int getWeightByCar(String make, String model, String year) {
        int weight = 0;
        try {
            Connection connection = car.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year);
            while (rs.next()) {
                String maka = rs.getString("Make");
                if (maka.equals(make)) {
                    String modele = rs.getString("Model");
                    if (modele.equals(model))
                        weight = Integer.parseInt(rs.getString("Weight"));
                }
            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weight;
    }
/**
 * Returns whether any makes were found and adds the results to an array for later.
 * @param year
 * @param make
 * @return
 * @author Aevum Kairos
 */
    public static boolean getCarMakeResults(int year, String make) {
        boolean foundmake = false;
        int i = 0;
        try {
            Connection connection = car.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year + " WHERE Make LIKE '" + make + "'");
            while (rs.next()) {
                if (rs.getString("Make").equalsIgnoreCase(make)) {
                    foundmake = true;
                    double std = convertToStandard(Integer.parseInt((rs.getString("Weight"))));
                    double price = getPrice(std);
                    String prices = "$" + Math.round(price);
                    String[] add = { rs.getString("Make"), rs.getString("Model"), rs.getString("Weight"), prices };
                    makemodels[i] = add;
                    i++;
                }

            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (String[] s : makemodels) {
            if (s[0] != null) {
                count++;
            }
        }
        makemodelstrimmed = new String[count][3];
        int index = 0;
        for (String[] s : makemodels) {
            if (s[0] != null) {
                makemodelstrimmed[index++] = s;
            }
        }
        return foundmake;

    }
/**
 * Returns whether a model was found and adds the models found to an array for later.
 * @param year
 * @param make
 * @param model
 * @return
 * @author Aevum Kairos
 */
    public static boolean getCarModelResults(int year, String make, String model) {
        boolean foundmodel = false;
        boolean first = true;
        int i = 0;
        try {
            Connection connection = car.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("USE car_weight");
            ResultSet rs = statement.executeQuery("SELECT * FROM cars_" + year + " WHERE Make LIKE '" + make + "'");
            while (rs.next()) {
                if (rs.getString("Model").contains(model.toUpperCase())) {
                    if (first) {
                        makemodels = new String[200][200];
                        first = false;
                    }
                    double std = convertToStandard(Integer.parseInt((rs.getString("Weight"))));
                    double price = getPrice(std);
                    String prices = "$" + Math.round(price);
                    String[] add = { rs.getString("Make"), rs.getString("Model"), rs.getString("Weight"), prices };
                    makemodels[i] = add;
                    i++;
                    foundmodel = true;
                }
            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (String[] s : makemodels) {
            if (s[0] != null) {
                count++;
            }
        }
        makemodelstrimmed = new String[count][3];
        int index = 0;
        for (String[] s : makemodels) {
            if (s[0] != null) {
                makemodelstrimmed[index++] = s;
            }
        }
        return foundmodel;

    }
/**
 * Converts a weight from metric to standard
 * @param metricweight
 * @return
 * @author Aevum Kairos
 */
    public static double convertToStandard(int metricweight) {
        double standard = 0;
        standard = metricweight / 0.45359237;
        return standard;
    }
/**
 * Caclulates the price of a standard weight.  TODO: Make this adjustable in admin panel.
 * @param standardweight
 * @return
 * @author Aevum Kairos
 */
    public static double getPrice(double standardweight) {
        double price = 0;
        price = standardweight * 0.05;
        return price;
    }
/**
 * Returns the array of makes, models, weights, and prices assembled from other methods that must be executed first.
 * @return
 * @author Aevum Kairos
 */
    public static String[][] getModelArray() {
        return makemodelstrimmed;
    }
}
