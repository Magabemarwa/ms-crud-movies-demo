package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.ApiRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.CustomerAccountRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.CustomerAccountRequest1;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.TibcoRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response.BillingInfo;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.response.TibcoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class TibcoServiceImpl implements TibcoService {

    private final WebClient webClient;

    public TibcoServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ApiResponse> makeTibcoCall(ApiRequest apiRequest) {
        Mono<TibcoResponse> tibcoResponseMono = formatAndMakeRequestToTibco(apiRequest);

        return tibcoResponseMono.flatMap(tibcoResponse -> {
            if(tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
            .getRelatedParty().getResultCode().equals("0")){
                BillingInfo billingInfo = tibcoResponse.getCustomerAccountResponse1().getCustomerAccountResponse()
                        .getBillingInfo();
                return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 200,
                        "Success", "Billing info fetched successfully", billingInfo));
            }

            return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 400,
                    "Failed", "Billing info fetch failed", null));
        });
    }


    private Mono<TibcoResponse> formatAndMakeRequestToTibco(ApiRequest apiRequest){
        TibcoRequest tibcoRequest = new TibcoRequest();
        CustomerAccountRequest customerAccountRequest = new CustomerAccountRequest();
        CustomerAccountRequest1 customerAccountRequest1 = new CustomerAccountRequest1();
        customerAccountRequest.setPrimaryIdentity(apiRequest.getPrimaryIdentity());
        customerAccountRequest1.setCustomerAccountRequest(customerAccountRequest);
        tibcoRequest.setCustomerAccountRequest1(customerAccountRequest1);

        return webClient.post()
                .uri("provide url/ endpoint")
                .headers(headers -> headers.addAll(makeRequestHeaders()))
                .body(Mono.just(tibcoRequest), TibcoRequest.class)
                .retrieve()
                .bodyToMono(TibcoResponse.class);

    }

    private HttpHeaders makeRequestHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-source-identity-token", "VElCQ09BQ0FDSUExMjMh");
        httpHeaders.set("x-route-id", "FixedDataTribe");
        httpHeaders.set("x-source-system", "diy-portal");

        return httpHeaders;
    }
}
