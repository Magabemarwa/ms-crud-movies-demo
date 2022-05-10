package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillingInfo {
    @JsonProperty("ActivationDate")
    private String activationDate;
    @JsonProperty("ExpirationDate")
    private String expirationDate;
    @JsonProperty("ChargedAmount")
    private String chargedAmount;

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(String chargedAmount) {
        this.chargedAmount = chargedAmount;
    }
}
