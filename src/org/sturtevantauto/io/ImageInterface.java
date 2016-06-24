package org.sturtevantauto.io;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageInterface {
	static boolean foundstock;
	static boolean imagetime;
	static int count;
	/**
	 * Lists all of the files in the given folder and stores them with CarDefinitions.setImageNames();
	 * @param file  Filepath to search
	 */
	public static void findFile(File file)
    {
		count = 0;
		foundstock = false;
		imagetime = false;
		CarDefinitions.setStock(null);
        File[] list = file.listFiles();
        if(list!=null)
        for (File filer : list)
        {
        if(imagetime == true)
        	{
        		if (filer.getName().contains("F") || filer.getName().contains("G0") || filer.getName().contains("E") || filer.getName().contains("G1"))
                {
        			imagetime = false;
                }
        		else
        		{
        		CarDefinitions.setImageNames(filer.getPath(), count);
        		count++;
        		}
        	}
        	if(foundstock == false)
        	{
            if (filer.getName().contains("F")
            		|| filer.getName().contains("G0")
            		|| filer.getName().contains("E")
            		|| filer.getName().contains("G1"))
            {
                CarDefinitions.setStock(filer.getName());
                count = 0;
                foundstock = true;
                imagetime = true;
                CarDefinitions.setStockFile(filer);
            }
        	}
        }
    }
	/**
	 * Counts the amount of pictures in the folder
	 * @param file
	 * @return int picturecount
	 */
	public static int countPictures(File file)
    {
        File[] list = file.listFiles();
        int pictures = 0;
        if(list!=null)
        for (File filer : list)
        {
            if (filer.getName().contains("F")
            		|| filer.getName().contains("G0")
            		|| filer.getName().contains("E")
            		|| filer.getName().contains("G1"))
            {
            	pictures++;
            }
        }
        return pictures;
    }
	/**
	 * Copies images from the sorting location to the sorted location when given the stock, model, and make
	 * @param stock
	 * @param model
	 * @param make
	 */
	public static void CopyFiles(String stock, String model, String make)
	{
		File mainpath = CarDefinitions.getStorageLocation(false, make, model, stock);
		String carpath = make + "_" + model + "_" + stock + "_";
		mainpath.mkdirs();
		File[] images = new File[count];
		File[] imagesend = new File[count];
		int i = 0;
		while(i < count)
		{
			if(CarDefinitions.getImageNames()[i] == null ||
					CarDefinitions.getImageNames()[i].contains("F")  ||
					CarDefinitions.getImageNames()[i].contains("G0") || 
					CarDefinitions.getImageNames()[i].contains("E1") ||
					CarDefinitions.getImageNames()[i].contains("G1"))
			{
				System.err.println("Image " + (i + 1) + " failed to move because it was a stock number picture somehow.");
			}
			else
			{
			images[i] = new File(CarDefinitions.getImageNames()[i]);
			if(CarDefinitions.getImageNames()[i].endsWith("_W.JPG"))
			{
			mainpath = CarDefinitions.getStorageLocation(true, make, model, stock);
			mainpath.mkdirs();
			}
			imagesend[i] = new File(mainpath + "/" + carpath + (i + 1) + ".jpg");
			if(!images[i].renameTo(imagesend[i]))
			{
				System.out.println("Image " + (i + 1) + "failed to move. Missing?");
			}
			}
			i++;
		}
	}
	/**
	 * Buffered image scaling for the GUI
	 * @param sbi
	 * @param imageType
	 * @param dWidth
	 * @param dHeight
	 * @param fWidth
	 * @param fHeight
	 * @return scaled image
	 */
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}

}
