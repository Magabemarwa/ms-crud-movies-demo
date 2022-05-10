package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerAccountResponse1 {
    @JsonProperty("customerAccountResponse")
    private CustomerAccountResponse customerAccountResponse;

    public void setCustomerAccountResponse(CustomerAccountResponse customerAccountResponse){
        this.customerAccountResponse = customerAccountResponse;
    }
    public CustomerAccountResponse getCustomerAccountResponse(){
        return this.customerAccountResponse;
    }
}
