package org.sturtevantauto.gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

import org.sturtevantauto.io.CarDefinitions;
import org.sturtevantauto.io.ImageInterface;
import org.sturtevantauto.io.Logger;
import org.sturtevantauto.io.MakeModelInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MainGUI {

	public JFrame frmImageSorter;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	final int increment = 100 / ImageInterface.countPictures(CarDefinitions.getPictureLocation());

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//CarDefinitions.TrimStock();
		frmImageSorter = new JFrame();
		frmImageSorter.setResizable(false);
		frmImageSorter.setTitle("Image Sorter");
		frmImageSorter.setBounds(100, 100, 411, 300);
		frmImageSorter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImageSorter.getContentPane().setLayout(null);
		
		
		final JTextPane txtpnTestTestTest = new JTextPane();
		txtpnTestTestTest.setToolTipText("Displays output such as error or success messages");
		txtpnTestTestTest.setEditable(false);
		txtpnTestTestTest.setBounds(6, 138, 183, 70);
		frmImageSorter.getContentPane().add(txtpnTestTestTest);
		
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				txtpnTestTestTest.setText("Please enter the model first, and I'll look up the make for you.");
			}
		});
		textField_1.setToolTipText("Sometimes the make won't be found, and you'll have to enter it here");
		textField_1.setBounds(55, 73, 134, 28);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if(!MakeModelInterface.foundmake)
		        {
		        	txtpnTestTestTest.setText("Please enter the make as well.  I can't retrieve it.");
		        	textField_1.setText("");
		        }
		        else
		        {
		        textField_1.setText(CarDefinitions.getMake());
		        textField_1.setEditable(false);
		        }
			}
		});
		
		textField.setBounds(55, 39, 134, 28);
		frmImageSorter.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Make:");
		lblNewLabel.setBounds(15, 79, 37, 16);
		frmImageSorter.getContentPane().add(lblNewLabel);
		
		if(CarDefinitions.getImageNames()[0] != null)
		{
		ImagePanel panel = new ImagePanel(CarDefinitions.getImageNames()[0]);
		panel.setBounds(201, 6, 95, 95);
		frmImageSorter.getContentPane().add(panel);
		}
		else
		{
		JPanel panel = new JPanel();
		panel.setBounds(201, 6, 95, 95);
		frmImageSorter.getContentPane().add(panel);
		}
		
		if(CarDefinitions.getImageNames()[1] != null)
		{
		ImagePanel panel_1 = new ImagePanel(CarDefinitions.getImageNames()[1]);
		panel_1.setBounds(310, 6, 95, 95);
		frmImageSorter.getContentPane().add(panel_1);
		}
		else
		{
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(310, 6, 95, 95);
		frmImageSorter.getContentPane().add(panel_1);
		}
		
		if(CarDefinitions.getImageNames()[2] != null)
		{
		ImagePanel panel_2 = new ImagePanel(CarDefinitions.getImageNames()[2]);
		panel_2.setBounds(201, 113, 95, 95);
		frmImageSorter.getContentPane().add(panel_2);
		}
		else
		{
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(201, 113, 95, 95);
		frmImageSorter.getContentPane().add(panel_2);
		}
		
		if(CarDefinitions.getImageNames()[3] != null)
		{
		ImagePanel panel_3 = new ImagePanel(CarDefinitions.getImageNames()[3]);
		panel_3.setBounds(310, 113, 95, 95);
		frmImageSorter.getContentPane().add(panel_3);
		}
		else
		{
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(310, 113, 95, 95);
		frmImageSorter.getContentPane().add(panel_3);
		}
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 221, 399, 20);
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
					ImageInterface.CopyFiles(CarDefinitions.getStock(), CarDefinitions.getModel(), CarDefinitions.getMake());
					CarDefinitions.getStockFile().delete();
					Logger.LogCar(CarDefinitions.getStock());
					CarDefinitions.setMake(null);
					txtpnTestTestTest.setText(CarDefinitions.getMake() + " " + CarDefinitions.getModel() + " successfully sorted!");
			        ImageInterface.findFile(CarDefinitions.getPictureLocation());
					initialize();
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSort.setToolTipText("Clicking this button will sort the current car and proceed");
		btnSort.setBounds(288, 243, 117, 29);
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
		textField_2.setBounds(55, 5, 134, 28);
		frmImageSorter.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		if(CarDefinitions.getStock() != null)
		textField_2.setText(CarDefinitions.getStock());
		else
		textField_2.setText("NO_CAR");
		
		
		
		JLabel lblOutputWindow = new JLabel("Output window:");
		lblOutputWindow.setBounds(6, 113, 117, 16);
		frmImageSorter.getContentPane().add(lblOutputWindow);
	}
}
