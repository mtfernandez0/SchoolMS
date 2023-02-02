package com.mati.springschoolms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Classroom {

    public Classroom() {
    }

    public Classroom(String classroomName, Integer capacity, Boolean available, String professor, LocalTime availability) {
        this.classroomName = classroomName;
        this.capacity = capacity;
        this.available = available;
        this.professor = professor;
        this.availableAt = availability;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public LocalTime getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(LocalTime availability) {
        this.availableAt = availability;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomName='" + classroomName + '\'' +
                ", capacity=" + capacity +
                ", available=" + available +
                ", professor='" + professor + '\'' +
                ", availableAt=" + availableAt +
                '}';
    }

    @Id
    private String classroomName;
    private Integer capacity;
    @Column(nullable = false)
    private Boolean available;
    private String professor;
    private LocalTime availableAt;
}
