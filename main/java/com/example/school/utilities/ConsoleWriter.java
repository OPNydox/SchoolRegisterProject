package com.example.school.utilities;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.school.utilities.interfaces.IWriter;

@Component
public class ConsoleWriter implements IWriter{

	@Override
	public void writeError(String text) {
		System.out.println(text);
	}

	@Override
	public void writeErrors(List<String> errors) {
		for (String e : errors) {
			System.out.println(e);
		}
	}

}
