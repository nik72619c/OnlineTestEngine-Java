package com.nikhil.testengine.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.nikhil.testengine.user.UserDAO;
import com.nikhil.testengine.user.UserDTO;
import com.nikhil.testengine.util.CommonUtils;


public class LoginView extends JFrame {
	
	public Logger logger=Logger.getLogger(LoginView.class);

	private JPanel contentPane;
	private JTextField usernameFeild;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginView frame=new LoginView();
					frame.logger.debug("this is a test logger message for the main "
							+ "function of the login view !");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public void checkForLogin() {
		
		logger.debug("inside the checkForLogin function of LoginView !");
		String username=usernameFeild.getText();
		String password= new String(passwordField.getPassword());
		UserDAO userdao= new UserDAO();
		
		try {
			
	 UserDTO userDTO=userdao.loginUser(username, password);
//		JOptionPane.showMessageDialog(this, message);
	 if(userDTO==null) {
		 
		 JOptionPane.showMessageDialog(this,"Invalid UserName or Password , "
		 		+ "Kindly Check yr credentials");
		 return;
		 
	 }
	 
	 DashBoardView dashboard= new DashBoardView();
	 dashboard.fillDashboard(userDTO);
	 dashboard.setVisible(true);
	 dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
	 this.setVisible(false);
	 this.dispose();
		
		}
		
		catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(this, "Driver for your Database could not be "
					+ "loaded ! Kindly contact the DB Admin");
//			e.printStackTrace();
			
			logger.error(CommonUtils.convertPrintStackIntoString(e));
		
		}
		
		catch(SQLException e) {
			
			JOptionPane.showMessageDialog(this, "Some databse connectivity problem !"
					+ " The servers might be down ");
//			e.printStackTrace();
			logger.error(CommonUtils.convertPrintStackIntoString(e));
			
		}
		
		catch(Exception e) {
			
			JOptionPane.showMessageDialog(this, "Whoa ! Some serious problem has occured !"
					+ " contact the Customer Support");
//			e.printStackTrace();
			logger.error(CommonUtils.convertPrintStackIntoString(e));
		}
		
		
	}
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(596,518);
//		setBounds(100, 100, 612, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("WELCOME TO THE ONLINE TEST ENGINE !");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblWelcomeToThe.setBounds(28, 29, 526, 27);
		contentPane.add(lblWelcomeToThe);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUsername.setBounds(56, 136, 85, 27);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(56, 206, 85, 27);
		contentPane.add(lblPassword);
		
		usernameFeild = new JTextField();
		usernameFeild.setBounds(198, 142, 246, 34);
		contentPane.add(usernameFeild);
		usernameFeild.setColumns(10);
		
		JButton loginBtn = new JButton("LOGIN");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				checkForLogin();
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginBtn.setBounds(146, 333, 115, 46);
		contentPane.add(loginBtn);
		
		JButton registerBtn = new JButton("REGISTER");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				RegisterView rw= new RegisterView();
				rw.setVisible(true);
				
			}
		});
		
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerBtn.setBounds(329, 333, 115, 46);
		contentPane.add(registerBtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(198, 223, 246, 34);
		contentPane.add(passwordField);
	}
}
