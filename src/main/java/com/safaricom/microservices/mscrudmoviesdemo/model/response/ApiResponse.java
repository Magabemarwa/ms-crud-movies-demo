package com.safaricom.microservices.mscrudmoviesdemo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude
public class ApiResponse extends ModelApiResponse implements Serializable {

    public ApiResponse() {
    }

    public static ApiResponse responseFormatter(String referenceId, int responseCode, String technicalMessage,
                                                String customerMessage, Object responseBody){

        ApiResponse apiResponse = new ApiResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        headerResponse.setCustomerMessage(customerMessage);
        headerResponse.setRequestRefId(referenceId);
        headerResponse.setResponseMessage(technicalMessage);
        headerResponse.setTimestamp(LocalDateTime.now().toString());
        headerResponse.setResponseCode(responseCode);
        apiResponse.setHeader(headerResponse);
        apiResponse.setBody(responseBody);
        return apiResponse;
    }
}
