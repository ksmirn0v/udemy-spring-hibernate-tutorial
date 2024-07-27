package com.ksmirnov.demoapp;

import com.ksmirnov.demoapp.dao.InstructorDAO;
import com.ksmirnov.demoapp.entity.*;
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
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {

		return runner -> {
			createInstructors(instructorDAO);
			findInstructorById(instructorDAO);
			findInstructorDetail(instructorDAO);
			deleteInstructor(instructorDAO, 3);
			deleteInstructorDetail(instructorDAO);
			createInstructorWithCourses(instructorDAO);
			findCoursesByInstructorId(instructorDAO);
			findInstructorWithCoursesJoinFetch(instructorDAO);
			updateInstructors(instructorDAO);
			updateCourses(instructorDAO);
			deleteInstructor(instructorDAO, 4);
			deleteCourse(instructorDAO, 5);
			createCourseAndReviews(instructorDAO);
			findCourseAndReviews(instructorDAO);
			deleteCourse(instructorDAO, 6);
			createCourseAndStudents(instructorDAO);
			findCourseAndStudents(instructorDAO);
			findStudentAndCourses(instructorDAO);
			updateStudent(instructorDAO);
			deleteStudent(instructorDAO);
		};
	}

	private void createInstructors(InstructorDAO instructorDAO) {

		System.out.println("=== Creating Instructors with Instructor Details ===");

		Instructor instructor1 = new Instructor("first-name-1", "last-name-1", "f1l1@mail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("f1l1@youtube.com", "dancing");
		instructor1.setInstructorDetail(instructorDetail1);

		Instructor instructor2 = new Instructor("first-name-2", "last-name-2", "f2l2@mail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("f2l2@youtube.com", "fishing");
		instructor2.setInstructorDetail(instructorDetail2);

		Instructor instructor3 = new Instructor("first-name-3", "last-name-3", "f3l3@mail.com");
		InstructorDetail instructorDetail3 = new InstructorDetail("f3l3@youtube.com", "eating");
		instructor3.setInstructorDetail(instructorDetail3);

		instructorDAO.saveInstructor(instructor1);
		instructorDAO.saveInstructor(instructor2);
		instructorDAO.saveInstructor(instructor3);
	}

	private void findInstructorById(InstructorDAO instructorDAO) {

		System.out.println("=== Finding Instructors by Id ===");

		for (int id = 1; id <= 3; id++) {
			Instructor instructor = instructorDAO.findInstructorById(id);
			System.out.println("Instructor with id=" + id + ": " + instructor);
		}
	}

	private void findInstructorDetail(InstructorDAO instructorDAO) {

		System.out.println("=== Finding Instructor Details by Id ===");

		for (int id = 1; id <= 3; id++) {
			InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(id);
			System.out.println("Instructor detail with id=" + id + ": " + instructorDetail);
		}
	}

	private void deleteInstructor(InstructorDAO instructorDAO, int id) {

		System.out.println("=== Deleting an Instructor ===");

		instructorDAO.deleteInstructorById(id);
		List<Instructor> instructors = instructorDAO.findAllInstructors();
		for (Instructor instructor : instructors) {
			System.out.println("Instructor with id=" + instructor.getId() + ": " + instructor);
		}
	}

	private void deleteInstructorDetail(InstructorDAO instructorDAO) {

		System.out.println("=== Deleting an Instructor Detail ===");

		int id = 2;
		instructorDAO.deleteInstructorDetailById(id);

		List<InstructorDetail> instructorDetails = instructorDAO.findAllInstructorDetails();
		for (InstructorDetail instructorDetail : instructorDetails) {
			System.out.println("Instructor detail with id=" + instructorDetail.getId() + ": " + instructorDetail);
		}

		List<Instructor> instructors = instructorDAO.findAllInstructors();
		for (Instructor instructor : instructors) {
			System.out.println("Instructor with id=" + instructor.getId() + ": " + instructor);
		}
	}

	private void createInstructorWithCourses(InstructorDAO instructorDAO) {

		System.out.println("=== Creating Instructors with Instructor Details and Courses ===");

		Instructor instructor4 = new Instructor("first-name-4", "last-name-4", "f4l4@mail.com");
		InstructorDetail instructorDetail4 = new InstructorDetail("f4l4@youtube.com", "skiing");
		instructor4.setInstructorDetail(instructorDetail4);
		Course course4_1 = new Course("mathematics");
		Course course4_2 = new Course("physics");
		instructor4.add(course4_1);
		instructor4.add(course4_2);

		Instructor instructor5 = new Instructor("first-name-5", "last-name-5", "f5l5@mail.com");
		InstructorDetail instructorDetail5 = new InstructorDetail("f5l5@youtube.com", "cooking");
		instructor5.setInstructorDetail(instructorDetail5);
		Course course5_1 = new Course("english");
		Course course5_2 = new Course("german");
		Course course5_3 = new Course("french");
		instructor5.add(course5_1);
		instructor5.add(course5_2);
		instructor5.add(course5_3);

		instructorDAO.saveInstructor(instructor4);
		instructorDAO.saveInstructor(instructor5);
	}

	private void findCoursesByInstructorId(InstructorDAO instructorDAO) {

		System.out.println("=== Finding Courses by Instructor Id ===");

		for (int id = 1; id <= 5; id++) {
			List<Course> courses = instructorDAO.findCoursesByInstructorId(id);
			System.out.println("Instructor with id=" + id + " has the following courses:");
			for (Course course : courses) {
				System.out.println("Course: " + course);
			}
		}
	}

	private void findInstructorWithCoursesJoinFetch(InstructorDAO instructorDAO) {

		System.out.println("=== Finding Instructor with Courses by Instructor Id ===");

		int id = 4;
		Instructor instructor = instructorDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());
	}

	private void updateInstructors(InstructorDAO instructorDAO) {

		System.out.println("=== Updating Instructors ===");

		List<Instructor> instructors = instructorDAO.findAllInstructors();
		for (Instructor instructor : instructors) {
			instructor.setEmail("prefix-" + instructor.getEmail());
			instructorDAO.updateInstructor(instructor);
		}

		instructors = instructorDAO.findAllInstructors();
		for (Instructor instructor : instructors) {
			System.out.println("Instructor with id=" + instructor.getId() + ": " + instructor);
		}
	}

	private void updateCourses(InstructorDAO instructorDAO) {

		System.out.println("=== Updating Courses ===");

		List<Course> courses = instructorDAO.findAllCourses();
		for (Course course : courses) {
			course.setTitle("advanced " + course.getTitle());
			instructorDAO.updateCourse(course);
		}

		courses = instructorDAO.findAllCourses();
		for (Course course : courses) {
			System.out.println("Course with id=" + course.getId() + ": " + course);
		}
	}

	private void deleteCourse(InstructorDAO instructorDAO, int id) {

		System.out.println("=== Deleting a Course ===");

		instructorDAO.deleteCourseById(id);
		List<Course> courses = instructorDAO.findAllCourses();
		for (Course course : courses) {
			System.out.println("Course with id=" + course.getId() + ": " + course);
		}
	}

	private void createCourseAndReviews(InstructorDAO instructorDAO) {

		System.out.println("=== Creating a Course and Associated Reviews ===");

		Course course = new Course("game theory");
		course.addReview(new Review("amazing!"));
		course.addReview(new Review("great course!"));
		course.addReview(new Review("too bad!"));

		instructorDAO.saveCourse(course);
	}

	private void findCourseAndReviews(InstructorDAO instructorDAO) {

		System.out.println("=== Finding a Course and Associated Reviews ===");

		int id = 6;
		Course course = instructorDAO.findCourseAndReviewsByCourseId(id);
		System.out.println("Course: " + course);
		System.out.println("Reviews: " + course.getReviews());
	}

	private void createCourseAndStudents(InstructorDAO instructorDAO) {

		System.out.println("=== Creating a Course and Associated Students ===");

		Course course = new Course("russian");
		Student student1 = new Student("first-name-1", "last-name-1", "fl1@mail.com");
		Student student2 = new Student("first-name-1", "last-name-1", "fl2@mail.com");
		course.addStudent(student1);
		course.addStudent(student2);
		instructorDAO.saveCourse(course);
	}

	private void findCourseAndStudents(InstructorDAO instructorDAO) {

		System.out.println("=== Finding a Course and Associated Students ===");

		int id = 7;
		Course course = instructorDAO.findCourseAndStudentsByCourseId(id);
		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	private void findStudentAndCourses(InstructorDAO instructorDAO) {

		System.out.println("=== Finding a Student and Associated Courses ===");

		int id = 1;
		Student student = instructorDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	private void updateStudent(InstructorDAO instructorDAO) {

		System.out.println("=== Updating a Student ===");

		int id = 1;
		Student student = instructorDAO.findStudentAndCoursesByStudentId(id);
		Course course = new Course("chinese");
		student.addCourse(course);
		instructorDAO.updateStudent(student);

		student = instructorDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	private void deleteStudent(InstructorDAO instructorDAO) {

		System.out.println("=== Deleting a Student ===");

		int id = 1;
		instructorDAO.deleteStudentById(id);

		List<Course> courses = instructorDAO.findAllCourses();
		for (Course course : courses) {
			System.out.println("Course: " + course);
		}
	}
}
