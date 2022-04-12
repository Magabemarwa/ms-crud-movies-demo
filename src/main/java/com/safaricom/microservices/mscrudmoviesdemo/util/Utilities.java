package com.safaricom.microservices.mscrudmoviesdemo.util;

import com.safaricom.microservices.mscrudmoviesdemo.model.response.HeaderErrors;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.http.HttpHeaders;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    private Utilities() {
        // Empty constructor
    }

    public static HeaderErrors checkIfMissingCorrelationHeader(HttpHeaders headers){
        HeaderErrors errors = new HeaderErrors();
        if(!headers.containsKey(GlobalVariables.X_CORRELATION_CONVERSATION_ID)){
            errors.setMissingHeader(Boolean.TRUE);
            errors.setHeaderMismatch(Boolean.FALSE);
            errors.setHeaderName(GlobalVariables.X_CORRELATION_CONVERSATION_ID);
            errors.setHeaderErrorMessage("Missing correlation header");
        } else if(headers.containsKey(GlobalVariables.X_CORRELATION_CONVERSATION_ID)
                && Utilities.checkCorrelationHeaderPattern(Objects.requireNonNull(headers.get(GlobalVariables.X_CORRELATION_CONVERSATION_ID)).get(0))
        .equals(Boolean.FALSE)){
            errors.setMissingHeader(Boolean.FALSE);
            errors.setHeaderMismatch(Boolean.TRUE);
            errors.setHeaderName(GlobalVariables.X_CORRELATION_CONVERSATION_ID);
            errors.setHeaderErrorMessage("Header mismatch");
        }
        errors.setMissingHeader(Boolean.FALSE);
        errors.setHeaderMismatch(Boolean.FALSE);
        return errors;
    }

    public static Boolean checkCorrelationHeaderPattern(String header){
        Pattern pattern = Pattern.compile(GlobalVariables.CORRELATION_REGEX);
        Matcher matcher = pattern.matcher(header);
        if(matcher.matches()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
