package com.example.school.viewModels.Interfaces;

public class ViewModel implements IViewModel {

    private boolean isEmpty;

    @Override
    public boolean isEmpty() {
        return this.isEmpty;
    }

    @Override
    public boolean setEmpty() {
        this.isEmpty = true;
        return true;
    }
    
}
