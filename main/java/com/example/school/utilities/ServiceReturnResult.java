package com.example.school.utilities;

import java.util.ArrayList;
import java.util.List;

public class ServiceReturnResult<Т> {
    private List<String> errorMessages;

    private Т returnResultObject;

    public ServiceReturnResult() {
        errorMessages = new ArrayList<>();
    }

    public void setReturnResultObject(Т obj) {
        this.returnResultObject = obj;
    }

    public Т getReturnResultObject() {
        return returnResultObject;
    }

    public void addErrorMsg(String message) {
        errorMessages.add(message);
        
    }

    public void addErrorMsg(ServiceReturnResult<Т> returnResult) {
        this.addErrorMsg(returnResult.getErrorMessages());
    }

    public void addErrorMsg(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();
        result.addAll(errorMessages);
        return result;
    }

    public Boolean hasErrors() {
        if (errorMessages.isEmpty()) {
            return false;
        }
        return true;
    }
}
