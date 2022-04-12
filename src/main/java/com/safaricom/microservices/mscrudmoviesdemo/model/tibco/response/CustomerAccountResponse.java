package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

public class CustomerAccountResponse {
    private RelatedParty relatedParty;

    private BillingInfo BillingInfo;

    public void setRelatedParty(RelatedParty relatedParty){
        this.relatedParty = relatedParty;
    }
    public RelatedParty getRelatedParty(){
        return this.relatedParty;
    }
    public void setBillingInfo(BillingInfo BillingInfo){
        this.BillingInfo = BillingInfo;
    }
    public BillingInfo getBillingInfo(){
        return this.BillingInfo;
    }
}
