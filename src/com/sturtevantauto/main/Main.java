package com.sturtevantauto.main;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.UIManager;
import com.sturtevantauto.gui.LoginWindow;


public class Main {
    /**
     * Runs at the start of the program and sets properties as well as
     * initializing the GUI.
     * 
     * @param args
     * @throws IOException
     * @throws AWTException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, AWTException, ClassNotFoundException, SQLException {
        EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("static-access")
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
}
