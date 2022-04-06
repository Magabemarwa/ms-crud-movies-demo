package com.safaricom.microservices.mscrudmoviesdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.safaricom.microservices.mscrudmoviesdemo.util.GlobalVariables.LOGGER_FORMAT;

public class LogsManager {

    private static final Logger LOG = LoggerFactory.getLogger(LogsManager.class);

    public LogsManager() {
        // Empty constructor
    }

    public static void info(String requestId, String transactionType, String process, String processDuration,
                            String msisdn, String sourceSystem, String targetSystem, String customerMessage, int responseCode,
                            String responseMsg, String errorDescription, String requestPayload, String responsePayload,
                            String headers) {

        LOG.info(LOGGER_FORMAT, requestId, transactionType, process, processDuration, msisdn, sourceSystem,
                targetSystem, customerMessage, responseCode, responseMsg, errorDescription, requestPayload, responsePayload,
                headers);

    }

    public static void error(String requestId, String transactionType, String process, String processDuration,
                             String msisdn, String sourceSystem, String targetSystem, String response, int responseCode,
                             String responseMsg, String errorDescription, String requestPayload, String responsePayload,
                             String headers) {

        LOG.error(LOGGER_FORMAT, requestId, transactionType, process, processDuration, msisdn, sourceSystem,
                targetSystem, response, responseCode, responseMsg, errorDescription, requestPayload, responsePayload,
                headers);

    }
}
