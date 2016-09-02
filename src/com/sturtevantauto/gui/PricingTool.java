package com.sturtevantauto.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sturtevantauto.io.PricingToolHandler;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PricingTool {

    private JFrame pricingFrame;
    private JTextField searchField;
    public JComboBox<String> yearBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PricingTool window = new PricingTool();
                    window.pricingFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public PricingTool() {
        initialize();
        PricingToolHandler.getYears(yearBox);
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initialize() {
        pricingFrame = new JFrame();
        pricingFrame.setResizable(false);
        pricingFrame.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        pricingFrame.setTitle("Sturtevant Auto Pricing Tool");
        pricingFrame.setBounds(100, 100, 450, 300);
        pricingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pricingFrame.getContentPane().setLayout(null);

        searchField = new JTextField();
        searchField.setBounds(244, 6, 200, 26);
        pricingFrame.getContentPane().add(searchField);
        searchField.setColumns(10);
        TextPrompt searchPrompt = new TextPrompt("Search", searchField);
        searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        searchPrompt.setHorizontalAlignment(SwingConstants.LEFT);
        searchPrompt.setForeground(Color.GRAY);

        String[] makes = { "Select a make" };
        final JComboBox<String> makeBox = new JComboBox(makes);
        makeBox.setSelectedIndex(0);
        makeBox.setBounds(244, 83, 200, 27);
        pricingFrame.getContentPane().add(makeBox);

        String[] models = { "Select a model" };
        JComboBox<String> modelBox = new JComboBox(models);
        modelBox.setSelectedIndex(0);
        modelBox.setBounds(244, 118, 200, 27);
        pricingFrame.getContentPane().add(modelBox);

        String[] options = { "Select a package" };
        JComboBox<String> optionBox = new JComboBox(options);
        optionBox.setSelectedIndex(0);
        optionBox.setBounds(244, 157, 200, 27);
        pricingFrame.getContentPane().add(optionBox);

        yearBox = new JComboBox(new Object[] { "Select a year" });
        yearBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(yearBox.getSelectedItem());
                PricingToolHandler.getMakesByYear(yearBox.getSelectedItem().toString(), makeBox);
            }
        });
        yearBox.setBounds(244, 44, 200, 27);
        pricingFrame.getContentPane().add(yearBox);
    }
}
