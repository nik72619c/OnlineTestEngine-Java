package com.nikhil.testengine.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.nikhil.testengine.question.QuestionDTO;
import com.nikhil.testengine.question.questionUploadDAO;

public class TakeTestView extends JFrame {

	private JPanel contentPane;
	private ArrayList<QuestionDTO> questions;
	private int index=0;
	private int time=0;
	ButtonGroup bg= new ButtonGroup();
	Timer timer;
	JLabel qid;
	JLabel quesname = new JLabel("");
	JRadioButton ans1 = new JRadioButton("");
	JRadioButton ans2 = new JRadioButton("");
	JRadioButton ans3 = new JRadioButton("");
	JRadioButton ans4 = new JRadioButton("");
	JButton prevButton = new JButton("PREV");
	JButton nextButton = new JButton("NEXT");
	JButton finishTest = new JButton("FINISH  TEST");
	JLabel timeLeft = new JLabel("TIME LEFT :- ");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakeTestView frame = new TakeTestView();
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
	
	public void saveAns(int index) {
		
//		index--;
	if(ans1.isSelected()) {
		
		questions.get(index).setYourAns("a");
	}
	
	else if(ans2.isSelected()) {
		
		questions.get(index).setYourAns("b");
		
	}
	
	else if(ans3.isSelected()) {
		
		questions.get(index).setYourAns("c");
		
	}
	
	else if(ans4.isSelected()) {
		
		questions.get(index).setYourAns("d");
		
	}
	}
	
	public void enableDisableButtons() {
		
		if(questions.size()==1) {
			
			prevButton.setEnabled(false);
			nextButton.setEnabled(false);
		}
		
		else if(index==0) {
			
			prevButton.setEnabled(false);
			
			if(index<questions.size()) {
				nextButton.setEnabled(true);
			}
			
			else {
				
				nextButton.setEnabled(false);
			}
			
		}
		
		else if(index==questions.size()-1) {
			
			prevButton.setEnabled(true);
			nextButton.setEnabled(false);
			
		}
		else if(index<questions.size()) {
			
			prevButton.setEnabled(true);
			nextButton.setEnabled(true);
			
			
		}
	}
	
	public void printUserAns(QuestionDTO question,int index) {
		bg.clearSelection();
		if(question.getYourAns()!=null) {
			if(question.getYourAns().equals("a")) {
				
				ans1.setSelected(true);
			}
			
			else if(question.getYourAns().equals("b")) {
				
				ans2.setSelected(true);
			}

			else if(question.getYourAns().equals("c")) {
		
		ans3.setSelected(true);
	}

			else if(question.getYourAns().equals("d")) {
		
		ans4.setSelected(true);
	}
			
			else {
				
				bg.clearSelection();
				
			}
			
			}
		
		
	}
	
	
	public void printQuestions(int index) {
		
		QuestionDTO question= TakeTestView.this.questions.get(index);
		qid.setText(String.valueOf(question.getId()));
		quesname.setText(question.getName());
		ans1.setText(question.getAns1());
		ans2.setText(question.getAns2());
		ans3.setText(question.getAns3());
		ans4.setText(question.getAns4());
		
//		enableDisableButtons();
			printUserAns(question, index);
		enableDisableButtons();
		
	}
	
	public void setTimer() {
		this.time=60;
		timer= new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(TakeTestView.this.time!=0) {

                    timeLeft.setText("Time Left :-"+ TakeTestView.this.time);
				}
				
				if(TakeTestView.this.time==0) {
					timeLeft.setText("Time Left :-"+ 0);
					timer.stop();
					submitTest();
				}
				
				TakeTestView.this.time--;
			}
		});
		timer.start();
	}
	
	public void submitTest() {
		
		saveAns(index);
		int rightanswers=0;
		System.out.println("q.1 ans "+this.questions.get(0).getYourAns());
		System.out.println("q.2 ans "+this.questions.get(1).getYourAns());
		
	
		for(QuestionDTO question : this.questions) {
			
			if(question.getYourAns()==null) {
				
				question.setYourAns(" ");
			}
			
			else if(question.getYourAns().equals(question.getRans())) {
				
				rightanswers+=1;
			}
		}
		System.out.println("right answeers were : "+ rightanswers);
		
		ResultView rv= new ResultView(questions);
		rv.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}
	public void loadQuestions() {
		

		
		questionUploadDAO questionDAO = new questionUploadDAO();
		try {
			
			this.questions=questionDAO.getQuestions();
			
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Some DB error in loading questions !");
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(this,"Could not load questions !");
		}
	}
	public TakeTestView() {
		loadQuestions();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bg.add(ans1);
		bg.add(ans2);
		bg.add(ans3);
		bg.add(ans4);
		
		qid = new JLabel("");
		qid.setFont(new Font("Tahoma", Font.BOLD, 14));
		qid.setBounds(30, 63, 71, 36);
		contentPane.add(qid);
		
		
		quesname.setFont(new Font("Tahoma", Font.BOLD, 14));
		quesname.setBounds(111, 63, 726, 36);
		contentPane.add(quesname);
		
		
		ans1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ans1.setBounds(111, 141, 532, 36);
		contentPane.add(ans1);
		
		
		ans2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ans2.setBounds(111, 196, 532, 36);
		contentPane.add(ans2);
		
	
		ans3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ans3.setBounds(111, 255, 532, 36);
		contentPane.add(ans3);
		
	
		ans4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ans4.setBounds(111, 315, 532, 36);
		contentPane.add(ans4);
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveAns(index);
				index--;
				printQuestions(index);
			}
		});
		
		
		prevButton.setBounds(111, 399, 85, 21);
		contentPane.add(prevButton);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                saveAns(index);
				index++;
				printQuestions(index);
			}
		});
		
		
		nextButton.setBounds(230, 399, 85, 21);
		contentPane.add(nextButton);
		finishTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				submitTest();
			}
		});
		
		
		finishTest.setBounds(354, 399, 135, 21);
		contentPane.add(finishTest);
		
		
		timeLeft.setFont(new Font("Tahoma", Font.PLAIN, 13));
		timeLeft.setBounds(699, 10, 138, 43);
		contentPane.add(timeLeft);
		printQuestions(this.index);
	
		setTimer();
	}
}
