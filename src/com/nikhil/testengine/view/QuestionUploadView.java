package com.nikhil.testengine.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.nikhil.testengine.question.QuestionUploadHelper;
import com.nikhil.testengine.util.CommonUtils;

public class QuestionUploadView extends JFrame {

	public Logger logger= Logger.getLogger(QuestionUploadView.class);
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionUploadView frame = new QuestionUploadView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void  uploadTest() {
		
		JFileChooser jfilechooser=new JFileChooser("Documents");
		jfilechooser.showOpenDialog(this);
		File file=jfilechooser.getSelectedFile();
//		JOptionPane.showMessageDialog(this,"the path of the file is - " + file.getAbsolutePath());
		String path=file.getAbsolutePath();
		System.out.println("the path sent to uploadQuestion() "+path);
		QuestionUploadHelper questionuploadhelper=new QuestionUploadHelper();
		try {
			
			boolean isUploaded=questionuploadhelper.uploadQuestion(path);
			System.out.println("isUploaded : "+ isUploaded);
			JOptionPane.showMessageDialog(this,isUploaded?"the file uploaded successfully":"Upload failed...the file doesnot exist");
		} 
		
		catch (IOException e) {        
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"There was some problem in uploading the file ! Kindly contact the system admin");
			logger.debug(CommonUtils.convertPrintStackIntoString(e));
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "some DB problem occured ! kindly contact system admin");
		}
		
		
	}
	public QuestionUploadView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUploadYourTests = new JLabel("Upload your TESTS HERE !");
		lblUploadYourTests.setHorizontalAlignment(SwingConstants.CENTER);
		lblUploadYourTests.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUploadYourTests.setBounds(238, 29, 362, 43);
		contentPane.add(lblUploadYourTests);
		
		JButton uploadBtn = new JButton("UPLOAD TEST");
		uploadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				uploadTest();
			}
		});
		uploadBtn.setFont(new Font("Stencil", Font.PLAIN, 16));
		uploadBtn.setBounds(291, 130, 251, 43);
		contentPane.add(uploadBtn);
	}
}
