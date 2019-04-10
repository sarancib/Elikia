package Candidatures;

import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.scene.control.Cell;

public class test {

	  public static void main(
	      String[] args) {
	    
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("ma feuille");
	    HSSFRow row = sheet.createRow(0);
	    HSSFCell cell = row.createCell((short)0);
	    cell.setCellValue("Candidacy s name");

	    row.createCell((short)1).setCellValue("candidate");
	    
	    
	    FileOutputStream fileOut;
	    try {
	    	
	      fileOut = new FileOutputStream("D:/monfichier1.xls");
	      wb.write(fileOut);
	      fileOut.close(); 
	      System.out.println("cv");
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	}