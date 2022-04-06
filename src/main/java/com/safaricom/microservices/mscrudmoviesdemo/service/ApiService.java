package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.safaricom.microservices.mscrudmoviesdemo.model.request.MovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.UpdateMovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public interface ApiService {
    Mono<ApiResponse> createMovie(MovieRequest movieRequest);
    Mono<ApiResponse> fetchMovieList();
    Mono<ApiResponse> updateMovie(UpdateMovieRequest request, HttpHeaders headers);
    Mono<ApiResponse> deleteMovie(long id);
}
