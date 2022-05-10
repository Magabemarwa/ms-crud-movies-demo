package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request;

import javax.validation.constraints.NotNull;

public class ApiRequest {

    @NotNull(message = "Primary identity cannot be null")
    private String primaryIdentity;

    public String getPrimaryIdentity() {
        return primaryIdentity;
    }

    public void setPrimaryIdentity(String primaryIdentity) {
        this.primaryIdentity = primaryIdentity;
    }
}
