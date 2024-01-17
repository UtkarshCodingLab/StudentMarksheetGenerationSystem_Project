package com.jdbc.util;

public class QueryUtil {
    public static String insertStudentQuery() {
        return "INSERT INTO Student(name) VALUES(?)";
    }

    public static String selectAllStudents() {
        return "SELECT * FROM Student";
    }

    public static String selectStudentByRollNumber(int roll_num) {
        return "SELECT * FROM Student WHERE roll_number = "+roll_num;
    }

    public static String deleteStudentByRollNumber(int roll_num) {
        return "DELETE FROM Student WHERE roll_number = "+roll_num;
    }

    public static String selectStudentByName(String stud_name)
    {
        return "{ call GET_STUDENTS(?)}";
    }

    public static String updateStudentQuery(int roll_num) {
        return "UPDATE Student SET name = ? WHERE roll_number = "+roll_num;
    }

    public static String insertTeacherQuery() {
        return "INSERT INTO teacher(teacher_name) VALUES(?)";
    }

    public static String selectAllTeachers() {
        return "SELECT * FROM teacher";
    }

    public static String selectTeacherByID(int t_id) {
        return "SELECT * FROM teacher WHERE teacher_id = "+t_id;
    }

    public static String selectTeacherByName(String teach_name)
    {
        return "{ call GET_TEACHERS(?)}";
    }

    public static String updateTeacherQuery(int t_id) {
        return "UPDATE teacher SET teacher_name = ? WHERE teacher_id = "+t_id;
    }

    public static String deleteTeacherByID(int t_id) {
        return "DELETE FROM teacher WHERE teacher_id = "+t_id;
    }

    public static String insertSubjectQuery() {
        return "INSERT INTO subject(standard_id,subject_id,subject_name) VALUES(?,?,?)";
    }

    public static String selectAllSubjects() {
        return "SELECT * FROM subject";
    }

    public static String selectSubjectByCode(int s_id) {
        return "SELECT * FROM subject WHERE subject_id = "+s_id;
    }

    public static String selectSubjectByName(String sub_name)
    {
        return "{ call GET_SUBJECTNAME(?)}";
    }

    public static String updateSubjectQuery(int s_id) {
        return "UPDATE subject SET subject_name = ? WHERE subject_id = "+s_id;
    }

    public static String deleteSubjectByCode(int s_id) {
        return "DELETE FROM subject WHERE subject_id = "+s_id;
    }

    public static String insertMarksheetQuery() {
        return "INSERT INTO marksheet(marksheet_id,roll_number,teacher_id,standard_id,subject_id,marks) VALUES(?,?,?,?,?,?)";
    }

    public static String selectAllMarksheets() {
        return "SELECT * FROM marksheet";
    }

    public static String getStudentNamethroughInnerJoinRollNumber(int m_id)
    {
        return "select Student.name from marksheet inner join Student on marksheet.roll_number = Student.roll_number where marksheet_id = " + m_id;
    }

    public static String getSubjectNamethroughInnerJoinSubjectID(int m_id)
    {
        return "select subject.subject_name from marksheet inner join subject on marksheet.subject_id = subject.subject_id where marksheet_id = "+m_id;
    }

    public static String getTeacherNamethroughInnerJoinTeacherID(int m_id)
    {
        return "select teacher.teacher_name from marksheet inner join teacher on marksheet.teacher_id = teacher.teacher_id where marksheet_id = "+m_id;
    }

    public static String deleteMarksByID(int m_id) {
        return "DELETE FROM marksheet WHERE marksheet_id = "+m_id;
    }

    public static String updateMarksQuery(int m_id) {
        return "UPDATE marksheet SET marks = ? WHERE marksheet_id = "+m_id;
    }

    public static String selectMarksByID(int m_id) {
        return "SELECT * FROM marksheet WHERE marksheet_id = "+m_id;
    }

    public static String getStandardByRollNumber(int roll_num) {
        return "{ call GET_STANDARD(?)}";
    }

    public static String getMarksByRollNumber(int roll_num) {
        return "{ call GET_MARKS(?)}";
    }

    public static String getCountofSubjectsByRollNumber(int roll_num) {
        return "select count(marks) from marksheet where roll_number = " + roll_num;
    }

    public static String getTotalofSubjectsScoresByRollNumber(int roll_num) {
        return "select sum(marks) from marksheet where roll_number = " + roll_num;
    }



}
