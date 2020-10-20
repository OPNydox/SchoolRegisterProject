package com.example.school.utilities;

public class NumberHandler {
    public static ServiceReturnResult parseStringToLong(String string) {
        ServiceReturnResult result = new ServiceReturnResult();
        Long paresedLong = null;
        
        try {
            paresedLong = Long.parseLong(string);
            result.setReturnResultObject(paresedLong);
        } catch (NumberFormatException e) {
            result.addErrorMsg(e.getMessage());
        }

		return result;
	}
}
