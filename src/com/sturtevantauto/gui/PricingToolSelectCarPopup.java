/**
 * 
 * @author Aevum Kairos
 * @Note
 * Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
package com.sturtevantauto.gui;

import java.awt.Dialog.ModalityType;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import com.sturtevantauto.io.PricingToolHandler;

/**
 *
 * @author Aevum Kairos
 * @Note 
 * Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
public class PricingToolSelectCarPopup {

    private JPanel contentPane;
    private JDialog dialogue;

    /**
     * Launch the application.
     */
    public static void run() {
        try {
            PricingToolSelectCarPopup window = new PricingToolSelectCarPopup();
            window.dialogue.setVisible(true);
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
        dialogue = new JDialog(PricingTool.getFrame());
        dialogue.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        dialogue.setTitle("Select a car:");
        dialogue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogue.setBounds(100, 100, 800, 426);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        dialogue.setContentPane(contentPane);
        String[] columnnames = { "Make", "Model", "Weight", "Price" };
        JTable table = new JTable(PricingToolHandler.getModelArray(), columnnames);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        JScrollPane listScroll = new JScrollPane(table);
        contentPane.add(listScroll);
        dialogue.setLocationRelativeTo(null);
        dialogue.setModal(true);
        dialogue.setAlwaysOnTop(true);
        dialogue.setModalityType(ModalityType.APPLICATION_MODAL);
    }

}
