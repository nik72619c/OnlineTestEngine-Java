package com.nikhil.testengine.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nikhil.testengine.question.QuestionDTO;
import com.nikhil.testengine.question.model.jTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResultView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResultView(ArrayList<QuestionDTO> questions) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReportCard = new JLabel("REPORT CARD");
		lblReportCard.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblReportCard.setBounds(366, 26, 134, 29);
		contentPane.add(lblReportCard);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 68, 771, 397);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new jTableModel(questions));
		scrollPane.setViewportView(table);
	}
}
