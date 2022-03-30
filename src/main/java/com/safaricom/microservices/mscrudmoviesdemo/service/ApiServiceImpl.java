package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.safaricom.microservices.mscrudmoviesdemo.model.Movie;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.MovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.UpdateMovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import com.safaricom.microservices.mscrudmoviesdemo.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ApiServiceImpl implements ApiService {

    private final MovieRepository movieRepository;

    public ApiServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Mono<ApiResponse> createMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setGenre(movieRequest.getGenre());
        movie.setName(movieRequest.getName());
        movie.setQuality(movieRequest.getQuality());
        movie.setStarring(movieRequest.getStarring());
        movie.setType(movieRequest.getType());
        movie.setYear(movieRequest.getYear());

        return movieRepository.save(movie)
                .flatMap(m ->
                        Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 200,
                                "Success", "Movie created successfully",
                                m))
                );
    }

    @Override
    public Mono<ApiResponse> fetchMovieList() {

        return movieRepository.findAll()
                .collectList()
                .flatMap(movieList -> {
                    return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 202,
                            "Success", "Movies fetched successfully",
                            movieList));
                });

//        return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 200,
//                "Success", "Movie created successfully",
//                movieFlux.collectList().flatMapMany(Flux::just)));
    }

    @Override
    public Mono<ApiResponse> updateMovie(UpdateMovieRequest request) {
        Mono<Movie> movieMono = movieRepository.findById(request.getId());

        return movieMono.flatMap(m -> {
            m.setName(request.getName());
            m.setStarring(request.getStarring());
            m.setQuality(request.getQuality());

            return movieRepository.save(m)
                    .flatMap(updatedMovie -> {
                        return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 201,
                                "Success", "Movies updated successfully",
                                updatedMovie));
                    });
        }).switchIfEmpty(
                Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 203,
                        "Success", "Movie with id " + request.getId() + " does not exist",
                        null))
        );
    }

    @Override
    public Mono<ApiResponse> deleteMovie(long id) {
        Mono<Movie> movieMono = movieRepository.findById(id);

        return movieMono.flatMap(m -> {
            return movieRepository.delete(m)
                    .then(Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 204,
                                    "Success", "Movie deleted successfully",
                                    null)));
        }).switchIfEmpty(
                Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 203,
                        "Success", "Movie with id " + id + " does not exist",
                        null))
        );
    }
}
