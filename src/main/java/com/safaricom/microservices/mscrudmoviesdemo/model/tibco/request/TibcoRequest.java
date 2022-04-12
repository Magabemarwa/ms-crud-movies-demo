package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "customerAccountRequest"
})
public class TibcoRequest {

    @JsonProperty("customerAccountRequest")
    private CustomerAccountRequest1 customerAccountRequest1;

    public CustomerAccountRequest1 getCustomerAccountRequest1() {
        return customerAccountRequest1;
    }

    public void setCustomerAccountRequest1(CustomerAccountRequest1 customerAccountRequest1) {
        this.customerAccountRequest1 = customerAccountRequest1;
    }
}
