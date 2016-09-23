/**
 * 
 * @author Aevum Kairos
 * @Note
 * Javadoc authored by me doesn't necessarily indicate that I wrote it. It just means I documented the purpose of something.
 */
package com.sturtevantauto.gui;

import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
        dialogue.setBounds(100, 100, 650, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        dialogue.setContentPane(contentPane);
        String[] columnnames = { "Make", "Model", "Weight", "Price" };
        JTable table = new JTable(PricingToolHandler.getModelArray(), columnnames);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent m) {
                JTable table =(JTable) m.getSource();
                Point p = m.getPoint();
                int row = table.rowAtPoint(p);
                if (m.getClickCount() == 2) {
                    System.out.println(table.getSelectedRow());
                    System.out.println(row);
                }
            }
        });
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        JScrollPane listScroll = new JScrollPane(table);
        contentPane.add(listScroll);
        dialogue.setLocationRelativeTo(null);
        dialogue.setModal(true);
        dialogue.setAlwaysOnTop(true);
        dialogue.setModalityType(ModalityType.APPLICATION_MODAL);
    }

}
