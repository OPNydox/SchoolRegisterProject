package com.example.school.factories.interfaces;

import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.ViewModel;

public interface EntityFactory {
    ServiceReturnResult getEntity(ViewModel viewModel);
}
