package com.stuartsul.ordering;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class OrderingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField n_textField;
	private JTextField r_textField;
	private JTextArea ordering_textArea;
	private JComboBox mode_comboBox;
	private JLabel error_label;
	private int[][] ordering = { };

	public static void gui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderingGUI frame = new OrderingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderingGUI() {
		setTitle("Ordering");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] modes = {"Factorial", "Permutation", "Combination"};
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1, 40, 210, 35, 18, 135, 0};
		gbl_contentPane.rowHeights = new int[]{1, 34, 71, 50, 26, 26, 29, 16, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		ordering_textArea = new JTextArea();
		ordering_textArea.setEditable(false);
		ordering_textArea.setLineWrap(true);
		scrollPane.setViewportView(ordering_textArea);
		
		mode_comboBox = new JComboBox(modes);
		GridBagConstraints gbc_mode_comboBox = new GridBagConstraints();
		gbc_mode_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mode_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_mode_comboBox.gridwidth = 3;
		gbc_mode_comboBox.gridx = 4;
		gbc_mode_comboBox.gridy = 1;
		contentPane.add(mode_comboBox, gbc_mode_comboBox);
		
		JLabel label1 = new JLabel("N:");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.EAST;
		gbc_label1.insets = new Insets(0, 0, 5, 5);
		gbc_label1.gridx = 4;
		gbc_label1.gridy = 2;
		contentPane.add(label1, gbc_label1);
		
		n_textField = new JTextField();
		GridBagConstraints gbc_n_textField = new GridBagConstraints();
		gbc_n_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_n_textField.insets = new Insets(0, 0, 5, 0);
		gbc_n_textField.gridx = 5;
		gbc_n_textField.gridy = 2;
		contentPane.add(n_textField, gbc_n_textField);
		n_textField.setColumns(10);
		
		JLabel label2 = new JLabel("R:");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.anchor = GridBagConstraints.EAST;
		gbc_label2.insets = new Insets(0, 0, 5, 5);
		gbc_label2.gridx = 4;
		gbc_label2.gridy = 3;
		contentPane.add(label2, gbc_label2);
		
		r_textField = new JTextField();
		GridBagConstraints gbc_r_textField = new GridBagConstraints();
		gbc_r_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_r_textField.insets = new Insets(0, 0, 5, 0);
		gbc_r_textField.gridx = 5;
		gbc_r_textField.gridy = 3;
		contentPane.add(r_textField, gbc_r_textField);
		r_textField.setColumns(10);
		
		JButton calculate_button = new JButton("Order");
		calculate_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n, r, mode = mode_comboBox.getSelectedIndex() + 1;

				try {
					n = Integer.parseInt(n_textField.getText());
					r = Integer.parseInt(r_textField.getText());
				} catch (NumberFormatException ex) {
					printError(102);
					return;
				}
				
				if (mode == 1) r = n;

				if (!(1 <= n && n <= 10 && 1 <= r && r <= 10 && n >= r)) {
					printError(103);
					return;
				}
				
				ordering = new Ordering(n, r, mode).getOrdering();
				printOrdering();
			}
		});
		GridBagConstraints gbc_calculate_button = new GridBagConstraints();
		gbc_calculate_button.gridwidth = 2;
		gbc_calculate_button.insets = new Insets(0, 0, 5, 0);
		gbc_calculate_button.gridx = 4;
		gbc_calculate_button.gridy = 4;
		contentPane.add(calculate_button, gbc_calculate_button);
		
		JLabel result_label = new JLabel("RESULT");
		GridBagConstraints gbc_result_label = new GridBagConstraints();
		gbc_result_label.gridwidth = 2;
		gbc_result_label.insets = new Insets(0, 0, 5, 0);
		gbc_result_label.gridx = 4;
		gbc_result_label.gridy = 5;
		contentPane.add(result_label, gbc_result_label);
		
		error_label = new JLabel("khjjk");
		GridBagConstraints gbc_error_label = new GridBagConstraints();
		gbc_error_label.gridwidth = 2;
		gbc_error_label.insets = new Insets(0, 0, 5, 0);
		gbc_error_label.gridx = 4;
		gbc_error_label.gridy = 6;
		contentPane.add(error_label, gbc_error_label);
	}

	public void printError(int cause) {
		ordering_textArea.setText("");
		
		String errorMessage = "";
		
		switch (cause) {
		case 100:
			errorMessage = "Invalid input: input is too long";
			break;
		case 101:
			errorMessage = "Invalid input: no appropriate operator";
			break;
		case 102:
			errorMessage = "Invalid input: no appropriate integer found";
			break;
		case 103:
			errorMessage = "Invalid input: integer size inappropriate";
			break;
		}
		
		error_label.setText(errorMessage);
	}
	
	public void printOrdering() {
		error_label.setText(ordering.length + " orders");
		
		String ordering_string = "";

		for (int i = 0; i < ordering.length; i++) {
			ordering_string += (i + 1) + ": (";
			
			for (int j = 0; j < ordering[0].length; j++) {
				ordering_string += this.ordering[i][j] + "";
				
				if (j < ordering[0].length - 1)
					ordering_string += ", ";
				else
					ordering_string += ")\n";
			}
		}
		
		ordering_textArea.setText(ordering_string);
	}

}
