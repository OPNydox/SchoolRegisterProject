package com.example.school.utilities.interfaces;

import java.util.List;

public interface IWriter {
	void writeError(String text);
	
	void writeErrors(List<String> errors);
}
