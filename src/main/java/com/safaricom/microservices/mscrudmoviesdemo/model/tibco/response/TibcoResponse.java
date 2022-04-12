package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TibcoResponse {
    @JsonProperty("customerAccountResponse")
    private CustomerAccountResponse1 customerAccountResponse1;

    public CustomerAccountResponse1 getCustomerAccountResponse1() {
        return customerAccountResponse1;
    }

    public void setCustomerAccountResponse1(CustomerAccountResponse1 customerAccountResponse1) {
        this.customerAccountResponse1 = customerAccountResponse1;
    }
}
