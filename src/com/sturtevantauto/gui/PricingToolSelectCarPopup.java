/**
 * 
 * @author Aevum Kairos
 * @Note
 * Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
package com.sturtevantauto.gui;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import com.sturtevantauto.io.PricingToolHandler;

/**
 *
 * @author Aevum Kairos
 * @Note Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
public class PricingToolSelectCarPopup {

    private JFrame frame;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void run() {
        try {
            PricingToolSelectCarPopup window = new PricingToolSelectCarPopup();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the application.
     */
    public PricingToolSelectCarPopup() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        frame.setTitle("Select a car:");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 426);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        String[] columnnames = { "Make", "Model", "Weight", "Price" };
        JTable table = new JTable(PricingToolHandler.getModelArray(), columnnames);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        JScrollPane listScroll = new JScrollPane(table);
        contentPane.add(listScroll);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
    }

}
