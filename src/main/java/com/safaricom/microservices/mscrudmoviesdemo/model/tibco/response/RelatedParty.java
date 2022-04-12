package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

public class RelatedParty {
    private String resultCode;

    private String ResultDesc;

    private String PrimaryIdentity;

    private String OfferingID;

    public void setResultCode(String resultCode){
        this.resultCode = resultCode;
    }
    public String getResultCode(){
        return this.resultCode;
    }
    public void setResultDesc(String ResultDesc){
        this.ResultDesc = ResultDesc;
    }
    public String getResultDesc(){
        return this.ResultDesc;
    }
    public void setPrimaryIdentity(String PrimaryIdentity){
        this.PrimaryIdentity = PrimaryIdentity;
    }
    public String getPrimaryIdentity(){
        return this.PrimaryIdentity;
    }
    public void setOfferingID(String OfferingID){
        this.OfferingID = OfferingID;
    }
    public String getOfferingID(){
        return this.OfferingID;
    }
}
