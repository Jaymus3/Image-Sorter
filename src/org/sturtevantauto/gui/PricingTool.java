package org.sturtevantauto.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;

public class PricingTool {

	private JFrame frmSturtevantAutoPricing;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PricingTool window = new PricingTool();
					window.frmSturtevantAutoPricing.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSturtevantAutoPricing = new JFrame();
		frmSturtevantAutoPricing.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frmSturtevantAutoPricing.setTitle("Sturtevant Auto Pricing Tool");
		frmSturtevantAutoPricing.setBounds(100, 100, 450, 300);
		frmSturtevantAutoPricing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSturtevantAutoPricing.getContentPane().setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(244, 6, 200, 26);
		frmSturtevantAutoPricing.getContentPane().add(searchField);
		searchField.setColumns(10);
		TextPrompt searchPrompt = new TextPrompt("Search", searchField);
		searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		searchPrompt.setHorizontalAlignment(SwingConstants.LEFT);
		searchPrompt.setForeground(Color.GRAY);
		
		String[] makes = {"Honda", "Ford", "Dodge", "Toyota", "Chevrolet", "BMW", "Buick"};
		JComboBox<String> makeBox = new JComboBox<String>(makes);
		makeBox.setSelectedIndex(0);
		makeBox.setBounds(244, 38, 200, 27);
		frmSturtevantAutoPricing.getContentPane().add(makeBox);
		
		String[] models = {"Civic", "Taurus", "Durango", "Camry", "Corvette", "Bavaria", "Riviera"};
		JComboBox<String> modelBox = new JComboBox<String>(models);
		modelBox.setSelectedIndex(0);
		modelBox.setBounds(244, 104, 200, 27);
		frmSturtevantAutoPricing.getContentPane().add(modelBox);
		
		String[] options = {"2DR", "3DR", "4DR", "LX", "EX", "SE", "LE", "XE"};
		JComboBox<String> optionBox = new JComboBox<String>(options);
		optionBox.setSelectedIndex(0);
		optionBox.setBounds(244, 137, 200, 27);
		frmSturtevantAutoPricing.getContentPane().add(optionBox);
	}
}
