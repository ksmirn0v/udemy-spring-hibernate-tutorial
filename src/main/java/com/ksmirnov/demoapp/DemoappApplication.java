package com.ksmirnov.demoapp;

import com.ksmirnov.demoapp.dao.StudentDAO;
import com.ksmirnov.demoapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			deleteStudents(studentDAO);
		};
	}

	private void deleteStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");
		studentDAO.deleteAll();
	}
}
