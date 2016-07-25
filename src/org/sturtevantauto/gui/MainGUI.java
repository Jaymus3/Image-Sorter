package org.sturtevantauto.gui;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JMenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

public class MainGUI {

	public JFrame frmImageSorter;
	private JTextField modelField;
	private JTextField makeField;
	private JTextField stockField;
	int increment = 100;

	/**
	 * Create the application.
	 */
	public MainGUI() {
		CarDefinitions.setMake(null);
        ImageInterface.findFile(CarDefinitions.getPictureLocation(), false);
    if(CarDefinitions.getStock() == null)
    {
    	System.err.println("No cars found to sort!");
    }
    else
    	if(CarDefinitions.getPictureLocation().toString().endsWith("/SORT_ME"))
    	CarDefinitions.TrimStock(false);
    	else if (CarDefinitions.getPictureLocation().toString().endsWith("/SKIPPED"))
    	{
    	ImageInterface.findFile(CarDefinitions.getPictureLocation(), true);
    	CarDefinitions.TrimStock(true);
    	}
    	
    
    
    if(ImageInterface.countPictures(CarDefinitions.getPictureLocation()) != 0)
    	increment = 100 / ImageInterface.countPictures(CarDefinitions.getPictureLocation());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImageSorter = new JFrame();
		frmImageSorter.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frmImageSorter.setResizable(false);
		frmImageSorter.setTitle("Image Sorter");
		frmImageSorter.setBounds(100, 100, 1000, 622);
		frmImageSorter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImageSorter.getContentPane().setLayout(null);
		final JButton btnSort = new JButton("Sort");
		btnSort.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		btnSort.setEnabled(false);
		final JTextPane outputWindow = new JTextPane();
		outputWindow.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		outputWindow.setToolTipText("Displays output such as error or success messages");
		outputWindow.setEditable(false);
		outputWindow.setBounds(15, 141, 295, 172);
		frmImageSorter.getContentPane().add(outputWindow);
		if(CarDefinitions.getStock() == null)
		{
		outputWindow.setText("No cars were found in the sorting directory.  Perhaps you didn't add the stock number to the end of any of the files, or forgot to import the pictures?");
		}

		makeField = new JTextField();
		makeField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		makeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modelField.getText() != null)
				{
					try {
						CarDefinitions.setMake(makeField.getText());
						MakeModelInterface.WriteMakeModelIndex(CarDefinitions.getModel(), CarDefinitions.getMake());
						outputWindow.requestFocus();
						outputWindow.setText("Make/Model association set!  Click the sort button to sort this car.");
						btnSort.setEnabled(true);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
				outputWindow.setText("Please enter the model first, and I'll look up the make for you.");
			}
		});
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 545, 859, 27);
		progressBar.setVisible(false);
		frmImageSorter.getContentPane().add(progressBar);
		
		JLabel lblStock = new JLabel("Stock #:");
		lblStock.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblStock.setBounds(1, 11, 51, 16);
		frmImageSorter.getContentPane().add(lblStock);
		
		stockField = new JTextField();
		stockField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		stockField.setToolTipText("Stock # of the current scanned vehicle");
		stockField.setEditable(false);
		stockField.setBounds(55, 5, 255, 28);
		stockField.setColumns(10);
		frmImageSorter.getContentPane().add(stockField);
		
		modelField = new JTextField();
		modelField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		modelField.setToolTipText("Enter the model of the vehicle here");
		modelField.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CarDefinitions.setModel(modelField.getText());
				MakeModelInterface.foundmake = false;
		        try 
		        {
		        	MakeModelInterface.CheckMakeModelIndex(CarDefinitions.getModel());
				} 
		        catch (IOException e) 
		        	{
						e.printStackTrace();
		        	} 
		        catch (ClassNotFoundException e) 
		        	{
						e.printStackTrace();
		        	} 
		        catch (SQLException e) 
		        	{
						e.printStackTrace();
		        	}
		        if(!MakeModelInterface.foundmake)
		        {
		        	outputWindow.requestFocus();
		        	outputWindow.setText("Please enter the make as well.  I can't retrieve it.");
		        	makeField.setText("");
		        	makeField.setEditable(true);
		        }
		        else
		        {
		        outputWindow.requestFocus();
		        outputWindow.setText("Make found!  Click sort to sort this car!");
		        btnSort.setEnabled(true);
		        makeField.setText(CarDefinitions.getMake());
		        makeField.setEditable(false);
		        }
			}
		});
		
		JLabel lblmodel = new JLabel("\u0010Model:");
		lblmodel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblmodel.setBounds(6, 45, 46, 16);
		frmImageSorter.getContentPane().add(lblmodel);
		
		modelField.setBounds(55, 39, 255, 28);
		modelField.setColumns(10);
		frmImageSorter.getContentPane().add(modelField);
		
		JLabel lblNewLabel = new JLabel("Make:");
		lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblNewLabel.setBounds(15, 79, 37, 16);
		frmImageSorter.getContentPane().add(lblNewLabel);
		
		makeField.setToolTipText("Sometimes the make won't be found, and you'll have to enter it here");
		makeField.setBounds(55, 73, 255, 28);
		makeField.setEditable(false);
		makeField.setColumns(10);
		frmImageSorter.getContentPane().add(makeField);
		
		if(CarDefinitions.getStock() == null)
		modelField.setEditable(false);
		
		JLabel lblOutputWindow = new JLabel("Output window:");
		lblOutputWindow.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblOutputWindow.setBounds(15, 113, 117, 16);
		frmImageSorter.getContentPane().add(lblOutputWindow);
		
		final JLabel carPicture1 = new JLabel();
		carPicture1.setToolTipText("Double click on this to open the displayed image");
		carPicture1.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		loadMouseAdapter(carPicture1, 0);
		carPicture1.setBounds(322, 6, 330, 253);
		loadImage(carPicture1, 0);
		frmImageSorter.getContentPane().add(carPicture1);
		
		final JLabel carPicture2 = new JLabel();
		carPicture2.setToolTipText("Double click on this to open the displayed image");
		carPicture2.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		loadMouseAdapter(carPicture2, 1);
		carPicture2.setBounds(664, 6, 330, 253);
		loadImage(carPicture2, 1);
		frmImageSorter.getContentPane().add(carPicture2);
		
		final JLabel carPicture3 = new JLabel();
		carPicture3.setToolTipText("Double click on this to open the displayed image");
		carPicture3.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		loadMouseAdapter(carPicture3, 2);
		carPicture3.setBounds(322, 271, 330, 253);
		loadImage(carPicture3, 2);
		frmImageSorter.getContentPane().add(carPicture3);
		
		final JLabel carPicture4 = new JLabel();
		carPicture4.setToolTipText("Double click on this to open the displayed image");
		carPicture4.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		loadMouseAdapter(carPicture4, 3);
		carPicture4.setBounds(664, 271, 330, 253);
		loadImage(carPicture4, 3);
		frmImageSorter.getContentPane().add(carPicture4);
		
		/**
		 * Handles when the "Sort" button is clicked
		 */
		
		btnSort.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				progressBar.setVisible(true);
				btnSort.setEnabled(false);
				progressBar.setValue(progressBar.getValue() + increment);
				outputWindow.setText("");
	        	String[] options = new String[]{"Delete", "Continue"};
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
						outputWindow.setText(CarDefinitions.getMake() + " " + CarDefinitions.getModel() + " successfully sorted!");
						CarDefinitions.setMake(null);
						ImageInterface.findFile(CarDefinitions.getPictureLocation(), false);
			        	loadImage(carPicture1, 0);
			        	loadImage(carPicture2, 1);
			        	loadImage(carPicture3, 2);
			        	loadImage(carPicture4, 3);
						CarDefinitions.TrimStock(false);
						stockField.setText(CarDefinitions.getStock());
					}
					else
						stockField.setText("NO CARS");
			        
					makeField.setText("");
					makeField.setEditable(false);
					modelField.setText("");
					
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
		
		final JButton skipCarButton = new JButton("Skip this car (copies it to skipped car folder)");
		skipCarButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				progressBar.setValue(progressBar.getValue() + increment);
				CarDefinitions.getStockFile().renameTo(new File("/Users/sturtevantauto/Pictures/Car_Pictures/SKIPPED/" + CarDefinitions.getStock() + "__.jpg"));
				for(int i = 0; CarDefinitions.getImageNames().length > i; i++)
				{
					if(CarDefinitions.getImageNames()[i] != null)
					{
					File carfile = new File(CarDefinitions.getImageNames()[i]);
					carfile.renameTo(new File("/Users/sturtevantauto/Pictures/Car_Pictures/SKIPPED/" + CarDefinitions.getStock() + "_" + i + ".jpg"));
					}
				}
				outputWindow.setText(CarDefinitions.getStock() + " successfully moved out for later sorting!");
				CarDefinitions.setMake(null);
		        ImageInterface.findFile(CarDefinitions.getPictureLocation(), false);
		        if(CarDefinitions.getStock() != null)
				{
					CarDefinitions.TrimStock(false);
					stockField.setText(CarDefinitions.getStock());
				}
					else
					stockField.setText("NO CARS");
				makeField.setText("");
				makeField.setEditable(false);
				modelField.setText("");
				
			}
		});
		skipCarButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		skipCarButton.setBounds(15, 323, 295, 36);
		frmImageSorter.getContentPane().add(skipCarButton);
		
		JLabel label = new JLabel("");
		label.setBounds(436, 547, 61, 16);
		frmImageSorter.getContentPane().add(label);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		frmImageSorter.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		menuBar.add(menuFile);
		
		JMenuItem menuItem1 = new JMenuItem("About");
		menuItem1.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		menuFile.add(menuItem1);
		
		JMenuItem menuItem2 = new JMenuItem("Change storage location");
		menuItem2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(frmImageSorter);
				if (result == JFileChooser.APPROVE_OPTION) {
					String[] options = new String[]{"Cancel", "Continue"};
					int choice = JOptionPane.showOptionDialog(null, "This will restart the program, resulting in loss of progress beyond the last time you clicked the sort button.  Would you like to proceed?",
							"Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					if(choice == 1)
					{
					File selectedFile = fileChooser.getSelectedFile();
					CarDefinitions.setPictureLocation(selectedFile.toString() + "/");
					restart();
					skipCarButton.setVisible(false);
					
					}
				}
			}
		});
		menuItem2.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		menuFile.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem("Restart");
		menuItem3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				restart();
			}
		});
		menuItem3.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		menuFile.add(menuItem3);
		
		JCheckBoxMenuItem menuItem4 = new JCheckBoxMenuItem("Check!");
		menuFile.add(menuItem4);
		if(CarDefinitions.getStock() != null)
		stockField.setText(CarDefinitions.getStock());
		else
		{
		carPicture1.setText("                      No image found!");
		carPicture1.setIcon(null);
		carPicture2.setText("                      No image found!");
		carPicture2.setIcon(null);
		carPicture3.setText("                      No image found!");
		carPicture3.setIcon(null);
		carPicture4.setText("                      No image found!");
		carPicture4.setIcon(null);
		stockField.setText("NO CARS");
		frmImageSorter.setLocationRelativeTo(null);
		}
	}
	private void loadImage(JLabel carPicture, int j)
	{
		if(CarDefinitions.getImageNames()[j] != null)
		{
			BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File(CarDefinitions.getImageNames()[j]));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(carPicture.getWidth(), carPicture.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		carPicture.setIcon(imageIcon);
		}
		else
		{
			carPicture.setText("                      No image found!");
		}
	}
	private void loadMouseAdapter(JLabel carPicture, final int j)
	{
		carPicture.addMouseListener(new MouseAdapter() 
		{
			boolean OneClick;

			@Override
			public void mouseClicked(MouseEvent mouseEvent) 
		{
			if (OneClick) 
			{
				if(CarDefinitions.getImageNames()[j] != null)
					try {
						Desktop.getDesktop().open(new File(CarDefinitions.getImageNames()[j]));
					} catch (IOException e) {
						e.printStackTrace();
					}
				  OneClick = false;
			}
			else 
			{
				  OneClick = true;
				  Timer t = new Timer("clickTimer", false);
				  t.schedule(new TimerTask() 
				  	{
					  @Override
					  public void run()
					  {
						  OneClick = false;
					  }
				  	},500);
			}
		}
		});
	}
	private void restart()
	{
		frmImageSorter.dispose();
		new MainGUI();
		initialize();
		frmImageSorter.setVisible(true);
	}
}
