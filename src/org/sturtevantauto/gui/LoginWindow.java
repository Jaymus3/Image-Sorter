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

import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frmLoginWindow;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		frmLoginWindow.setTitle("Login");
		frmLoginWindow.setResizable(false);
		frmLoginWindow.setLocationRelativeTo(null);
		frmLoginWindow.setBounds(100, 100, 349, 330);
		frmLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginWindow.getContentPane().setLayout(null);
		
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
		usernameField.setBounds(31, 188, 141, 35);
		TextPrompt usernamePrompt = new TextPrompt("Username", usernameField);
		usernamePrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		usernamePrompt.setHorizontalAlignment(SwingConstants.LEFT);
		usernamePrompt.setForeground(Color.GRAY);
		frmLoginWindow.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(31, 226, 141, 35);
		TextPrompt passwordPrompt = new TextPrompt("Password", passwordField);
		passwordPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		passwordPrompt.setHorizontalAlignment(SwingConstants.LEFT);
		passwordPrompt.setForeground(Color.GRAY);
		frmLoginWindow.getContentPane().add(passwordField);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TODO:  Add login functionality
			}
		});
		loginButton.setBounds(6, 267, 166, 35);
		frmLoginWindow.getContentPane().add(loginButton);
		
		JLabel usernameLabel = new JLabel("New label");
		usernameLabel.setBounds(6, 192, 26, 26);
		BufferedImage img2 = null;
		try 
		{
		    img2 = ImageIO.read(new File("resources/img/username-logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg2 = img2.getScaledInstance(usernameLabel.getWidth(), usernameLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(dimg2);
		usernameLabel.setIcon(imageIcon2);
		frmLoginWindow.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("New label");
		passwordLabel.setBounds(6, 230, 26, 26);
		BufferedImage img3 = null;
		try 
		{
		    img3 = ImageIO.read(new File("resources/img/password-logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg3 = img3.getScaledInstance(passwordLabel.getWidth(), passwordLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon3 = new ImageIcon(dimg3);
		passwordLabel.setIcon(imageIcon3);
		frmLoginWindow.getContentPane().add(passwordLabel);
		
		JButton forgotButton = new JButton("Forgot password?");
		forgotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TODO: Add password recovery method
			}
		});
		forgotButton.setBounds(202, 267, 141, 35);
		frmLoginWindow.getContentPane().add(forgotButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TODO:  Add registration
			}
		});
		registerButton.setBounds(202, 227, 141, 35);
		frmLoginWindow.getContentPane().add(registerButton);
		//TODO:  Add SQL tying with a database that records the usernames and passwords.  Also ssl support wouldn't hurt, but this is a closed network so not necessary.
	}
}
