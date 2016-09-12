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
import javax.swing.JLabel;

public class PricingTool {

    private JFrame pricingFrame;
    private JTextField searchField;
    public JComboBox<String> yearBox;
    private JComboBox<String> makeBox;
    private boolean action;
    private boolean action2;

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
        
        JLabel pricePickupLabel = new JLabel("$0000");
        pricePickupLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 34));
        pricePickupLabel.setBounds(6, 227, 100, 45);
        pricingFrame.getContentPane().add(pricePickupLabel);
        
        JLabel priceDropoffLabel = new JLabel("$0000");
        priceDropoffLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 34));
        priceDropoffLabel.setBounds(142, 227, 100, 45);
        pricingFrame.getContentPane().add(priceDropoffLabel);
        
        JLabel pickupLabel = new JLabel("We pick it up:");
        pickupLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        pickupLabel.setBounds(6, 205, 100, 16);
        pricingFrame.getContentPane().add(pickupLabel);
        
        JLabel lblTheyBringIt = new JLabel("They bring it here:");
        lblTheyBringIt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        lblTheyBringIt.setBounds(130, 205, 115, 16);
        pricingFrame.getContentPane().add(lblTheyBringIt);
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initialize() {
        pricingFrame = new JFrame();
        pricingFrame.getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        pricingFrame.setResizable(false);
        pricingFrame.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        pricingFrame.setTitle("Sturtevant Auto Pricing Tool");
        pricingFrame.setBounds(100, 100, 450, 300);
        pricingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pricingFrame.getContentPane().setLayout(null);

        searchField = new JTextField();
        searchField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        searchField.setBounds(244, 6, 200, 26);
        pricingFrame.getContentPane().add(searchField);
        searchField.setColumns(10);
        TextPrompt searchPrompt = new TextPrompt("Search", searchField);
        searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        searchPrompt.setHorizontalAlignment(SwingConstants.LEFT);
        searchPrompt.setForeground(Color.GRAY);

        String[] models = { "Select a model" };
        JComboBox<String> modelBox = new JComboBox(models);
        modelBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        modelBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (action2 && action)
                {
                    int weight = PricingToolHandler.getWeightByCar(makeBox.getSelectedItem().toString(),
                            modelBox.getSelectedItem().toString(), yearBox.getSelectedItem().toString());
                    double stdweight = PricingToolHandler.convertToStandard(weight);
                    double stdprice = PricingToolHandler.getPrice(stdweight);
                    System.out.println("Price: " + stdprice);
                }
            }
        });
        modelBox.setSelectedIndex(0);
        modelBox.setBounds(244, 118, 200, 27);
        pricingFrame.getContentPane().add(modelBox);

        String[] makes = { "Select a make" };
        makeBox = new JComboBox(makes);
        makeBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        makeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (action) {
                    action2 = false;
                    modelBox.removeAllItems();
                    modelBox.addItem("Select a model");
                    System.out.println(makeBox.getSelectedItem());
                    PricingToolHandler.getModelsByMake(makeBox.getSelectedItem().toString(),
                            yearBox.getSelectedItem().toString(), modelBox);
                    action2 = true;
                }
            }
        });
        makeBox.setBounds(244, 83, 200, 27);
        pricingFrame.getContentPane().add(makeBox);

        String[] options = { "Select a package" };
        JComboBox<String> optionBox = new JComboBox(options);
        optionBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        optionBox.setSelectedIndex(0);
        optionBox.setBounds(244, 157, 200, 27);
        pricingFrame.getContentPane().add(optionBox);

        yearBox = new JComboBox(new Object[] { "Select a year" });
        yearBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        yearBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = false;
                makeBox.removeAllItems();
                makeBox.addItem("Select a make");
                modelBox.removeAllItems();
                modelBox.addItem("Select a model");
                System.out.println(yearBox.getSelectedItem());
                PricingToolHandler.getMakesByYear(yearBox.getSelectedItem().toString(), makeBox);
                action = true;
            }
        });
        yearBox.setBounds(244, 44, 200, 27);
        pricingFrame.getContentPane().add(yearBox);
        pricingFrame.setLocationRelativeTo(null);
    }
}