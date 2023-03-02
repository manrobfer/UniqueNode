package br.com.multreaders.model;

import java.util.Date;

public class Grade {
  private String subject;
  private double grade;
  private Date date;
  private String teacher;

    public Grade() {

    }
    public Grade(String subject, double grade, Date date, String teacher) {
        this.subject = subject;
        this.grade = grade;
        this.date = date;
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "subject='" + subject + '\'' +
                ", grade='" + grade + '\'' +
                ", date=" + date +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
