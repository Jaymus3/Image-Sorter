package org.sturtevantauto.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginWindow {

	private JFrame frmLoginWindow;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306?autoReconnect=true&useSSL=false";
	private static String dbUsername = "imagesorter";
	private static String dbPassword = "4vSmbst4Q#uhL#3%";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Sturtevant Auto Tools");
					System.setProperty("apple.laf.useScreenMenuBar", "true");
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					LoginWindow window = new LoginWindow();
					window.frmLoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginWindow = new JFrame();
		frmLoginWindow.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frmLoginWindow.setTitle("Sturtevant Auto Tools");
		frmLoginWindow.setResizable(false);
		frmLoginWindow.setBounds(100, 100, 349, 350);
		frmLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginWindow.getContentPane().setLayout(null);
		
		final JLabel errorLabel = new JLabel("Invalid username/password");
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
		errorLabel.setBounds(44, 174, 287, 16);
		errorLabel.setVisible(false);
		frmLoginWindow.getContentPane().add(errorLabel);
		
		JLabel lblLogo = new JLabel("Logo failed to load for some reason :(");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblLogo.setBounds(6, 6, 337, 170);
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File("resources/img/logo-transparent.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblLogo.setIcon(imageIcon);
		frmLoginWindow.getContentPane().add(lblLogo);
		
		usernameField = new JTextField();
		usernameField.setBounds(41, 188, 290, 35);
		TextPrompt usernamePrompt = new TextPrompt("Username", usernameField);
		usernamePrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		usernamePrompt.setHorizontalAlignment(SwingConstants.LEFT);
		usernamePrompt.setForeground(Color.GRAY);
		frmLoginWindow.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(41, 226, 290, 35);
		TextPrompt passwordPrompt = new TextPrompt("Password", passwordField);
		passwordPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		passwordPrompt.setHorizontalAlignment(SwingConstants.LEFT);
		passwordPrompt.setForeground(Color.GRAY);
		frmLoginWindow.getContentPane().add(passwordField);
		JButton loginButton = new JButton("Log In");
		loginButton.setForeground(new Color(128, 0, 128));
		loginButton.setBackground(new Color(128, 0, 128));
		loginButton.setOpaque(false);
		loginButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					foundausername = false;
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				    Statement statement = connection.createStatement();
					ResultSet use = statement.executeQuery("USE car_parts");
					String username = usernameField.getText();
					char[] password = passwordField.getPassword();
					ResultSet rs = statement.executeQuery("SELECT * FROM Account_Index where Username='" + username + "' and Password='" + new String(password) + "'");
					use.close();
					rs.close();
					statement.close();
					connection.close();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//TODO:  Add login functionality
			}
		});
		
		JLabel usernameLabel = new JLabel("New label");
		usernameLabel.setBounds(16, 192, 26, 26);
		BufferedImage img2 = null;
		try 
		{
		    img2 = ImageIO.read(new File("resources/img/username-logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		BufferedImage img3 = null;
		try 
		{
		    img3 = ImageIO.read(new File("resources/img/password-logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg2 = img2.getScaledInstance(usernameLabel.getWidth(), usernameLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(dimg2);
		usernameLabel.setIcon(imageIcon2);
		frmLoginWindow.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("New label");
		passwordLabel.setBounds(16, 230, 26, 26);
		Image dimg3 = img3.getScaledInstance(passwordLabel.getWidth(), passwordLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon3 = new ImageIcon(dimg3);
		passwordLabel.setIcon(imageIcon3);
		frmLoginWindow.getContentPane().add(passwordLabel);
		
		JLabel forgotLabel = new JLabel("Forgot your password?");
		forgotLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Forgot password");
				//TODO:  Implement forgotten password
			}
		});
		forgotLabel.setForeground(new Color(119, 136, 153));
		forgotLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
		forgotLabel.setBounds(16, 306, 115, 16);
		frmLoginWindow.getContentPane().add(forgotLabel);
		
		JLabel lblRegister = new JLabel("Sign Up");
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Sign up");
				//TODO: Implement registration
			}
		});
		lblRegister.setForeground(new Color(119, 136, 153));
		lblRegister.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
		lblRegister.setBounds(290, 306, 41, 16);
		frmLoginWindow.getContentPane().add(lblRegister);
		
		loginButton.setBounds(16, 267, 315, 35);
		frmLoginWindow.getContentPane().add(loginButton);
		frmLoginWindow.setLocationRelativeTo(null);
		//TODO:  Add SQL tying with a database that records the usernames and passwords.  Also ssl support wouldn't hurt, but this is a closed network so not necessary.
	}
}
