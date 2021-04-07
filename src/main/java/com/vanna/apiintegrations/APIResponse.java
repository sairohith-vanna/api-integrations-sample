package com.vanna.apiintegrations;

public class APIResponse {

    public APIResponse() {
    }

    public APIResponse(Object response, String status, String statusMessage) {
        this.response = response;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    private Object response;
    private String status;
    private String statusMessage;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
