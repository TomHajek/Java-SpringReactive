package com.spring.reactive;

import com.spring.reactive.table.Student;
import com.spring.reactive.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApplication.class, args);
	}

	/**
	 * Inserting data at application start up
	 */
	@Bean
	public CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			for (int i = 0; i < 300; i++) {
				repository.save(
						Student.builder()
								.firstname("Test" + i)
								.lastname("test" + i)
								.age(i)
								.build()
				).subscribe();
			}
		};
	}

}
