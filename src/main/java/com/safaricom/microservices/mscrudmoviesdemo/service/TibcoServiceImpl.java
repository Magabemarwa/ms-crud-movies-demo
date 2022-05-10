package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safaricom.microservices.mscrudmoviesdemo.config.ConfigProperties;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.ApiRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.CustomerAccountRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.CustomerAccountRequest1;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.TibcoRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response.BillingInfo;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response.RelatedParty;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response.TibcoResponse;
import com.safaricom.microservices.mscrudmoviesdemo.security.EncrypDecryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;


@Service
public class TibcoServiceImpl implements TibcoService {

    private static final Logger LOG = LoggerFactory.getLogger(TibcoServiceImpl.class);

    private final WebClient webClient;
    private final ConfigProperties properties;

    @Autowired
    private EncrypDecryptor encrypDecryptor;

    public TibcoServiceImpl(WebClient webClient, ConfigProperties properties) {
        this.webClient = webClient;
        this.properties = properties;
    }

    @PostConstruct
    public void init(){
        System.out.println(properties.getAppProfile() + " " + properties.getAppVersion()
                + " " + properties.getDbPassword() + " " + properties.getDbUsername());
        System.out.println("DB Username-encrypted " + encrypDecryptor
        .encryptText(properties.getDbUsername()));
        System.out.println("DB Password-encrypted " + encrypDecryptor
                .encryptText(properties.getDbPassword()));
    }

    @Override
    public Mono<ApiResponse> makeTibcoCall(ApiRequest apiRequest) {
        Mono<TibcoResponse> tibcoResponseMono = formatAndMakeRequestToTibco(apiRequest);

        return tibcoResponseMono.flatMap(tibcoResponse -> {
            LOG.info("json string {}", jsonToString(tibcoResponse));
            LOG.info(tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
                    .getRelatedParty().getResultCode());
//            LOG.info("Billing data {}", tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
//                    .getBillingInfo().getChargedAmount());
            if (tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
                    .getRelatedParty().getResultCode().equals("0")) {
                BillingInfo billingInfo = tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
                        .getBillingInfo();
                return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 200,
                        "Success", "Billing info fetched successfully", billingInfo));
            } else if (tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
                    .getRelatedParty().getResultCode().equals("118030260")) {
                RelatedParty relatedParty = tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
                        .getRelatedParty();
                return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 400,
                        "Failed", relatedParty.getResultDesc(), null));
            }
            return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 400,
                    "Failed", "Billing info fetch failed", null));
        });
    }


    private Mono<TibcoResponse> formatAndMakeRequestToTibco(ApiRequest apiRequest) {
        TibcoRequest tibcoRequest = new TibcoRequest();
        CustomerAccountRequest customerAccountRequest = new CustomerAccountRequest();
        CustomerAccountRequest1 customerAccountRequest1 = new CustomerAccountRequest1();
        customerAccountRequest.setPrimaryIdentity(apiRequest.getPrimaryIdentity());
        customerAccountRequest1.setCustomerAccountRequest(customerAccountRequest);
        tibcoRequest.setCustomerAccountRequest1(customerAccountRequest1);

        return webClient.post()
                .uri(properties.getTibcoBillingUrl())
                .headers(headers -> headers.addAll(makeRequestHeaders()))
                .body(Mono.just(tibcoRequest), TibcoRequest.class)
                .retrieve()
                .bodyToMono(TibcoResponse.class);

    }

    private HttpHeaders makeRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-source-identity-token", "VElCQ09BQ0FDSUExMjMh");
        httpHeaders.set("x-route-id", "FixedDataTribe");
        httpHeaders.set("x-source-system", "diy-portal");
        httpHeaders.setBasicAuth("tibco6", "66UVPJRQVP86JMCG");

        return httpHeaders;
    }


    private String jsonToString(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;

    }
}
