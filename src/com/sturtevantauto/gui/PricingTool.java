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

    // MARK: Variables
    private JFrame pricingFrame;
    private JTextField searchField;
    public JComboBox<String> yearBox;
    private JComboBox<String> makeBox;
    private JLabel priceDropoffLabel;
    private JLabel pricePickupLabel;
    private JLabel pickupLabel;
    private JLabel bringLabel;
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

    // MARK: Application creation
    public PricingTool() {
        initialize();
        PricingToolHandler.getYears(yearBox);

        pricePickupLabel = new JLabel("");
        pricePickupLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 34));
        pricePickupLabel.setBounds(6, 227, 100, 45);
        pricingFrame.getContentPane().add(pricePickupLabel);

        priceDropoffLabel = new JLabel("");
        priceDropoffLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 34));
        priceDropoffLabel.setBounds(142, 227, 100, 45);
        pricingFrame.getContentPane().add(priceDropoffLabel);

        pickupLabel = new JLabel("We pick it up:");
        pickupLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        pickupLabel.setBounds(6, 205, 100, 16);
        pickupLabel.setVisible(false);
        pricingFrame.getContentPane().add(pickupLabel);

        bringLabel = new JLabel("They bring it here:");
        bringLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        bringLabel.setBounds(130, 205, 115, 16);
        bringLabel.setVisible(false);
        pricingFrame.getContentPane().add(bringLabel);
    }

    // MARK: Initialization
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
        // MARK: Search handler
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                if (text.equals("") || text.equals(" "))
                    System.out.println("No valid text entered.");
                else {
                    System.out.println("Searching...");
                    int year = 0;
                    if (text.matches(".*\\d\\d.*") && !text.matches(".*\\d\\d\\d.*")) {
                        System.out.println("2 digit year found.");
                        if (text.indexOf('0') == -1) {
                            if (text.indexOf('9') != -1)
                                year = Integer.parseInt("19" + text.substring(text.indexOf('9'), (text.indexOf('9') + 2)));
                            if (text.indexOf('8') != -1)
                                year = Integer.parseInt("19" + text.substring(text.indexOf('8'), (text.indexOf('8') + 2)));
                            if (text.indexOf('7') != -1)
                                year = Integer.parseInt("19" + text.substring(text.indexOf('7'), (text.indexOf('7') + 2)));
                        }

                        else if (text.indexOf('0') != -1)
                            year = Integer.parseInt("20" + text.substring(text.indexOf('0'), (text.indexOf('0') + 2)));
                        if (year > 1970 && year < 2006) // Only specific year region permitted. TODO: Make this adjustable in admin panel
                        {
                            System.out.println("Year is: " + year);
                            String[] textspl = text.split(" ");
                            for (int i = 0; textspl.length > i; i++)
                                if (textspl[i].length() > 0)
                                    if (PricingToolHandler.getCarMakeResults(year, textspl[i])) {
                                       // for (int j = 0; textspl.length > j; j++)
                                            //if (textspl[j].length() > 0)
                                                //if (PricingToolHandler.getCarModelResults(year, textspl[i], textspl[j]))
                                                   // System.out.println("Even found a make.");

                                        PricingToolSelectCarPopup.run();
                                    }
                        } else if (year >= 2006)
                            System.out.println("Year is 2006 or newer.  The year is: " + year);
                        else if (year != 0)
                            System.out.println("Invalid year!  Year read was: " + year);
                        else
                            System.out.println("No year successfully parsed.");
                    }
                    if (text.matches(".*\\d\\d\\d\\d.*")) {
                        if (text.indexOf('2') == -1)
                            System.out.println(text.indexOf('1'));
                        else
                            System.out.println(text.indexOf('2'));
                        System.out.println("4 digit year found.");
                    }
                }
            }
        });
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
                if (action2 && action) {
                    int weight = PricingToolHandler.getWeightByCar(makeBox.getSelectedItem().toString(), modelBox.getSelectedItem().toString(), yearBox.getSelectedItem().toString());
                    double stdweight = PricingToolHandler.convertToStandard(weight);
                    double stdprice = PricingToolHandler.getPrice(stdweight);
                    priceDropoffLabel.setText("$" + Math.round(stdprice));
                    pricePickupLabel.setText("$" + (Math.round(stdprice) - 70));
                    pickupLabel.setVisible(true);
                    pricePickupLabel.setVisible(true);
                    priceDropoffLabel.setVisible(true);
                    bringLabel.setVisible(true);
                }
            }
        });
        modelBox.setSelectedIndex(0);
        modelBox.setBounds(244, 118, 200, 27);
        pricingFrame.getContentPane().add(modelBox);

        String[] makes = { "Select a make" };
        makeBox = new JComboBox(makes);
        makeBox.setFont(new Font("Helvetica Neue." + "", Font.PLAIN, 12));
        makeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (action) {
                    action2 = false;
                    modelBox.removeAllItems();
                    modelBox.addItem("Select a model");
                    pickupLabel.setVisible(false);
                    pricePickupLabel.setVisible(false);
                    priceDropoffLabel.setVisible(false);
                    bringLabel.setVisible(false);
                    System.out.println(makeBox.getSelectedItem());
                    PricingToolHandler.getModelsByMake(makeBox.getSelectedItem().toString(), yearBox.getSelectedItem().toString(), modelBox);
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
                pickupLabel.setVisible(false);
                pricePickupLabel.setVisible(false);
                priceDropoffLabel.setVisible(false);
                bringLabel.setVisible(false);
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