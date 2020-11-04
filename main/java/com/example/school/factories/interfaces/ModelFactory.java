package com.example.school.factories.interfaces;

import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.Interfaces.ViewModel;

public interface ModelFactory {
    ServiceReturnResult getEntity(ViewModel viewModel);
}
