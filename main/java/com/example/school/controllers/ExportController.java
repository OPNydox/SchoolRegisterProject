package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.school.services.interfaces.IExporterService;

@Controller
public class ExportController {
	@Autowired
	private IExporterService exportService;
	
	@PostMapping(value = "/testexp")
	private void testExport(@RequestParam String fileName) {
		exportService.testExporter(fileName);
	}
	
	@PostMapping(value = "/exportstud")
	private void exportStudents(@RequestParam String fileName) {
		exportService.exportStudents(fileName);
	}
	
}
