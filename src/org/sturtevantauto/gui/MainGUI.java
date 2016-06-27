package org.sturtevantauto.gui;

import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

import org.sturtevantauto.io.CarDefinitions;
import org.sturtevantauto.io.ImageInterface;
import org.sturtevantauto.io.Logger;
import org.sturtevantauto.io.MakeModelInterface;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MainGUI {

	public JFrame frmImageSorter;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	int increment = 100;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MainGUI() {
		CarDefinitions.setMake(null);
        ImageInterface.findFile(CarDefinitions.getPictureLocation());
    if(CarDefinitions.getStock() == null)
    {
    	System.err.println("No cars found to sort!");
    }
    else
    	CarDefinitions.TrimStock();
    
    
    if(ImageInterface.countPictures(CarDefinitions.getPictureLocation()) != 0)
    	increment = 100 / ImageInterface.countPictures(CarDefinitions.getPictureLocation());
    
    
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImageSorter = new JFrame();
		frmImageSorter.setResizable(false);
		frmImageSorter.setTitle("Image Sorter");
		frmImageSorter.setBounds(100, 100, 1000, 600);
		frmImageSorter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImageSorter.getContentPane().setLayout(null);
		
		
		final JTextPane txtpnTestTestTest = new JTextPane();
		txtpnTestTestTest.setToolTipText("Displays output such as error or success messages");
		txtpnTestTestTest.setEditable(false);
		txtpnTestTestTest.setBounds(15, 141, 295, 172);
		frmImageSorter.getContentPane().add(txtpnTestTestTest);
		if(CarDefinitions.getStock() == null)
			txtpnTestTestTest.setText("No cars were found in the sorting directory.  Perhaps you didn't add the stock number to the end of any of the files, or forgot to import the pictures?");
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText() != null)
				{
					try {
						CarDefinitions.setMake(textField_1.getText());
						MakeModelInterface.WriteMakeModelIndex(CarDefinitions.getModel(), CarDefinitions.getMake());
						txtpnTestTestTest.setText("Make/Model association set!  Click the sort button to sort this car.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
				txtpnTestTestTest.setText("Please enter the model first, and I'll look up the make for you.");
			}
		});
		textField_1.setToolTipText("Sometimes the make won't be found, and you'll have to enter it here");
		textField_1.setBounds(55, 73, 255, 28);
		textField_1.setEditable(false);
		frmImageSorter.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setToolTipText("Enter the model of the vehicle here");
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CarDefinitions.setModel(textField.getText());
				MakeModelInterface.foundmake = false;
		        try {
					MakeModelInterface.CheckMakeModelIndex(CarDefinitions.getModel());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        if(!MakeModelInterface.foundmake)
		        {
		        	txtpnTestTestTest.setText("Please enter the make as well.  I can't retrieve it.");
		        	textField_1.setText("");
		        	textField_1.setEditable(true);
		        }
		        else
		        {
		        txtpnTestTestTest.setText("Make found!  Click sort to sort this car!");
		        textField_1.setText(CarDefinitions.getMake());
		        textField_1.setEditable(false);
		        }
			}
		});
		
		textField.setBounds(55, 39, 255, 28);
		frmImageSorter.getContentPane().add(textField);
		if(CarDefinitions.getStock() == null)
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Make:");
		lblNewLabel.setBounds(15, 79, 37, 16);
		frmImageSorter.getContentPane().add(lblNewLabel);
		
		
		final JLabel jLabelObject = new JLabel();
		jLabelObject.setBounds(664, 6, 330, 253);
		if(CarDefinitions.getImageNames()[0] != null)
		{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(CarDefinitions.getImageNames()[0]));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(jLabelObject.getWidth(), jLabelObject.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		jLabelObject.setIcon(imageIcon);
		}
		else
		{
			jLabelObject.setText("                      No image found!");
		}
		frmImageSorter.getContentPane().add(jLabelObject);
		final JLabel jLabelObject1 = new JLabel();
		jLabelObject1.setBounds(322, 6, 330, 253);
		
		if(CarDefinitions.getImageNames()[1] != null)
		{
		BufferedImage img1 = null;
		try {
		    img1 = ImageIO.read(new File(CarDefinitions.getImageNames()[1]));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg1 = img1.getScaledInstance(jLabelObject1.getWidth(), jLabelObject1.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon1 = new ImageIcon(dimg1);
		jLabelObject1.setIcon(imageIcon1);
		}
		else
		{
			jLabelObject1.setText("                      No image found!");
		}
		frmImageSorter.getContentPane().add(jLabelObject1);
		final JLabel jLabelObject2 = new JLabel();
		jLabelObject2.setBounds(322, 271, 330, 253);
		if(CarDefinitions.getImageNames()[2] != null)
		{
		BufferedImage img2 = null;
		try {
		    img2 = ImageIO.read(new File(CarDefinitions.getImageNames()[2]));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg2 = img2.getScaledInstance(jLabelObject2.getWidth(), jLabelObject2.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(dimg2);
		jLabelObject2.setIcon(imageIcon2);
		}
		else
		{
			jLabelObject2.setText("                      No image found!");
		}
		frmImageSorter.getContentPane().add(jLabelObject2);
		final JLabel jLabelObject3 = new JLabel();
		jLabelObject3.setBounds(664, 271, 330, 253);
		if(CarDefinitions.getImageNames()[3] != null)
		{
		BufferedImage img3 = null;
		try {
		    img3 = ImageIO.read(new File(CarDefinitions.getImageNames()[3]));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg3 = img3.getScaledInstance(jLabelObject3.getWidth(), jLabelObject3.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon3 = new ImageIcon(dimg3);
		jLabelObject3.setIcon(imageIcon3);
		}
		else
		{
			jLabelObject3.setText("                      No image found!");
		}
		frmImageSorter.getContentPane().add(jLabelObject3);
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 536, 859, 27);
		progressBar.setVisible(false);
		frmImageSorter.getContentPane().add(progressBar);
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				progressBar.setVisible(true);
				progressBar.setValue(progressBar.getValue() + increment);
				txtpnTestTestTest.setText("");
	        	String[] options = new String[2];
	        	options[0] = "Delete";
	        	options[1] = "Continue";
	        	try {
					if(Logger.CheckIfCarIndexed(CarDefinitions.getStock()))
					{
					int choice = JOptionPane.showOptionDialog(null, "Would you like to delete the existing files for this car, or just continue anyways?",
							"Car already sorted!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					if(choice == 0)
					{
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
		        		return;
					}
					if(choice == 1)
					{
					//This is where the continue option is handled
					}
					}
					if(CarDefinitions.getStock() != null)
					{
					ImageInterface.CopyFiles(CarDefinitions.getStock(), CarDefinitions.getModel(), CarDefinitions.getMake());
					CarDefinitions.getStockFile().delete();
					Logger.LogCar(CarDefinitions.getStock());
					txtpnTestTestTest.setText(CarDefinitions.getMake() + " " + CarDefinitions.getModel() + " successfully sorted!");
					CarDefinitions.setMake(null);
			        ImageInterface.findFile(CarDefinitions.getPictureLocation());
					}
			        
			        if(CarDefinitions.getStock() != null)
			        {
			        if(CarDefinitions.getImageNames()[0] != null)
			        {
			        BufferedImage img = null;
					try {
					    img = ImageIO.read(new File(CarDefinitions.getImageNames()[0]));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					Image dimg = img.getScaledInstance(jLabelObject.getWidth(), jLabelObject.getHeight(),
					        Image.SCALE_SMOOTH);
					ImageIcon imageIcon = new ImageIcon(dimg);
					jLabelObject.setIcon(imageIcon);
			        }
			        else
					{
						jLabelObject.setText("                      No image found!");
					}
					
			        if(CarDefinitions.getImageNames()[1] != null)
			        {
					BufferedImage img1 = null;
					try {
					    img1 = ImageIO.read(new File(CarDefinitions.getImageNames()[1]));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					Image dimg1 = img1.getScaledInstance(jLabelObject1.getWidth(), jLabelObject1.getHeight(),
					        Image.SCALE_SMOOTH);
					ImageIcon imageIcon1 = new ImageIcon(dimg1);
					jLabelObject1.setIcon(imageIcon1);
			        }
			        else
					{
						jLabelObject1.setText("                      No image found!");
					}
					
					if(CarDefinitions.getImageNames()[2] != null)
			        {
					BufferedImage img2 = null;
					try {
					    img2 = ImageIO.read(new File(CarDefinitions.getImageNames()[2]));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					Image dimg2 = img2.getScaledInstance(jLabelObject2.getWidth(), jLabelObject2.getHeight(),
					        Image.SCALE_SMOOTH);
					ImageIcon imageIcon2 = new ImageIcon(dimg2);
					jLabelObject2.setIcon(imageIcon2);
					frmImageSorter.getContentPane().add(jLabelObject2);
			        }
					else
					{
						jLabelObject2.setText("                      No image found!");
					}
					
					if(CarDefinitions.getImageNames()[3] != null)
			        {
					BufferedImage img3 = null;
					try {
					    img3 = ImageIO.read(new File(CarDefinitions.getImageNames()[3]));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					Image dimg3 = img3.getScaledInstance(jLabelObject3.getWidth(), jLabelObject3.getHeight(),
					        Image.SCALE_SMOOTH);
					ImageIcon imageIcon3 = new ImageIcon(dimg3);
					jLabelObject3.setIcon(imageIcon3);
			        }
					else
					{
						jLabelObject3.setText("                      No image found!");
					}
			        }
			        else
			        {
			        	jLabelObject.setText("                      No image found!");
			        	jLabelObject1.setText("                      No image found!");
			        	jLabelObject2.setText("                      No image found!");
			        	jLabelObject3.setText("                      No image found!");
			        }
					if(CarDefinitions.getStock() != null)
					{
						CarDefinitions.TrimStock();
						textField_2.setText(CarDefinitions.getStock());
					}
						else
						textField_2.setText("NO CARS");
					textField_1.setText("");
					textField_1.setEditable(false);
					textField.setText("");
					
					
			        
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSort.setToolTipText("Clicking this button will sort the current car and proceed");
		btnSort.setBounds(877, 536, 117, 36);
		frmImageSorter.getContentPane().add(btnSort);
		
		JLabel lblmodel = new JLabel("\u0010Model:");
		lblmodel.setBounds(6, 45, 46, 16);
		frmImageSorter.getContentPane().add(lblmodel);
		
		JLabel lblStock = new JLabel("Stock #:");
		lblStock.setBounds(1, 11, 51, 16);
		frmImageSorter.getContentPane().add(lblStock);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Stock # of the current scanned vehicle");
		textField_2.setEditable(false);
		textField_2.setBounds(55, 5, 255, 28);
		frmImageSorter.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		if(CarDefinitions.getStock() != null)
		textField_2.setText(CarDefinitions.getStock());
		else
		textField_2.setText("NO CARS");
		
		
		
		JLabel lblOutputWindow = new JLabel("Output window:");
		lblOutputWindow.setBounds(15, 113, 117, 16);
		frmImageSorter.getContentPane().add(lblOutputWindow);
	}
}
