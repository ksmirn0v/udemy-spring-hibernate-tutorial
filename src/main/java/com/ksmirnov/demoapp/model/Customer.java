package com.ksmirnov.demoapp.model;

import com.ksmirnov.demoapp.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message="is required")
    @Size(min=1, message="the last name should contain at least one letter")
    private String lastName = "";

    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to 0")
    @Max(value=10, message="must be less than or equal to 10")
    private Integer freePasses;

    @NotNull(message="is required")
    @Pattern(regexp="^[a-zA-Z0-9]{5}", message="must contain 5 characters/digits are allowed")
    private String postalCode;

    @CourseCode
    private String courseCode;

    public Customer() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
