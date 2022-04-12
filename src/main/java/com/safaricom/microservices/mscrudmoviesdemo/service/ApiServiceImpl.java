package com.safaricom.microservices.mscrudmoviesdemo.service;

import com.safaricom.microservices.mscrudmoviesdemo.model.Movie;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.MovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.request.UpdateMovieRequest;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.ApiResponse;
import com.safaricom.microservices.mscrudmoviesdemo.model.response.HeaderErrors;
import com.safaricom.microservices.mscrudmoviesdemo.repository.MovieRepository;
import static com.safaricom.microservices.mscrudmoviesdemo.util.GlobalVariables.*;
import com.safaricom.microservices.mscrudmoviesdemo.util.LogsManager;
import com.safaricom.microservices.mscrudmoviesdemo.util.Utilities;
import org.springframework.http.HttpHeaders;
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
    public Mono<ApiResponse> updateMovie(UpdateMovieRequest request, HttpHeaders headers) {
        HeaderErrors errors = Utilities.checkIfMissingCorrelationHeader(headers);
        System.out.println(errors.getMissingHeader());
        System.out.println(errors.getHeaderMismatch());
        if(errors.getMissingHeader().equals(Boolean.TRUE)){

            return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 400,
                    "Failed", "Your request failed. Please try again",
                    errors));
        }
        if(errors.getHeaderMismatch().equals(Boolean.TRUE)){
            return Mono.just(ApiResponse.responseFormatter(UUID.randomUUID().toString(), 400,
                    "Failed", "Your request failed. Please try again",
                    errors));
        }
        long startTime = System.currentTimeMillis();
        String sourceSystem;
        String xCorrelationConversationId;
        if(headers.containsKey(X_SOURCE_SYSTEM) && headers.containsKey(X_CORRELATION_CONVERSATION_ID)){
            sourceSystem = headers.get(X_SOURCE_SYSTEM).get(0);
            xCorrelationConversationId = headers.get(X_CORRELATION_CONVERSATION_ID).get(0);
        } else {
            xCorrelationConversationId = "null";
            sourceSystem = "Default_header";
        }
        Mono<Movie> movieMono = movieRepository.findById(request.getId());

        return movieMono.flatMap(m -> {
            m.setName(request.getName());
            m.setStarring(request.getStarring());
            m.setQuality(request.getQuality());

            return movieRepository.save(m)
                    .flatMap(updatedMovie -> {
                        LogsManager.info(xCorrelationConversationId, "Updating movie record","updateMovie",
                                String.valueOf(System.currentTimeMillis() - startTime), "", sourceSystem,
                                "Mysql movie db", CUSTOMER_MESSAGE_SUCCESS_UPDATE_MOVIE, 201,
                                "Success", "", "", "", "");
                        return Mono.just(ApiResponse.responseFormatter(xCorrelationConversationId, 201,
                                "Success", CUSTOMER_MESSAGE_SUCCESS_UPDATE_MOVIE,
                                updatedMovie));
                    });
        }).switchIfEmpty(Mono.defer(() -> {
            LogsManager.info(xCorrelationConversationId, "Updating movie record","updateMovie",
                    String.valueOf(System.currentTimeMillis() - startTime), "", sourceSystem,
                    "Mysql movie db", CUSTOMER_MESSAGE_SUCCESS_UPDATE_MOVIE, 203,
                    "Success", "", "", "", "");
            return Mono.just(ApiResponse.responseFormatter(xCorrelationConversationId, 203,
                    "Success", "Movie with id " + request.getId() + " does not exist",
                    null));
                })
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
