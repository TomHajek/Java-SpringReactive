package com.spring.reactive.controller;

import com.spring.reactive.request.StudentRequest;
import com.spring.reactive.service.StudentService;
import com.spring.reactive.table.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> create(@RequestBody StudentRequest request) {
        return service.save(request);
    }

    @GetMapping("/filter")
    public Flux<Student> findByAuthor(@RequestParam String name) {
        return service.findByFirstname(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
