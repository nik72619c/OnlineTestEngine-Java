package com.nikhil.testengine.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.nikhil.testengine.user.RegisterClass;
import com.nikhil.testengine.user.UserDAO;
import com.nikhil.testengine.util.CommonUtils;

public class RegisterView extends JFrame {
	
	public Logger logger= Logger.getLogger(RegisterView.class);

	private JPanel contentPane;
	private JTextField registerUserName;
	private JPasswordField registerPassword;
	private JLabel lblGender;
	private JPasswordField registerRepassword;
	JRadioButton male ;
	JRadioButton female = new JRadioButton("Female");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView frame = new RegisterView();
					frame.setVisible(true);
				} catch (Exception e) {
					Logger logger=Logger.getLogger(RegisterView.class);
				logger.error(CommonUtils.convertPrintStackIntoString(e));
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//	
//	public Boolean isRegisterFeildEmpty() {
//		
//		
//		
//	}
	
	public Boolean checkReTypePassword() {
		
		return new String(registerPassword.getPassword()).equals(new String(registerRepassword.getPassword()));
		
	}
	public RegisterView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrationForm = new JLabel("REGISTRATION FORM !");
		lblRegistrationForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegistrationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrationForm.setBounds(268, 24, 270, 22);
		contentPane.add(lblRegistrationForm);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(200, 89, 100, 28);
		contentPane.add(lblUsername);
		
		registerUserName = new JTextField();
		registerUserName.setBounds(328, 89, 320, 33);
		contentPane.add(registerUserName);
		registerUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(200, 161, 100, 28);
		contentPane.add(lblPassword);
		
		registerPassword = new JPasswordField();
		registerPassword.setBounds(328, 168, 320, 33);
		contentPane.add(registerPassword);
		
		lblGender = new JLabel("GENDER");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(200, 302, 100, 28);
		contentPane.add(lblGender);
		
		male = new JRadioButton("Male");
		male.setFont(new Font("Tahoma", Font.PLAIN, 14));
		male.setBounds(346, 302, 103, 28);
		contentPane.add(male);
		
		female = new JRadioButton("Female");
		female.setFont(new Font("Tahoma", Font.PLAIN, 14));
		female.setBounds(500, 302, 103, 28);
		contentPane.add(female);
		
		
		ButtonGroup group= new ButtonGroup();
		group.add(male);
		group.add(female);
		JLabel lblReenterPassword = new JLabel("RE-ENTER PASSWORD");
		lblReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblReenterPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReenterPassword.setBounds(108, 238, 192, 28);
		contentPane.add(lblReenterPassword);
		
		registerRepassword = new JPasswordField();
		registerRepassword.setBounds(328, 245, 320, 33);
		contentPane.add(registerRepassword);
		
		JLabel lblState = new JLabel("STATE");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblState.setBounds(200, 387, 100, 28);
		contentPane.add(lblState);
		
		String STATES_ARRAY[]= new String[] {
			   "AP: Andhra Pradesh",
			   "AR: Arunachal Pradesh",
			   "AS: Assam",
			   "BR: Bihar",
			   "CG: Chhattisgarh",
			   "Chandigarh: Chandigarh",
			   "DN: Dadra and Nagar Haveli",
			   "DD: Daman and Diu",
			   "DL: Delhi",
			   "GA: Goa",
			   "GJ: Gujarat",
			   "HR: Haryana",
			   "HP: Himachal Pradesh",
			   "JK: Jammu and Kashmir",
			   "JH: Jharkhand",
			   "KA: Karnataka",
			   "KL: Kerala",
			   "MP: Madhya Pradesh",
			   "MH: Maharashtra",
			   "MN: Manipur",
			   "ML: Meghalaya",
			   "MZ: Mizoram",
			   "NL: Nagaland",
			   "OR: Orissa",
			   "PB: Punjab",
			   "PY: Pondicherry",
			   "RJ: Rajasthan",
			   "SK: Sikkim",
			   "TN: Tamil Nadu",
			   "TR: Tripura",
			   "UP: Uttar Pradesh",
			   "UK: Uttarakhand",
			   "WB: West Bengal"
			}; 
		JComboBox stateComboBox = new JComboBox(STATES_ARRAY);

		stateComboBox.setBounds(328, 393, 320, 21);
		contentPane.add(stateComboBox);
		
		JButton Register = new JButton("REGISTER");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String radioValue="";
				radioValue=male.isSelected()?"Male":"Female";
				String selected_text = (String) stateComboBox.getItemAt(stateComboBox.getSelectedIndex());
			
		RegisterClass obj= new RegisterClass(registerUserName.getText()
				,new String(registerPassword.getPassword()),radioValue,selected_text);
		
		String message;
		UserDAO userdao= new UserDAO();
		
		try {
			
			if(checkReTypePassword()) {
				
				message=userdao.registerUser(obj);
				JOptionPane.showMessageDialog(RegisterView.this,message);
				
			}
			
			else {
				
				JOptionPane.showMessageDialog(RegisterView.this, "The passwords entered donot match !");
				
			}
			
	
			
		}
		
catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(RegisterView.this, "Driver for your Database could not be loaded ! Kindly contact the DB Admin");
//			e.printStackTrace();
			logger.error(CommonUtils.convertPrintStackIntoString(e));
		}
		
		catch(SQLException e) {
			
			JOptionPane.showMessageDialog(RegisterView.this, "Some databse connectivity problem ! The servers might be down ");
//			e.printStackTrace();
			CommonUtils.convertPrintStackIntoString(e);
			
		}
		
		catch(Exception e) {
			
			JOptionPane.showMessageDialog(RegisterView.this, "Whoa ! Some serious problem has occured ! contact the Customer Support");
//			e.printStackTrace();
			logger.error(CommonUtils.convertPrintStackIntoString(e));
		}
		
		
				
			}
		});
		Register.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Register.setBounds(331, 489, 118, 50);
		contentPane.add(Register);
		
		JButton BackToLogin = new JButton("BACK TO LOGIN SCREEN");
		BackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LoginView lv=new LoginView();
				lv.setVisible(true);
			}
		});
		BackToLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BackToLogin.setBounds(502, 489, 207, 50);
		contentPane.add(BackToLogin);
	}
}
