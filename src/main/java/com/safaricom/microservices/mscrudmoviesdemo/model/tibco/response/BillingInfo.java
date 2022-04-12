package com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response;

public class BillingInfo {
    private String ActivationDate;

    private String ExpirationDate;

    private String ChargedAmount;

    public void setActivationDate(String ActivationDate){
        this.ActivationDate = ActivationDate;
    }
    public String getActivationDate(){
        return this.ActivationDate;
    }
    public void setExpirationDate(String ExpirationDate){
        this.ExpirationDate = ExpirationDate;
    }
    public String getExpirationDate(){
        return this.ExpirationDate;
    }
    public void setChargedAmount(String ChargedAmount){
        this.ChargedAmount = ChargedAmount;
    }
    public String getChargedAmount(){
        return this.ChargedAmount;
    }
}
