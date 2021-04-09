package com.ecommerceproject.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ecommerceproject.entities.Order;

public class ExcelExporter {
	
	public static ByteArrayInputStream exportToExcel(Order order) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Order");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Product Designation");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Price");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Quantity");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Sub Total");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each customer
//	        for(int i = 0; i < customers.size(); i++) {
//	        	Row dataRow = sheet.createRow(i + 1);
//	        	dataRow.createCell(0).setCellValue(customers.get(i).getFirstName());
//	        	dataRow.createCell(1).setCellValue(customers.get(i).getLastName());
//	        	dataRow.createCell(2).setCellValue(customers.get(i).getMobileNumber());
//	        	dataRow.createCell(3).setCellValue(customers.get(i).getEmail());
//	        }
	        
	        for(int i = 0; i < order.getOrderLines().size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(order.getOrderLines().get(i).getProduct().getDesignation());
	        	dataRow.createCell(1).setCellValue(order.getOrderLines().get(i).getProduct().getPrice().doubleValue());
	        	dataRow.createCell(2).setCellValue(order.getOrderLines().get(i).getQuantity());
	        	dataRow.createCell(3).setCellValue(order.getOrderLines().get(i).getQuantity() * order.getOrderLines().get(i).getProduct().getPrice().doubleValue());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
