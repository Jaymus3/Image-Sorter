package org.sturtevantauto.gui;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

import org.sturtevantauto.io.CarDefinitions;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginWindow {

	public JFrame frmLoginWindow;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306?autoReconnect=true&useSSL=false";
	private static String dbUsername = "imagesorter";
	private static String dbPassword = "4vSmbst4Q#uhL#3%";


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
		frmLoginWindow.setBounds(100, 100, 290, 350);
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
		lblLogo.setBounds(6, 6, 277, 170);
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File("resources/img/logo-transparent.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(img);
		lblLogo.setIcon(imageIcon);
		frmLoginWindow.getContentPane().add(lblLogo);
		
		usernameField = new JTextField();
		usernameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				passwordField.requestFocus();
			}
		});
		usernameField.setBounds(41, 188, 243, 35);
		TextPrompt usernamePrompt = new TextPrompt("Username", usernameField);
		usernamePrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		usernamePrompt.setHorizontalAlignment(SwingConstants.LEFT);
		usernamePrompt.setForeground(Color.GRAY);
		frmLoginWindow.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				login(usernameField, passwordField, errorLabel);
			}
		});
		passwordField.setBounds(41, 226, 243, 35);
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
				login(usernameField, passwordField, errorLabel);
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
		
		ImageIcon imageIcon2 = new ImageIcon(img2);
		usernameLabel.setIcon(imageIcon2);
		frmLoginWindow.getContentPane().add(usernameLabel);
		
		BufferedImage img3 = null;
		try 
		{
		    img3 = ImageIO.read(new File("resources/img/password-logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		JLabel passwordLabel = new JLabel("New label");
		passwordLabel.setBounds(16, 230, 26, 26);
		
		ImageIcon imageIcon3 = new ImageIcon(img3);
		passwordLabel.setIcon(imageIcon3);
		frmLoginWindow.getContentPane().add(passwordLabel);
		
		JLabel forgotLabel = new JLabel("Forgot your password?");
		forgotLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				JOptionPane.showMessageDialog(frmLoginWindow, "Forgotten passwords currently have no recovery method.  Ask James for an account password reset.", "Forgot password", JOptionPane.INFORMATION_MESSAGE);
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
				if(!CarDefinitions.getRegister())
				{
				RegistrationWindow window = new RegistrationWindow();
				window.frmRegistration.setVisible(true);
				CarDefinitions.setRegisterOpen(true);
				}
			}
		});
		lblRegister.setForeground(new Color(119, 136, 153));
		lblRegister.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
		lblRegister.setBounds(243, 306, 41, 16);
		frmLoginWindow.getContentPane().add(lblRegister);
		
		loginButton.setBounds(16, 267, 268, 35);
		frmLoginWindow.getContentPane().add(loginButton);
		frmLoginWindow.setLocationRelativeTo(null);
		
	}
	private void login(JTextField userfield, JPasswordField passwordfield, JLabel error)
	{
		try {
			String user = null;
			String pass = null;
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		    Statement statement = connection.createStatement();
			ResultSet use = statement.executeQuery("USE car_parts");
			String username = userfield.getText();
			char[] password = passwordfield.getPassword();
			ResultSet rs = statement.executeQuery("SELECT * FROM Account_Index where Username='" + username + "' and Password='" + new String(password) + "'");
			while(rs.next())
			{
				user = rs.getString("Username");
                pass = rs.getString("Password");
			}
			if(username.equals(user) && new String(password).equals(pass))
			{
				frmLoginWindow.dispose();
				MainGUI window = new MainGUI();
				window.frmImageSorter.setVisible(true);
			}
			else
			{
				error.setVisible(true);
			}
			use.close();
			rs.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
