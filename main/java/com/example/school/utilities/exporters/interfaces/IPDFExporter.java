package com.example.school.utilities.exporters.interfaces;

import java.util.List;

import com.example.school.database.entities.Student;

public interface IPDFExporter {
	void testExporter(String fileName);
	
	void exportStudents(List<Student> students, String fileName);
}
