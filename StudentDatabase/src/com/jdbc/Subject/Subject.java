package com.jdbc.Subject;

public class Subject {
    private int standard_id;
    private int subject_id;
    private String subject_name;

    public Subject(int subject_id, String subject_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public Subject(int standard_id, int subject_id, String subject_name) {
        this.standard_id = standard_id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public int getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(int standard_id) {
        this.standard_id = standard_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
