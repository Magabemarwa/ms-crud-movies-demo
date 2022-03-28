package com.safaricom.microservices.mscrudmoviesdemo.model.response;

public class HeaderResponse {

    private String requestRefId;
    private String responseMessage;
    private String timestamp;
    private String customerMessage;
    private Integer responseCode;

    public String getRequestRefId() {
        return requestRefId;
    }

    public void setRequestRefId(String requestRefId) {
        this.requestRefId = requestRefId;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
