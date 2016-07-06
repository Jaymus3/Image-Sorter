package org.sturtevantauto.main;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.sturtevantauto.gui.MainGUI;
import org.sturtevantauto.io.CarDefinitions;
import org.sturtevantauto.io.ImageInterface;
import org.sturtevantauto.io.Logger;
import org.sturtevantauto.io.MakeModelInterface;

@SuppressWarnings("unused")
public class Main {
	/**
	 * Runs at the start of the program and handles all of the scanner functions and eventually initializes the GUI instead.
	 * @param args
	 * @throws IOException
	 * @throws AWTException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
    public static void main(String[] args) throws IOException, AWTException, ClassNotFoundException, SQLException 
    {
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.setProperty("apple.laf.useScreenMenuBar", "true");
	                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Image Sorter");
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainGUI window = new MainGUI();  //GUI Initializer here.  GUI is still in a usable state so I've rigged it up
					window.frmImageSorter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        /*
        while(true)  //Starts infinite loop that's terminated when all cars are sorted
        {
        	CarDefinitions.setMake(null);
            ImageInterface.findFile(CarDefinitions.getPictureLocation());
        if(CarDefinitions.getStock() == null)  //Kills the program loop.
        {
        	System.err.println("All cars sorted! Terminating.");
        	System.exit(0);
        }
        
        CarDefinitions.TrimStock(); //Trims the image name down so only the stock number is left.
        if(Logger.CheckIfCarIndexed(CarDefinitions.getStock()))  //Checks to ensure the car isn't already indexed
        {
        	String[] options = new String[2];
        	options[0] = "Delete";
        	options[1] = "Continue";
        	String delorcont;
        	System.out.println("Stock number for this car is:" + CarDefinitions.getStock());
        	System.out.println("Would you like to delete the existing files for this car, " +
        			"or just continue anyways?");
        	System.out.println("(del/cont)");
        	delorcont = scan.next();
        	if(delorcont.equals("del") || delorcont.equals("DEL"))
        	{
        		System.out.println("Deleting images...");
        		CarDefinitions.getStockFile().delete();
        		File[] images = new File[CarDefinitions.getImageNames().length];
        		int i = 0;
        		while(i < CarDefinitions.getImageNames().length)
        		{
        			if(CarDefinitions.getImageNames()[i] != null)
        			{
        			images[i] = new File(CarDefinitions.getImageNames()[i]);
        			images[i].delete();
        			}
        			i++;
        		}
        		continue;
        	}
        }
        System.err.println("Car found! Stock #: " + CarDefinitions.getStock());
        System.out.println("Enter the model of the car:");
        CarDefinitions.setModel(scan.next());  //Waits for model of car to be entered
        MakeModelInterface.foundmake = false;
        MakeModelInterface.CheckMakeModelIndex(CarDefinitions.getModel());  //Checks if the make is currently indexed
        if(!MakeModelInterface.foundmake)
        {
        	System.out.println("I'm gonna need the make as well, just this once!");
        	CarDefinitions.setMake(scan.next());
        	MakeModelInterface.WriteMakeModelIndex(CarDefinitions.getModel(), CarDefinitions.getMake());  //Writes the newly typed make to the index
        }
        ImageInterface.CopyFiles(CarDefinitions.getStock(), CarDefinitions.getModel(), CarDefinitions.getMake());  //Copies files to the correct folder
        CarDefinitions.getStockFile().delete();  //Deletes stock picture, since it was just to index the stock number
        Logger.LogCar(CarDefinitions.getStock());  //Logs car so we won't index it again
    	}
    	*/
    }
}

