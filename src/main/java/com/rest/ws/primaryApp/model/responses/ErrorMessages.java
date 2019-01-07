package com.rest.ws.primaryApp.model.responses;

import lombok.Getter;
import lombok.Setter;


public enum ErrorMessages {
    MISSING_REQUIRED_FIELDS("Missing required fields. Check the documentation of the field"),
    RECORD_ALREADY_EXISTS("Reocords already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    RECORD_NOT_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Couldn't update the record"),
    COULD_NOT_DELETE_RECORD("Couldn't delete the record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email cannot be verified");

    public String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
