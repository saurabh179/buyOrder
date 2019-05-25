package com.example.buyOrder.model;

public class SuccessResponseDTO {

    Boolean success;
    String message;
    String email;

    public SuccessResponseDTO(Boolean success, String message, String email) {
        this.success = success;
        this.message = message;
        this.email = email;
    }

    public SuccessResponseDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
