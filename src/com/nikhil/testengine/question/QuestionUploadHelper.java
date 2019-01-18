package com.nikhil.testengine.question;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.nikhil.testengine.pathconstants.PathConstants;
import com.nikhil.testengine.util.CommonUtils;

public class QuestionUploadHelper {
	
	public Boolean uploadQuestion(String path) throws IOException, SQLException {
	
		
		boolean isUploaded=false;
		File file =new File(path);
		
		if(file.exists()) {
			int EOF=-1;
			FileInputStream fi= new FileInputStream(path);
			BufferedInputStream bi= new BufferedInputStream(fi);
			String filename=CommonUtils.getFileName(path);
			String fullName=PathConstants.UPLOAD_PATH+filename;
			FileOutputStream fo= new FileOutputStream(fullName);
			BufferedOutputStream bo= new BufferedOutputStream(fo);
			int singleByte=bi.read();
			
			while(singleByte!=EOF) {
				
				bo.write(singleByte);
				singleByte=bi.read();
				
			}
			isUploaded=true;
			
			bo.close();
			fo.close();
			bi.close();
			fi.close();
			
			
		System.out.println("Bingo ! the file uploaded successfully !");
		writeFileToDatabase(fullName);
		}
		
		else {
			System.out.println("Could not upload the file ! kindly check");
			
			
		}
		
		return isUploaded;
		
		
	}
	
	public void writeFileToDatabase(String path) throws IOException, SQLException {
		
		ArrayList<QuestionDTO> questionList= new ArrayList<>();
		FileInputStream fs= new FileInputStream(path);
		int cellCounter=0;
		boolean isFirstRowPass=false;
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet=workbook.getSheetAt(0);
	Iterator<Row> rows=sheet.rowIterator();
	
	while(rows.hasNext()) {
		
		Row currentRow = rows.next();
		if(!isFirstRowPass){
			isFirstRowPass = true;
			continue;
		}
		cellCounter=0;
		QuestionDTO questionDTO = new QuestionDTO();
		Iterator<Cell> cells = currentRow.cellIterator();
		while(cells.hasNext()){
			Cell currentCell = cells.next();
			cellCounter++;
			if(cellCounter==1){
				questionDTO.setId((int)currentCell.getNumericCellValue());
			}
			else
			if(cellCounter==2){
				questionDTO.setName(currentCell.getStringCellValue());
			}
			else
				if(cellCounter==3){
					questionDTO.setAns1(currentCell.getStringCellValue());
				}
				else
					if(cellCounter==4){
						questionDTO.setAns2(currentCell.getStringCellValue());
					}
					else
						if(cellCounter==5){
							questionDTO.setAns3(currentCell.getStringCellValue());
						}
						else
							if(cellCounter==6){
								questionDTO.setAns4(currentCell.getStringCellValue());
							}
							else
								if(cellCounter==7){
									questionDTO.setRans(currentCell.getStringCellValue());
								}
								else
									if(cellCounter==8){
										questionDTO.setScore((int)currentCell.getNumericCellValue());
									}
			
			
		} // Cell Loop Ends
		questionList.add(questionDTO);
	} // Row Loop Ends
	
	
	System.out.println("Questions are :: "+questionList);
	try {
		System.out.println("inside the try section of the excel reading class going for bulk upload");
		questionUploadDAO questionuploadDAO= new questionUploadDAO();
		questionuploadDAO.bulkUploadQuestions(questionList);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("inside the class not found exception of the QuestionUploadHelper ");
		e.printStackTrace();
		
	} 
	
//	catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
	}

}








