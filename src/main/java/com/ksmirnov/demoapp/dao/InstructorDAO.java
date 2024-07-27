package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.entity.Course;
import com.ksmirnov.demoapp.entity.Instructor;
import com.ksmirnov.demoapp.entity.InstructorDetail;
import com.ksmirnov.demoapp.entity.Student;

import java.util.List;

public interface InstructorDAO {

    void saveInstructor(Instructor instructor);

    Instructor findInstructorById(int id);

    List<Instructor> findAllInstructors();

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    List<InstructorDetail> findAllInstructorDetails();

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void updateInstructor(Instructor instructor);

    Course findCourseById(int id);

    List<Course> findAllCourses();

    void updateCourse(Course course);

    void deleteCourseById(int id);

    void saveCourse(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndStudentsByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    void updateStudent(Student student);

    void deleteStudentById(int id);
}
