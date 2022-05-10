package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerAccountResponse {
    @JsonProperty("relatedParty")
    private RelatedParty relatedParty;
    @JsonProperty("BillingInfo")
    private BillingInfo billingInfo;

    public void setRelatedParty(RelatedParty relatedParty){
        this.relatedParty = relatedParty;
    }
    public RelatedParty getRelatedParty(){
        return this.relatedParty;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
}
