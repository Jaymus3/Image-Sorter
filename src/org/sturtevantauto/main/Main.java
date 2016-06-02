package org.sturtevantauto.main;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.io.IOException;
import org.sturtevantauto.gui.MainGUI;
import org.sturtevantauto.io.CarDefinitions;
import org.sturtevantauto.io.ImageInterface;

public class Main {
	
    public static void main(String[] args) throws IOException, AWTException 
    {
    	CarDefinitions.setMake(null);
        ImageInterface.findFile(CarDefinitions.getPictureLocation());
        //Scanner scan = new Scanner(System.in);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmImageSorter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        if(CarDefinitions.getStock() == null)
        {
        	/*
        	Robot r = new Robot();
        	r.delay(2000);
        	r.keyPress(KeyEvent.VK_ENTER);
        	r.keyRelease(KeyEvent.VK_ENTER);
        	r.delay(3500);
        	System.out.println(MouseInfo.getPointerInfo().getLocation());
        	r.mouseMove(461, 677);
        	r.delay(100);
        	r.mousePress(InputEvent.BUTTON1_MASK);
        	r.mouseRelease(InputEvent.BUTTON1_MASK);
        	r.delay(300);
        	r.keyPress(KeyEvent.VK_DOWN);
        	r.keyRelease(KeyEvent.VK_DOWN);
        	r.delay(100);
        	r.keyPress(KeyEvent.VK_DOWN);
        	r.keyRelease(KeyEvent.VK_DOWN);
        	r.delay(100);
        	r.mouseMove(645, 493);
        	r.delay(100);
        	r.mousePress(InputEvent.BUTTON1_MASK);
        	r.mouseRelease(InputEvent.BUTTON1_MASK);
        	r.delay(500);
        	r.keyPress(KeyEvent.VK_T);
        	r.keyRelease(KeyEvent.VK_T);
        	r.delay(100);
        	r.keyPress(KeyEvent.VK_E);
        	r.keyRelease(KeyEvent.VK_E);
        	r.delay(100);
        	r.keyPress(KeyEvent.VK_S);
        	r.keyRelease(KeyEvent.VK_S);
        	r.delay(100);
        	r.keyPress(KeyEvent.VK_T);
        	r.keyRelease(KeyEvent.VK_T);
        	r.delay(100);
        	r.keyPress(KeyEvent.VK_ENTER);
        	r.keyRelease(KeyEvent.VK_ENTER);
        	System.out.println(MouseInfo.getPointerInfo().getLocation());
        	*/
        	System.err.println("All cars sorted! Terminating.");
        	System.exit(0);
        }
        /*
        //CarDefinitions.TrimStock();
        if(Logger.CheckIfCarIndexed(CarDefinitions.getStock()))
        {
        	String[] options = new String[2];
        	options[0] = "Delete";
        	options[1] = "Continue";
        	JOptionPane.showOptionDialog(null, "Would you like to delete the existing files \n for this car, or just continue anyways?",
        			"Car already sorted!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
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
    	*/
    }
    }

