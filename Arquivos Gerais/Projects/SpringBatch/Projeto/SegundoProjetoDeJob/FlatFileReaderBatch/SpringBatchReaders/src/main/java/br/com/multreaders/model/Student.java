package br.com.multreaders.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private int age;
    private String school;
    private String grade;
    private String email;

    private List<Grade> grades = new ArrayList<>();

    public Student() {

    }
    public Student(String name, int age, String school, String grade, String email) {
        this.name = name;
        this.age = age;
        this.school = school;
        this.grade = grade;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                ", grade=" + grade +
                ", email='" + email + '\'' +
                (grades.isEmpty() ? " " : " ,grades = " + grades) + '\'' +
                '}';
    }
}
