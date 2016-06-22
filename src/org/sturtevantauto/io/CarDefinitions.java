package org.sturtevantauto.io;

import java.io.File;

public class CarDefinitions {
	static String make;
	static String model;
	static File picloc = new File("/Users/sturtevantauto/Pictures/Car_Pictures/SORT_ME/");
	static File[] list = picloc.listFiles();
	static String stock;
	static File stockfile;
	static String[] imagenames = new String[50];
	
	
	public static void setPictureLocation(String folderpath)
	{
		picloc = new File(folderpath);
	}
	public static void setModel(String models)
	{
		model = models;
	}
	public static void setMake(String makes)
	{
		make = makes;
	}
	public static void setStockFile(File stock)
	{
		stockfile = stock;
	}
	public static void setList(File[] thelist)
	{
		list = thelist;
	}
	public static void setImageNames(String imagename, int i)
	{
		imagenames[i] = imagename;
	}
	public static void TrimStock()
	{
		stock = stock.replace(".JPG", "");
        stock = stock.replace(".jpg", "");
        stock = stock.substring(9);
	}
	public static String[] getImageNames()
	{
		return imagenames;
	}
	public static String getMake()
	{
		return make;
	}
	public static void setStock(String string)
	{
		stock = string;
	}
	public static String getStock()
	{
		return stock;
	}
	public static File getStockFile()
	{
		return stockfile;
	}
	public static File getPictureLocation()
	{
		return picloc;
	}
	
	/**
	 * 
	 * @param wheels Whether the storage location is wheels (boolean)
	 * @param make Make of the car
	 * @param model Model of the car
	 * @param stock Stock of the car
	 * @return storage path for an image
	 */
	public static File getStorageLocation(boolean wheels, String make, String model, String stock)
	{
		if(wheels)
		{
			File ret = new File("/Users/sturtevantauto/Pictures/Car_Pictures/Wheels/" + make + "/" + model + "/" + stock);
			return ret;
		}
		else
		{
			File ret = new File("/Users/sturtevantauto/Pictures/Car_Pictures/" + make + "/" + model + "/" + stock);
			return ret;
		}
		
	}
	public static File[] getList()
	{
		return list;
	}
	public static String getModel() {
		return model;
	}

}
