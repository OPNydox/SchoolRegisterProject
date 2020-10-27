package com.example.school.viewModels.decorators.dataValidators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;

import org.springframework.stereotype.Component;

@Component
public class IdValidator {
    
    public List<String> validate(String id) {
        List<String> result = new ArrayList<>();
        ServiceReturnResult parseResult;
        Long idNumber;

        if (id == null) {
            result.add("Id is null");
            return result;
        }

        parseResult = NumberHandler.parseStringToLong(id);

        if (!parseResult.isSuccessful()) {
            result.addAll(parseResult.getErrorMessages());
            return result;
        }

        idNumber = (Long) parseResult.getReturnResultObject();

        if (idNumber < 0) {
            result.add("Id " + id + " is invalid.");
        }
        
        return result;
    }
}
