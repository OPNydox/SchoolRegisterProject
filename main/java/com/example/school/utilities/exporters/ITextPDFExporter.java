package com.example.school.utilities.exporters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.utilities.exporters.interfaces.IPDFExporter;
import com.example.school.utilities.interfaces.IWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class ITextPDFExporter implements IPDFExporter {
	@Autowired
	private IWriter writer;
	
	@Override
	public void testExporter(String fileName) {
		Document exportedDocument = new Document();
		
		fileName = getPdfFileName(fileName);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(exportedDocument, new FileOutputStream(fileName));
			exportedDocument.open();
			exportedDocument.add(new Paragraph("Hello World from a PDF Document"));
			exportedDocument.close();
			writer.close();
		} catch (DocumentException e) {
			writer.writeError(e.getMessage());
		} catch (FileNotFoundException e) {
			writer.writeError(e.getMessage());
		}
	}
	
	@Override
	public void exportStudents(List<Student> students, String fileName) {
		Document document = new Document();
		String studentGrades;
		
		
		fileName = getPdfFileName(fileName);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			PdfPTable table = new PdfPTable(3); 
	        table.setWidthPercentage(100); 
	        table.setSpacingBefore(10f); 
	        table.setSpacingAfter(10f);
	        float[] columnWidths = {1f, 1f, 1f};
	        table.setWidths(columnWidths);
	        
	        
	        PdfPCell nameCell = new PdfPCell(new Paragraph("Name"));
	        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        nameCell.setVerticalAlignment(Element.ALIGN_CENTER);
	        
	        PdfPCell emailCell = new PdfPCell(new Paragraph("Email"));
	        emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        emailCell.setVerticalAlignment(Element.ALIGN_CENTER);
	        
	        PdfPCell gradesCell = new PdfPCell(new Paragraph("Grades"));
	        gradesCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        gradesCell.setVerticalAlignment(Element.ALIGN_CENTER);
	        
	        table.addCell(nameCell);
	        table.addCell(emailCell);
	        table.addCell(gradesCell);
	        table.completeRow();
	        
	        for (Student s : students) {
	        	nameCell = new PdfPCell(new Paragraph(s.getName()));
	        	emailCell = new PdfPCell(new Paragraph(s.getEmail()));
	        	studentGrades = getGradesForStudent(s);
	        	gradesCell = new PdfPCell(new Paragraph(studentGrades));
	        	
	        	table.addCell(nameCell);
	        	table.addCell(emailCell);
	        	table.addCell(gradesCell);
	        	
	        	table.completeRow();
	        }
	        
	        document.add(table);
	        document.close();
			writer.close();
		} catch (DocumentException e) {
			writer.writeError(e.getMessage());
		} catch (FileNotFoundException e) {
			writer.writeError(e.getMessage());
		}
		
	}

	private String getPdfFileName(String name) {
		if(!name.endsWith(".pdf")) {
			name = name.concat(".pdf");
			return name;
		}
		
		return name;
	}
	
	private String getGradesForStudent(Student student) {
		List<Grade> grades = List.copyOf(student.getGrades());
		List<String> numberGrades = new ArrayList<>();
		String result;
		
		if (grades.isEmpty()) {
			return "";
		}
		
		for (Grade g : grades) {
			numberGrades.add(Double.toString(g.getMark()));
		}
		
		result = String.join(", ", numberGrades);
		
		return result;
	}

}
