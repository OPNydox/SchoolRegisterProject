package com.example.school.utilities;

import java.util.ArrayList;
import java.util.List;

public class ServiceReturnResult implements Cloneable {
    private boolean isSuccessful;

    private List<String> errorMessages;

    private Object returnResultObject;

    public ServiceReturnResult() {
        errorMessages = new ArrayList<>();
    }

    public void setReturnResultObject(Object obj) {
        this.returnResultObject = obj;
    }
    
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public Object getReturnResultObject() {
        return returnResultObject;
    }

    public boolean isSuccessful() {
        if (errorMessages.isEmpty()) {
            return true;
        }

        return false;
    }

    public void addErrorMsg (String msg) {
        this.errorMessages.add(msg);
    }

    public void addErrorMsg (List<String> msgs) {
        this.errorMessages.addAll(msgs);
    }

    public ServiceReturnResult getCopy() throws CloneNotSupportedException {
        return (ServiceReturnResult) this.clone();
    }
}
