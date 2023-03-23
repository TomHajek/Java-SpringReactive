package com.spring.reactive.repository;

import com.spring.reactive.table.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    Flux<Student> findAllByFirstnameContainingIgnoreCase(String firstname);

}
