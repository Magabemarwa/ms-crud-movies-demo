package com.safaricom.microservices.mscrudmoviesdemo.repository;

import com.safaricom.microservices.mscrudmoviesdemo.model.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MovieRepository extends ReactiveCrudRepository<Movie, Long> {

}
