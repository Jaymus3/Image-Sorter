package com.sturtevantauto.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.sturtevantauto.io.AccountHandler;
import com.sturtevantauto.io.Car;

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
    static Car car = new Car();

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

        final JLabel errorLabel = new JLabel("Invalid username/password.");
        errorLabel.setVisible(false);
        errorLabel.setForeground(new Color(255, 0, 0));
        errorLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
        errorLabel.setBounds(44, 174, 240, 16);
        frmLoginWindow.getContentPane().add(errorLabel);

        JLabel lblLogo = new JLabel("");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        lblLogo.setBounds(7, 6, 277, 170);
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/img/logo-transparent.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(img);
        lblLogo.setIcon(imageIcon);
        frmLoginWindow.getContentPane().add(lblLogo);

        usernameField = new JTextField();
        usernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            public void actionPerformed(ActionEvent e) {
                AccountHandler.login(usernameField, passwordField, errorLabel, frmLoginWindow);
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
            public void actionPerformed(ActionEvent e) {
                AccountHandler.login(usernameField, passwordField, errorLabel, frmLoginWindow);
            }
        });

        JLabel usernameLabel = new JLabel("");
        usernameLabel.setBounds(16, 192, 26, 26);
        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(getClass().getResource("/img/username-logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon imageIcon2 = new ImageIcon(img2);
        usernameLabel.setIcon(imageIcon2);
        frmLoginWindow.getContentPane().add(usernameLabel);

        BufferedImage img3 = null;
        try {
            img3 = ImageIO.read(getClass().getResource("/img/password-logos.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel passwordLabel = new JLabel("");
        passwordLabel.setBounds(16, 230, 26, 26);

        ImageIcon imageIcon3 = new ImageIcon(img3);
        passwordLabel.setIcon(imageIcon3);
        frmLoginWindow.getContentPane().add(passwordLabel);

        JLabel forgotLabel = new JLabel("Forgot your password?");
        forgotLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String messageString = "<html><i>\"You're the only one that's small enough to get up there, so if you get stuck we<br>can't crawl in there to get you.\"</i> <b>*pauses*</b> <i>\"Don't get stuck.\"</i> - Howard<br><br>"
                        + "If you forgot your password, there's no real way to recover it.  You're gonna have<br>to ask James for it.  Having a recovery method would be a <i>security vulnerability!</i></html>";
                Font font = new Font("Helvetica Neue", Font.PLAIN, 14);
                JLabel labelConversionForFontSupport = new JLabel(messageString);
                labelConversionForFontSupport.setFont(font);
                JOptionPane.showMessageDialog(frmLoginWindow, labelConversionForFontSupport, "Forgot password", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        forgotLabel.setForeground(new Color(119, 136, 153));
        forgotLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
        forgotLabel.setBounds(16, 306, 115, 16);
        frmLoginWindow.getContentPane().add(forgotLabel);

        JLabel lblRegister = new JLabel("Sign Up");
        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!car.getRegister()) {
                    RegistrationWindow window = new RegistrationWindow();
                    window.frmRegistration.setVisible(true);
                    car.setRegisterOpen(true);
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

    public static Car getCar() {
        return car;
    }

}
