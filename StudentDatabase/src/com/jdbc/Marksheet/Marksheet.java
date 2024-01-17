package com.jdbc.Marksheet;

public class Marksheet {
    private int marksheet_id;
    private int standard_id;
    private int roll_number;
    private int subject_id;
    private int teacher_id;
    private int marks;

    public Marksheet(int marksheet_id, int roll_number, int teacher_id, int standard_id, int subject_id, int marks) {
        this.marksheet_id = marksheet_id;
        this.standard_id = standard_id;
        this.roll_number = roll_number;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.marks = marks;
    }

    public Marksheet(int marksheet_id, int marks) {
        this.marksheet_id = marksheet_id;
        this.marks = marks;
    }

    public void updateStudentName(int marksheet_id, int roll_number)
    {
        this.marksheet_id=marksheet_id;
        this.roll_number=roll_number;
    }
    public int getMarksheet_id() {
        return marksheet_id;
    }

    public void setMarksheet_id(int marksheet_id) {
        this.marksheet_id = marksheet_id;
    }

    public int getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(int standard_id) {
        this.standard_id = standard_id;
    }

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
