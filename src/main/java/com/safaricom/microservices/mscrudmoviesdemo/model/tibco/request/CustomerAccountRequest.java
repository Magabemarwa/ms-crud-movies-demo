package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "primaryIdentity"
})
public class CustomerAccountRequest {
    @JsonProperty("primaryIdentity")
    private String primaryIdentity;

    public void setPrimaryIdentity(String primaryIdentity){
        this.primaryIdentity = primaryIdentity;
    }
    public String getPrimaryIdentity(){
        return this.primaryIdentity;
    }
}
