package com.nikhil.testengine.question.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.nikhil.testengine.question.QuestionDTO;

public class jTableModel extends AbstractTableModel {

	
	private String columns[] = { "qid","question","Right Answer","Your Answer","Score" };
	private ArrayList<QuestionDTO> questions= new ArrayList<>();
	
	 public jTableModel(ArrayList<QuestionDTO> questions) {
		 
		
		 this.questions= questions;
	}
	
		@Override
		public String getColumnName(int column) {
			
				return columns[column];
			
		}
		
		
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return questions.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		QuestionDTO questionDTO= this.questions.get(rowIndex);
		
		switch(columnIndex) {	
			case 0:
				return questionDTO.getId();
			case 1:
				return questionDTO.getName();
			case 2:
				return questionDTO.getRans();
			case 3:
				return questionDTO.getYourAns();
			case 4:	
				return questionDTO.getScore();
		}
	
			return null;
		
	    }
	}
	
	


