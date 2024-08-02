package com.ksmirnov.demoapp;

import com.ksmirnov.demoapp.dao.StudentDAO;
import com.ksmirnov.demoapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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

		System.out.println("Retrieving all the students");
		List<Student> students = studentDAO.findAll();

		for (Student student : students) {
			System.out.println(student);
		}

		System.out.println("Retrieving students with the last name = Bobick");
		students = studentDAO.findByLastName("Bobick");
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
