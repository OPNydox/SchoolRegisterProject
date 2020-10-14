package com.example.school.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Student;
import com.example.school.services.interfaces.IExporterService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.exporters.interfaces.IPDFExporter;

@Service
public class ExporterServiceImpl implements IExporterService {
	@Autowired
	private IPDFExporter pdfExporter;
	
	@Autowired
	private IStudentService studentService;

	@Override
	public void testExporter(String fileName) {
		pdfExporter.testExporter(fileName);
	}

	@Override
	public void exportStudents(String fileName) {
		List<Student> students = studentService.findAllStudents();
		
		pdfExporter.exportStudents(students, fileName);
		
	}

}
