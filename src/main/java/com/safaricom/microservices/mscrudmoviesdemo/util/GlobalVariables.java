package com.safaricom.microservices.mscrudmoviesdemo.util;

public class GlobalVariables {

    private GlobalVariables() {
        // empty constructor
    }

    public static final String LOGGER_FORMAT = "TransactionID={} | TransactionType={} | Process={} |"
            + " ProcessDuration={} | Msisdn={} | SourceSystem={} | TargetSystem={}  | CustomerMessage={} | ResponseCode={}  | ResponseMsg={} | "
            + "ErrorDescription={} | RequestPayload={} | ResponsePayload={} | RequestHeaders={} ";
    public static final String CUSTOMER_MESSAGE_SUCCESS_UPDATE_MOVIE = "Movie updated successfully";
    public static final String X_SOURCE_SYSTEM = "x-source-system";
    public static final String X_CORRELATION_CONVERSATION_ID = "x-conversation-correlation-id";
    public static final String CORRELATION_REGEX = "[0-9]+";
}
