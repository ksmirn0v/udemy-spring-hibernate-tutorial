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
			createStudents(studentDAO);
			readStudent(studentDAO);
			readStudents(studentDAO);
			readStudentsByLastName(studentDAO);
			updateStudents(studentDAO);
			deleteStudent(studentDAO);
			deleteStudents(studentDAO);
		};
	}

	private void createStudents(StudentDAO studentDAO) {

		System.out.println("=== Creating students ===");

		Student student1 = new Student("Harry", "Potter", "harry.potter@mail.com");
		studentDAO.save(student1);

		Student student2 = new Student("Hermione", "Granger", "hermione.granger@mail.com");
		studentDAO.save(student2);

		Student student3 = new Student("Ron", "Weasley", "ron.weasley@mail.com");
		studentDAO.save(student3);
	}

	private void readStudent(StudentDAO studentDAO) {

		System.out.println("=== Reading a student ===");

		int studentId1 = 1;
		Student student1 = studentDAO.findById(studentId1);
		System.out.println("=====> Student with id=" + studentId1 + " is " + student1);

		int studentId2 = 3;
		Student student2 = studentDAO.findById(studentId2);
		System.out.println("=====> Student with id=" + studentId2 + " is " + student2);
	}

	private void readStudents(StudentDAO studentDAO) {

		System.out.println("=== Reading all the students ===");

		List<Student> students = studentDAO.findAll();
		for (Student student : students) {
			System.out.println("=====> " + student.getId() + ": " + student);
		}
	}

	private void readStudentsByLastName(StudentDAO studentDAO) {

		System.out.println("=== Reading all the students by last name ===");

		String lastName = "Potter";
		List<Student> students = studentDAO.findByLastName(lastName);
		for (Student student : students) {
			System.out.println("=====> " + student.getId() + ": " + student);
		}
	}

	private void updateStudents(StudentDAO studentDAO) {

		System.out.println("=== Updating students ===");

		int studentId = 2;
		Student student = studentDAO.findById(studentId);
		student.setEmail("hgranger@mail.com");
		studentDAO.update(student);

		student = studentDAO.findById(studentId);
		System.out.println("=====> " + student.getId() + ": " + student);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		System.out.println("=== Deleting a student ===");

		int studentId = 3;
		studentDAO.delete(studentId);
		readStudents(studentDAO);
	}

	private void deleteStudents(StudentDAO studentDAO) {

		System.out.println("=== Deleting all the students ===");

		studentDAO.deleteAll();
		readStudents(studentDAO);
	}
}
