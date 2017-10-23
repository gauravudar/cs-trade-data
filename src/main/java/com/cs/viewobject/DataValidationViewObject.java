package com.cs.viewobject;

import java.util.List;


public class DataValidationViewObject {

    private List<DataValidationError> validationErrors;

    public DataValidationViewObject() {
    }

    public DataValidationViewObject(List<DataValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

	public List<DataValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<DataValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public void addError(DataValidationError dataValidationError) {
		validationErrors.add(dataValidationError);
	}
   
}
