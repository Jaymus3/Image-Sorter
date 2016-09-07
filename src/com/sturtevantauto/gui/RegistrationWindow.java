package com.sturtevantauto.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sturtevantauto.io.Car;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RegistrationWindow {

    JFrame frmRegistration;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField secretCodeField;
    Car car = LoginWindow.getCar();

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
            public void actionPerformed(ActionEvent e) {
                if (new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))
                        && new String(passwordField.getPassword()).length() > 4) {
                    if (usernameField.getText().length() >= 4) {
                        if (nameField.getText().length() >= 3) {
                            if (secretCodeField.getText().equals("snecret")) {
                                try {
                                    boolean usernametaken = false;
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection connection = DriverManager.getConnection(car.getDBUrl(),
                                            car.getSQLUsername(), car.getSQLPassword());
                                    Statement statement = connection.createStatement();
                                    ResultSet use = statement.executeQuery("USE car_parts");
                                    ResultSet rs = statement.executeQuery("SELECT * FROM Account_Index where Username='"
                                            + usernameField.getText() + "'");
                                    while (rs.next())
                                        usernametaken = true;

                                    if (!usernametaken) {
                                        statement.executeUpdate(
                                                "INSERT INTO `Account_Index` (`Name`, `Username`, `Password`) VALUES ('"
                                                        + nameField.getText() + "', '" + usernameField.getText()
                                                        + "', '" + new String(passwordField.getPassword()) + "')");
                                        JOptionPane.showMessageDialog(frmRegistration, "Registration successful!",
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                                        frmRegistration.dispose();
                                    } else
                                        JOptionPane.showMessageDialog(frmRegistration,
                                                "That username is already in use!  Did you forget your password?",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    use.close();
                                    statement.close();
                                    connection.close();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            } else
                                JOptionPane.showMessageDialog(frmRegistration,
                                        "Secret code incorrect!  Don't know the secret code?  Ask someone else that has an account for it.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(frmRegistration,
                                    "Name too short!  Name must be at least 3 characters.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(frmRegistration,
                                "Username too short!  Must be at least 4 characters.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(frmRegistration, "Passwords do not match or password is too short!",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnRegister.setBounds(187, 288, 157, 34);
        frmRegistration.getContentPane().add(btnRegister);
        frmRegistration.setLocationRelativeTo(null);
        frmRegistration.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                car.setRegisterOpen(false);
            }
        });
    }
}