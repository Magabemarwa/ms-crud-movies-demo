package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.safaricom.microservices.mscrudmoviesdemo.model.request.MovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.UpdateMovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import reactor.core.publisher.Mono;

public interface ApiService {
    Mono<ApiResponse> createMovie(MovieRequest movieRequest);
    Mono<ApiResponse> fetchMovieList();
    Mono<ApiResponse> updateMovie(UpdateMovieRequest request);
    Mono<ApiResponse> deleteMovie(long id);
}
