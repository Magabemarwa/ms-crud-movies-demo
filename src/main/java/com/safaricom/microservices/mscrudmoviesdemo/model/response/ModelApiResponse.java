package com.safaricom.microservices.mscrudmoviesdemo.model.response;



public class ModelApiResponse {
    private HeaderResponse header;
    private Object body;

    public HeaderResponse getHeader() {
        return header;
    }

    public void setHeader(HeaderResponse header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
