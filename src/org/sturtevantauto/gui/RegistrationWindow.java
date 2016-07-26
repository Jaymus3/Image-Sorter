package org.sturtevantauto.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationWindow {

	private JFrame frmRegistration;
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField secretCodeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow window = new RegistrationWindow();
					window.frmRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.setTitle("Registration");
		frmRegistration.setResizable(false);
		frmRegistration.setBounds(100, 100, 350, 350);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistration.getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(144, 6, 200, 26);
		frmRegistration.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 11, 61, 16);
		frmRegistration.getContentPane().add(lblName);
		
		usernameField = new JTextField();
		usernameField.setBounds(144, 67, 200, 26);
		frmRegistration.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblUsername.setBounds(6, 72, 73, 16);
		frmRegistration.getContentPane().add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 128, 200, 26);
		frmRegistration.getContentPane().add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblPassword.setBounds(6, 133, 73, 16);
		frmRegistration.getContentPane().add(lblPassword);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(144, 189, 200, 26);
		frmRegistration.getContentPane().add(confirmPasswordField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(6, 194, 126, 16);
		frmRegistration.getContentPane().add(lblConfirmPassword);
		
		secretCodeField = new JTextField();
		secretCodeField.setBounds(144, 250, 200, 26);
		frmRegistration.getContentPane().add(secretCodeField);
		secretCodeField.setColumns(10);
		
		JLabel secretCodeLabel = new JLabel("Secret Code:");
		secretCodeLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		secretCodeLabel.setBounds(6, 255, 126, 16);
		frmRegistration.getContentPane().add(secretCodeLabel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Registration time");
				//TODO:  Implement SQL table additions here.
			}
		});
		btnRegister.setBounds(187, 288, 157, 34);
		frmRegistration.getContentPane().add(btnRegister);
	}
}
