package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.ApiRequest;
import reactor.core.publisher.Mono;

public interface TibcoService {
    Mono<ApiResponse> makeTibcoCall(ApiRequest apiRequest);
}
