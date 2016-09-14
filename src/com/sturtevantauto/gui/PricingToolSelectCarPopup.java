/**
 * 
 * @author Aevum Kairos
 * @Note
 * Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
package com.sturtevantauto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

/**
 *
 * @author Aevum Kairos
 * @Note Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
@SuppressWarnings("serial")
public class PricingToolSelectCarPopup extends JFrame {

    private JPanel contentPane;

    // MARK: Temporary main method for testing
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PricingToolSelectCarPopup frame = new PricingToolSelectCarPopup();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // MARK: Initialize components
    public PricingToolSelectCarPopup() {
        setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        setTitle("Select a car:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        String[] columnnames = { "Make", "Model", "Weight", "Price" };
        String[][] lists = { { "Honda", "Civic", "1337", "$137" }, { "Ford", "Taurus", "2727", "$270" } };
        JTable table = new JTable(lists, columnnames);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        JScrollPane listScroll = new JScrollPane(table);
        contentPane.add(listScroll);
    }

}
