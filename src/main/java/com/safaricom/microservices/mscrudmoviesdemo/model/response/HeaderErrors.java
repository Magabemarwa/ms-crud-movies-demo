package com.safaricom.microservices.mscrudmoviesdemo.model.response;


public class HeaderErrors {

    private String headerName;
    private Boolean missingHeader;
    private Boolean headerMismatch;
    private String headerErrorMessage;

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderErrorMessage() {
        return headerErrorMessage;
    }

    public void setHeaderErrorMessage(String headerErrorMessage) {
        this.headerErrorMessage = headerErrorMessage;
    }

    public Boolean getMissingHeader() {
        return missingHeader;
    }

    public void setMissingHeader(Boolean missingHeader) {
        this.missingHeader = missingHeader;
    }

    public Boolean getHeaderMismatch() {
        return headerMismatch;
    }

    public void setHeaderMismatch(Boolean headerMismatch) {
        this.headerMismatch = headerMismatch;
    }
}
