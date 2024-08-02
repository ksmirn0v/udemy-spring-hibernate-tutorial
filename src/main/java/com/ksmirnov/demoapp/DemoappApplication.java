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
			readStudents(studentDAO);
		};
	}

	private void readStudents(StudentDAO studentDAO) {

		System.out.println("Creating new student objects");
		Student student1 = new Student("John", "Doe", "jdoe@mail.com");
		Student student2 = new Student("Michael", "Bobick", "mbobick@mail.com");

		System.out.println("Saving the new student objects");
		studentDAO.save(student1);
		studentDAO.save(student2);
	}
}
