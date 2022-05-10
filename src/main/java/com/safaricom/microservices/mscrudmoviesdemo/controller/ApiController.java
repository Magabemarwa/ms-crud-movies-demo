package com.safaricom.microservices.mscrudmoviesdemo.controller;

import com.safaricom.microservices.mscrudmoviesdemo.model.request.MovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.UpdateMovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import com.safaricom.microservices.mscrudmoviesdemo.model.tibco.request.ApiRequest;
import com.safaricom.microservices.mscrudmoviesdemo.service.ApiServiceImpl;
import com.safaricom.microservices.mscrudmoviesdemo.service.TibcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private ApiServiceImpl apiService;

    @Autowired
    private TibcoService tibcoService;

    @PostMapping(value = "/createMovie")
    public Mono<ApiResponse> createMovie(@RequestBody MovieRequest movieRequest){
        return apiService.createMovie(movieRequest);
    }

    @GetMapping("/fetchMovies")
    public Mono<ApiResponse> fetchListOfMovies(){
        return apiService.fetchMovieList();
    }

    @PostMapping("/updateMovie")
    public Mono<ApiResponse> updateMovie(@RequestBody UpdateMovieRequest request,
                                         @RequestHeader HttpHeaders headers){
        return apiService.updateMovie(request, headers);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public Mono<ApiResponse> deleteMovie(@PathVariable("id") long id){
        return apiService.deleteMovie(id);
    }

    @PostMapping("/tibco/getBillingInfo")
    public Mono<ApiResponse> fetchBillingInfo(@Valid @RequestBody ApiRequest apiRequest){
        return tibcoService.makeTibcoCall(apiRequest);
    }
}
