package com.spring.reactive.service;

import com.spring.reactive.repository.StudentRepository;
import com.spring.reactive.request.StudentRequest;
import com.spring.reactive.table.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Flux == list of objects
     */
    public Flux<Student> findAll() {
        return studentRepository.findAll()
                .delayElements(Duration.ofSeconds(1)); // data will consistently pop up after 1 sec
    }

    /**
     * Mono == 1 object
     */
    public Mono<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Mono<Student> save(StudentRequest request) {
        return studentRepository.save(
                Student.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .age(request.getAge())
                        .build()
        );
    }

    public Flux<Student> findByFirstname(String firstname) {
        return studentRepository.findAllByFirstnameContainingIgnoreCase(firstname);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id).subscribe();
    }
}
