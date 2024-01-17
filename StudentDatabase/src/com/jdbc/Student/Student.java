package com.jdbc.Student;

public class Student {
    private int roll_number;
    private String name;

    public Student(int roll_number, String name) {
        this.roll_number = roll_number;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
