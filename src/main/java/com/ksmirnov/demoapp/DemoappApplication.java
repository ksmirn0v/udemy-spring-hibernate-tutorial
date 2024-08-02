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
			updateStudent(studentDAO);
		};
	}

	private void updateStudent(StudentDAO studentDAO) {

		System.out.println("Retrieve a student based on the id");
		int id = 2;
		Student student = studentDAO.findById(id);

		System.out.println("Change e-mail address");
		student.setEmail("mbob@mail.com");

		System.out.println("Updating the student");
		studentDAO.update(student);
	}
}
