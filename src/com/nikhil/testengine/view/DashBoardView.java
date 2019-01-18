package com.nikhil.testengine.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nikhil.testengine.user.RightDTO;
import com.nikhil.testengine.user.UserDTO;


public class DashBoardView extends JFrame {

	private JPanel contentPane;
	JLabel welcomeLbl;
	JMenu file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoardView frame = new DashBoardView();
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
	
	public void fillDashboard(UserDTO userDTO) {
		
		System.out.println("inside the fillDashboard function and the userDTO obtained is :-" + userDTO);
		System.out.println(userDTO.getRights().size());
		if(userDTO.getRights()!=null) {
			
			this.welcomeLbl.setText("WELCOME "+ userDTO.getRolename() + " "+ userDTO.getUserid());
			//after this we need to fill the menu code also in it !
			
			
			for(RightDTO right: userDTO.getRights()) {
				System.out.println("inside rightDTO for loop");
				JMenuItem menuItem = new JMenuItem(right.getName());
				
				
				
				menuItem.addActionListener(new ActionListener() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name=right.getScreenname();
						int index=name.lastIndexOf(".java");
						String className=name.substring(0, index);
						System.out.println("classNAme is " +className);
						try {
						Object object=Class.forName("com.nikhil.testengine.view."+className).newInstance();
	System.out.println("the class path is :-"+ className );
	System.out.println("object is "+ object);
						Method method=object.getClass().getMethod("setVisible", boolean.class);
						method.invoke(object, true);
						} 
						
						catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(DashBoardView.this, "Some Internal error while loading dashboard!");
						}
						
					catch(NoSuchMethodException e1) {
						
						JOptionPane.showMessageDialog(DashBoardView.this, "the dashboard could not be loaded !");
						e1.printStackTrace();
					}
						
						catch(InvocationTargetException e1) {
							
							e1.printStackTrace();
							JOptionPane.showMessageDialog(DashBoardView.this, "some internal error occured while loading dashboard");
						}
					}
				});
				file.add(menuItem);
			}
		}
		
	}
	public DashBoardView() {
		file= new JMenu("File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 607);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(file);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	welcomeLbl = new JLabel("");
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		welcomeLbl.setBounds(233, 78, 290, 51);
		contentPane.add(welcomeLbl);
	}
}
