package com.sturtevantauto.io;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * This class is used to store all of the common variables accessed by any tools
 * in the program.
 * 
 * @author sturtevantauto
 *
 */
public class Car {
    static String make;
    static String model;
    static File picloc = new File("/Users/sturtevantauto/Pictures/Car_Pictures/SORT_ME/");
    static File[] list = picloc.listFiles();
    static String stock;
    static File stockfile;
    static boolean registerwindow;
    private static String dbUrl = "jdbc:mysql://192.168.1.38:3306?autoReconnect=true&useSSL=false";
    private static String dbUsername = "imagesorter";
    private static String dbPassword = "4vSmbst4Q#uhL#3%";
    static String[] imagenames = new String[50];
    static boolean yearState;
    Connection connection = null;

    public static void setYearState(boolean yearState) {
        Car.yearState = yearState;
    }

    /**
     * Sets the picture location to the given path
     * 
     * @param folderpath
     */
    public void setPictureLocation(String folderpath) {
        picloc = new File(folderpath);
    }

    public void setDBUrl(String DBUrl) {
        dbUrl = "jdbc:mysql://" + DBUrl + "?autoReconnect=true&useSSL=false";
    }

    /**
     * Sets whether the registration window is currently open or not.
     * 
     * @param register
     */
    public void setRegisterOpen(boolean register) {
        registerwindow = register;
    }

    /**
     * Sets the car model to the given string
     * 
     * @param models
     */
    public void setModel(String models) {
        model = models;
    }

    /**
     * Sets the car make to the given string
     * 
     * @param makes
     */
    public void setMake(String makes) {
        make = makes;
    }

    /**
     * Set's the stock number placeholder file to the given file
     * 
     * @param stock
     */
    public void setStockFile(File stock) {
        stockfile = stock;
    }

    /**
     * Sets the list of pictures in a file array to a new file array
     * 
     * @param thelist
     */
    public void setList(File[] thelist) {
        list = thelist;
    }

    /**
     * Set's the image path of a specific image to the given string. i is the
     * array position given
     * 
     * @param imagename
     * @param i
     */
    public void setImageNames(String imagename, int i) {
        imagenames[i] = imagename;
    }

    /**
     * Trims the stock file name down to only contain the stock number.
     * 
     * @param skipped
     *            This determines whether or not the trimmer is trimming the
     *            stock number of previously skipped cars or not.
     */
    public void TrimStock(boolean skipped) {
        if (skipped) {
            stock = stock.replace(".JPG", "");
            stock = stock.replace(".jpg", "");
            stock = stock.replace("__", "");
        } else {
            stock = stock.replace(".JPG", "");
            stock = stock.replace(".jpg", "");
            stock = stock.substring(9);
        }
    }

    /**
     * Returns the image paths array
     * 
     * @return imagepaths
     */
    public String[] getImageNames() {
        return imagenames;
    }

    /**
     * Returns the database URL
     * 
     * @return dbUrl
     */
    public String getDBUrl() {
        return dbUrl;
    }

    public boolean getYearState() {
        return yearState;
    }

    /**
     * Returns the database username.
     * 
     * @return dbUsername
     */
    public String getSQLUsername() {
        return dbUsername;
    }

    /**
     * Returns the database password.
     * 
     * @return dbPassword
     */
    public String getSQLPassword() {
        return dbPassword;
    }

    /**
     * Gets whether the registration window is open or not.
     * 
     * @return boolean registerwindowstatus
     */
    public boolean getRegister() {
        return registerwindow;
    }

    /**
     * Returns the make of the active car
     * 
     * @return make
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the stock number of the active car to the given string
     * 
     * @param string
     */
    public void setStock(String string) {
        stock = string;
    }

    /**
     * Returns the stock number of the active car
     * 
     * @return stock
     */
    public String getStock() {
        return stock;
    }

    /**
     * Returns the stock number file for the active car
     * 
     * @return stockfile
     */
    public File getStockFile() {
        return stockfile;
    }

    /**
     * Returns the current picture storage location
     * 
     * @return piclocation
     */
    public File getPictureLocation() {
        return picloc;
    }

    /**
     * When given the parameters listed below, returns a storage path for the
     * images with the proper appendages
     * 
     * @param wheels
     *            Whether the storage location is wheels (boolean)
     * @param make
     *            Make of the car
     * @param model
     *            Model of the car
     * @param stock
     *            Stock of the car
     * @return storage path for an image
     */
    // TODO: Make these file paths adjustable
    public File getStorageLocation(boolean wheels, String make, String model, String stock) {
        File ret;
        if (wheels) {
            ret = new File("/Users/sturtevantauto/Pictures/Car_Pictures/Wheels/" + make + "/" + model + "/" + stock);
        } else {
            ret = new File("/Users/sturtevantauto/Pictures/Car_Pictures/" + make + "/" + model + "/" + stock);
        }
        return ret;
    }

    /**
     * Returns the list of files of the active car
     * 
     * @return thelist
     */
    public File[] getList() {
        return list;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        if (connection == null)
            connection = DriverManager.getConnection(getDBUrl(), getSQLUsername(), getSQLPassword());
        return connection;
    }

    /**
     * Returns the model of the active car
     * 
     * @return model
     */
    public String getModel() {
        return model;
    }

}
