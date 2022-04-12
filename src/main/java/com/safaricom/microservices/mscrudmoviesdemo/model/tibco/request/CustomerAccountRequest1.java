package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "customerAccountRequest"
})
public class CustomerAccountRequest1 {
    @JsonProperty("customerAccountRequest")
    private CustomerAccountRequest customerAccountRequest;

    public void setCustomerAccountRequest(CustomerAccountRequest customerAccountRequest){
        this.customerAccountRequest = customerAccountRequest;
    }
    public CustomerAccountRequest getCustomerAccountRequest(){
        return this.customerAccountRequest;
    }
}
