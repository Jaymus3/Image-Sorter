package org.sturtevantauto.io;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageInterface {
	static boolean foundstock;
	static boolean imagetime;
	static int count;
	
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
        		if (filer.getName().contains("F") || filer.getName().contains("G0"))
                {
        			//System.out.println("Stock2");
        			imagetime = false;
                }
        		else
        		{
        		//System.out.println("Setting image " + (count + 1) + " " + filer.getPath());
        		CarDefinitions.setImageNames(filer.getPath(), count);
        		count++;
        		}
        	}
        	if(foundstock == false)
        	{
            if (filer.getName().contains("F") || filer.getName().contains("G0"))
            {
            	//System.out.println("Stock");
                CarDefinitions.setStock(filer.getName());
                count = 0;
                foundstock = true;
                imagetime = true;
                CarDefinitions.setStockFile(filer);
            }
        	}
        }
    }
	
	public static int countPictures(File file)
    {
        File[] list = file.listFiles();
        int pictures = 0;
        if(list!=null)
        for (File filer : list)
        {
            if (filer.getName().contains("F") || filer.getName().contains("G0"))
            {
            	pictures++;
            }
        }
        return pictures;
    }
	
	public static void CopyFiles(String stock, String model, String make)
	{
		File mainpath = new File("/Users/sturtevantauto/Pictures/Car_Pictures/" 
				+ make + "/" + model + "/" + stock);
		String carpath = make + "_" + model + "_" + stock + "_";
		mainpath.mkdirs();
		File[] images = new File[count];
		File[] imagesend = new File[count];
		int i = 0;
		while(i < count)
		{
			if(CarDefinitions.getImageNames()[i] == null || CarDefinitions.getImageNames()[i].contains("F") || CarDefinitions.getImageNames()[i].contains("G0"))
			{
				System.err.println("Image " + (i + 1) + " failed to move. Missing?");
			}
			else
			{
			images[i] = new File(CarDefinitions.getImageNames()[i]);
			imagesend[i] = new File(mainpath + "/" + carpath + (i + 1) + ".jpg");
			if(!images[i].renameTo(imagesend[i]))
			{
				System.out.println("Image " + i + "failed to move. Missing?");
			}
			}
			i++;
		}
	}
	
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
