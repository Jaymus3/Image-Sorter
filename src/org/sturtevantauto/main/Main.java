package org.sturtevantauto.main;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.sturtevantauto.gui.MainGUI;
import org.sturtevantauto.io.CarDefinitions;
import org.sturtevantauto.io.ImageInterface;
import org.sturtevantauto.io.Logger;
import org.sturtevantauto.io.MakeModelInterface;

@SuppressWarnings("unused")
public class Main {
	
    public static void main(String[] args) throws IOException, AWTException, ClassNotFoundException, SQLException 
    {
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainGUI window = new MainGUI();
					//window.frmImageSorter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        while(true)
        {
        	CarDefinitions.setMake(null);
            ImageInterface.findFile(CarDefinitions.getPictureLocation());
        if(CarDefinitions.getStock() == null)
        {
        	System.err.println("All cars sorted! Terminating.");
        	System.exit(0);
        }
        
        CarDefinitions.TrimStock();
        if(Logger.CheckIfCarIndexed(CarDefinitions.getStock()))
        {
        	String[] options = new String[2];
        	options[0] = "Delete";
        	options[1] = "Continue";
        	//JOptionPane.showOptionDialog(null, "Would you like to delete the existing files \n for this car, or just continue anyways?",
        	//		"Car already sorted!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        	String delorcont;
        	System.out.println("Would you like to delete the existing files for this car, " +
        			"or just continue anyways?");
        	System.out.println("(del/cont)");
        	delorcont = scan.next();
        	if(delorcont.equals("del") || delorcont.equals("DEL"))
        	{
        		System.out.println("Deleting images...");
        		CarDefinitions.getStockFile().delete();
        		File[] images = new File[5];
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
        CarDefinitions.setModel(scan.next());
        //System.out.println("Checking make/model index...");
        MakeModelInterface.foundmake = false;
        MakeModelInterface.CheckMakeModelIndex(CarDefinitions.getModel());
        if(!MakeModelInterface.foundmake)
        {
        	System.out.println("I'm gonna need the make as well, just this once!");
        	CarDefinitions.setMake(scan.next());
        	MakeModelInterface.WriteMakeModelIndex(CarDefinitions.getModel(), CarDefinitions.getMake());
        }
        //System.out.println("Copying files/making directories...");
        ImageInterface.CopyFiles(CarDefinitions.getStock(), CarDefinitions.getModel(), CarDefinitions.getMake());
        //System.out.println("Files moved.");
        CarDefinitions.getStockFile().delete();
        //System.out.println("Logging car in index of done cars...");
        Logger.LogCar(CarDefinitions.getStock());
    	}
    }
    	}

