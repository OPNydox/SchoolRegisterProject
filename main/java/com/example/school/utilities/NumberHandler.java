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
        } finally {
            if (result.isSuccessful() && paresedLong == null) {
                result.addErrorMsg("Long parse error");
            }
        }

		return result;
    }
    
    public static ServiceReturnResult parseStringToDouble(String string) {
        ServiceReturnResult result = new ServiceReturnResult();
        Double parsedDouble = null;

        try {
            parsedDouble = Double.parseDouble(string);
            result.setReturnResultObject(parsedDouble);
        } catch (NumberFormatException e) {
            result.addErrorMsg(e.getMessage());
        } finally {
            if (result.isSuccessful() && parsedDouble == null) {
                result.addErrorMsg("Double parse error");
            }
        }

        return result;
    }

    public static ServiceReturnResult parseStringToInteger(String string) {
        ServiceReturnResult result = new ServiceReturnResult();
        Integer parsedDouble = null;

        try {
            parsedDouble = Integer.parseInt(string);
            result.setReturnResultObject(parsedDouble);
        } catch (NumberFormatException e) {
            result.addErrorMsg(e.getMessage());
        } finally {
            if (result.isSuccessful() && parsedDouble == null) {
                result.addErrorMsg("Integer parse error");
            }
        }

        return result;
    }
}
