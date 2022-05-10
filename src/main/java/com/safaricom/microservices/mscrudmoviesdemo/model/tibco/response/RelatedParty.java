package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedParty {
    @JsonProperty("resultCode")
    private String resultCode;
    @JsonProperty("ResultDesc")
    private String resultDesc;
    @JsonProperty("PrimaryIdentity")
    private String primaryIdentity;
    @JsonProperty("OfferingID")
    private String offeringId;

    public void setResultCode(String resultCode){
        this.resultCode = resultCode;
    }
    public String getResultCode(){
        return this.resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getPrimaryIdentity() {
        return primaryIdentity;
    }

    public void setPrimaryIdentity(String primaryIdentity) {
        this.primaryIdentity = primaryIdentity;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }
}
