package com.jdbc.service;

import com.jdbc.Student.Student;
import com.jdbc.util.DatabaseUtil;
import com.jdbc.util.QueryUtil;
import com.jdbc.Teacher.Teacher;
import com.jdbc.Subject.Subject;
import com.jdbc.Marksheet.Marksheet;
import java.sql.*;

public class DatabaseService {
    DatabaseUtil databaseUtil = new DatabaseUtil();
    public void insertStudent(Student student) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();)
        {
           PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery());
           String alreadyExistingStudent = student.getName();
           if(getStudentbyName(alreadyExistingStudent))
           {
               System.out.println("Student Already Exists");
           }
           else {
               preparedStatement.setString(1, alreadyExistingStudent);
               int rows = preparedStatement.executeUpdate();
               if (rows > 0) {
                   System.out.println("Inserted Student sucessfully");
               } else {
                   System.out.println("Inserted Student failed...");
               }
           }
        }
    } // End of insertStudent()

    public void getAllStudents() throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllStudents())) {
            System.out.printf("---------------------------%n");
            System.out.printf("   Student's Information       %n");
            System.out.printf("---------------------------%n");
            System.out.printf("| %-4s | %-16s |%n", "ROLL", " STUDENT NAME");
            System.out.printf("---------------------------%n");
            while(resultSet.next()) {
                printStudent(new Student(resultSet.getInt("roll_number"),resultSet.getString("name")));
            }
            System.out.printf("---------------------------%n");
        }
    } // End of getAllStudents()

    private void printStudent(Student student) {
        System.out.printf("| %-4d | %-16s |%n", student.getRoll_number(), student.getName());
    } // End of printStudent

    public boolean getStudentByRollNumber(int ro_no) throws SQLException{
        boolean isFound = false;
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByRollNumber(ro_no))) {
            if(resultSet.next()) {
                isFound = true;
                printSingleStudentRecord(new Student(resultSet.getInt("roll_number"),resultSet.getString("name")));
            } else {
                System.out.println("Record not found for Roll Number "+ro_no);
            }
        }
        return isFound;
    } // End of getStudentByRollNumber()

    private void printSingleStudentRecord(Student student)  {
        System.out.println("Name : "+student.getName());
        System.out.println("Roll Number : "+student.getRoll_number());
    } // End of printSingleStudentRecord()

    public void deleteStudentByRollNumber(int ro_no) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement();) {
            int rows = statement.executeUpdate(QueryUtil.deleteStudentByRollNumber(ro_no));
            if(rows>0){
                System.out.println("Student Record Deleted Successfully");
            } else {
                System.out.println("Something went wrong");
            }

        }

    } // End of deleteStudentByRollNumber()

    public boolean getStudentbyName(String st_name) throws SQLException {
        boolean isStudentFound = false;
        try(Connection connection = databaseUtil.getConnection(); CallableStatement callableStatement = connection.prepareCall(QueryUtil.selectStudentByName(st_name));)
        {
            callableStatement.setString(1,st_name);
            ResultSet resultSet = callableStatement.executeQuery();
            if(resultSet.next()) {
                isStudentFound=true;
                printSingleStudentRecord(new Student(resultSet.getInt("roll_number"),resultSet.getString("name")));
            } else {
                System.out.println("Record not found for Name "+st_name);
            }
        }
        return isStudentFound;
    } //End of getStudentbyName()

    public void updateStudent(Student student) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStudentQuery(student.getRoll_number()));)
        {
            preparedStatement.setString(1,student.getName());
            int rows = preparedStatement.executeUpdate();
            if(rows>0) {
                System.out.println("Student Record updated Successfully");
            } else {
                System.out.println("Failed to update Student Record");
            }
        }
    } //End of updateStudent()

    public void insertTeacher(Teacher teacher) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();)
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertTeacherQuery());
            String alreadyTeacherExists = teacher.getTeacher_name();
            if(getTeacherbyName(alreadyTeacherExists))
            {
                System.out.println("Teacher Already Exists");
            }
            else {
                preparedStatement.setString(1, alreadyTeacherExists);
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Inserted Teacher sucessfully");
                } else {
                    System.out.println("Inserted Teacher failed...");
                }
            }
        }
    } // End of insertTeacher()

    public void getAllTeachers() throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllTeachers())) {
            System.out.printf("---------------------------%n");
            System.out.printf("  Teacher's Information    %n");
            System.out.printf("---------------------------%n");
            System.out.printf("| %-4s | %-16s |%n", "ID", " TEACHER NAME");
            System.out.printf("---------------------------%n");
            while(resultSet.next()) {
                printTeacher(new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name")));
            }
            System.out.printf("---------------------------%n");
        }
    } // End of getAllTeachers()

    private void printTeacher(Teacher teacher) {
        System.out.printf("| %-4d | %-16s |%n", teacher.getTeacher_id(), teacher.getTeacher_name());
    } // End of printTeacher

    public boolean getTeacherByID(int teach_id) throws SQLException{
        boolean isFound = false;
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectTeacherByID(teach_id))) {
            if(resultSet.next()) {
                isFound = true;
                printSingleTeacherRecord(new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name")));
            } else {
                System.out.println("Record not found for ID "+teach_id);
            }
        }
        return isFound;
    } // End of getTeacherByID()

    private void printSingleTeacherRecord(Teacher teacher)  {
        System.out.println("Teacher's ID : "+teacher.getTeacher_id());
        System.out.println("Teacher's Name : "+teacher.getTeacher_name());
    } // End of printSingleTeacherRecord()

    public boolean getTeacherbyName(String t_name) throws SQLException {
        boolean isTeacherFound = false;
        try(Connection connection = databaseUtil.getConnection(); CallableStatement callableStatement = connection.prepareCall(QueryUtil.selectTeacherByName(t_name));)
        {
            callableStatement.setString(1,t_name);
            ResultSet resultSet = callableStatement.executeQuery();
            if(resultSet.next()) {
                isTeacherFound = true;
                printSingleTeacherRecord(new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name")));
            } else {
                System.out.println("Record not found for Name "+t_name);
            }
        }
        return isTeacherFound;
    } //End of getTeacherbyName()

    public void updateTeacher(Teacher teacher) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateTeacherQuery(teacher.getTeacher_id()));)
        {
            preparedStatement.setString(1,teacher.getTeacher_name());
            int rows = preparedStatement.executeUpdate();
            if(rows>0) {
                System.out.println("Teacher Record updated Successfully");
            } else {
                System.out.println("Failed to update Teacher Record");
            }
        }
    } //End of updateTeacher()

    public void deleteTeacherByID(int teach_id) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement();) {
            int rows = statement.executeUpdate(QueryUtil.deleteTeacherByID(teach_id));
            if(rows>0){
                System.out.println("Teacher Record Deleted Successfully");
            } else {
                System.out.println("Something went wrong");
            }

        }
    } // End of deleteTeacherByID()

    public void insertSubject(Subject subject) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();)
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertSubjectQuery());
            preparedStatement.setInt(1,subject.getStandard_id());
            int doesSubjectCodeExists = subject.getSubject_id();
            if(getSubjectByCode(doesSubjectCodeExists))
            {
                System.out.println("Subject Already Exists");
            }
            else {
                preparedStatement.setInt(2, doesSubjectCodeExists);
                preparedStatement.setString(3, subject.getSubject_name());
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Inserted Subject sucessfully");
                } else {
                    System.out.println("Inserted Subject failed...");
                }
            }
        }
    } // End of insertSubject()

    public void getAllSubjects() throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllSubjects())) {
            System.out.printf("---------------------------------%n");
            System.out.printf("     Subjects's Information       %n");
            System.out.printf("---------------------------------%n");
            System.out.printf("| %-3s | %-4s | %-15s |%n", "STD", " CODE", "SUBJECT NAME");
            System.out.printf("---------------------------------%n");
            while(resultSet.next()) {
                printSubject(new Subject(resultSet.getInt("standard_id"),resultSet.getInt("subject_id"),resultSet.getString("subject_name")));
            }
            System.out.printf("---------------------------------%n");
        }
    } // End of getAllSubjects()

    private void printSubject(Subject subject) {
        System.out.printf("| %-3d | %-5d | %-15s |%n", subject.getStandard_id(),subject.getSubject_id(),subject.getSubject_name());
    } // End of printSubject()

    public boolean getSubjectByCode(int sub_id) throws SQLException{
        boolean isFound = false;
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectSubjectByCode(sub_id))) {
            if(resultSet.next()) {
                isFound = true;
                printSingleSubjectRecord(new Subject(resultSet.getInt("standard_id"),resultSet.getInt("subject_id"),resultSet.getString("subject_name")));
            } else {
                System.out.println("Record not found for Code "+sub_id);
            }
        }
        return isFound;
    } // End of getSubjectByCode()

    private void printSingleSubjectRecord(Subject subject)  {
        System.out.println("Standard is: "+subject.getStandard_id());
        System.out.println("Subject id is: "+subject.getSubject_id());
        System.out.println("Subject name is: "+subject.getSubject_name());
    } // End of printSingleSubjectRecord()

    public void getSubjectbyName(String s_name) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); CallableStatement callableStatement = connection.prepareCall(QueryUtil.selectSubjectByName(s_name));)
        {
            callableStatement.setString(1,s_name);
            ResultSet resultSet = callableStatement.executeQuery();
            if(resultSet.next()) {
                printSingleSubjectRecord(new Subject(resultSet.getInt("standard_id"),resultSet.getInt("subject_id"),resultSet.getString("subject_name")));
            } else {
                System.out.println("Record not found for Name "+s_name);
            }
        }
    } //End of getSubjectbyName()

    public void updateSubject(Subject subject) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateSubjectQuery(subject.getSubject_id()));)
        {
            preparedStatement.setString(1, subject.getSubject_name());
            int rows = preparedStatement.executeUpdate();
            if(rows>0) {
                System.out.println("Subject Record updated Successfully");
            } else {
                System.out.println("Failed to update Subject Record");
            }
        }
    } //End of updateSubject()

    public void deleteSubjectByCode(int sub_id) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement();) {
            int rows = statement.executeUpdate(QueryUtil.deleteSubjectByCode(sub_id));
            if(rows>0){
                System.out.println("Subject Record Deleted Successfully");
            } else {
                System.out.println("Something went wrong");
            }

        }
    } // End of deleteSubjectByCode()

    public void insertMarksheet(Marksheet marksheet) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertMarksheetQuery());
            int isMarksIDExists = marksheet.getMarksheet_id();
            if (getMarksByID(isMarksIDExists)) {
                System.out.println("Marks ID Already Exists");
            } else {
                preparedStatement.setInt(1, isMarksIDExists);
                preparedStatement.setInt(2, marksheet.getRoll_number());
                preparedStatement.setInt(3, marksheet.getTeacher_id());
                preparedStatement.setInt(4, marksheet.getStandard_id());
                preparedStatement.setInt(5, marksheet.getSubject_id());
                int marks = marksheet.getMarks();
                if (marks >= 0 && marks <= 100) {
                    preparedStatement.setInt(6, marks);
                } else {
                    System.out.println("Please Enter the marks between the range 0 to 100");
                }
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Inserted Marks sucessfully");
                } else {
                    System.out.println("Inserted Marks failed...");
                }
            }
        }

    } // End of insertMarksheet()

    public boolean getMarksByID(int marks_id) throws SQLException{
        boolean isFound = false;
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectMarksByID(marks_id))) {
            if(resultSet.next()) {
                isFound = true;
                printSingleMarksRecord(new Marksheet(resultSet.getInt("marksheet_id"),resultSet.getInt("marks")));
            } else {
                System.out.println("Record not found for ID "+marks_id);
            }
        }
        return isFound;
    } // End of getMarksByID()

    private void printSingleMarksRecord(Marksheet marksheet)  {
        System.out.println("Marks ID is: "+marksheet.getMarksheet_id());
        System.out.println("Marks is: "+marksheet.getMarks());
    } // End of printSingleMarksRecord()

    public void updateMarks(Marksheet marksheet) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateMarksQuery(marksheet.getMarksheet_id()));)
        {
            preparedStatement.setInt(1, marksheet.getMarks());
            int rows = preparedStatement.executeUpdate();
            if(rows>0) {
                System.out.println("Marks Record updated Successfully");
            } else {
                System.out.println("Failed to update Marks Record");
            }
        }
    } //End of updateMarks()



    public void deleteMarksByID(int marks_id) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement();) {
            int rows = statement.executeUpdate(QueryUtil.deleteMarksByID(marks_id));
            if(rows>0){
                System.out.println("Marks Record Deleted Successfully");
            } else {
                System.out.println("Something went wrong");
            }

        }
    } // End of deleteMarksByID()

    public void getStandardbyRollNumber(int ro_no) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); CallableStatement callableStatement = connection.prepareCall(QueryUtil.getStandardByRollNumber(ro_no));)
        {
            callableStatement.setInt(1,ro_no);
            ResultSet resultSet = callableStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println("Standard : "+resultSet.getInt("standard_id"));
            }
        }
    } //End of getStandardbyRollNumber()

    public void getMarksbyRollNumber(int ro_no) throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); CallableStatement callableStatement = connection.prepareCall(QueryUtil.getMarksByRollNumber(ro_no));)
        {
            callableStatement.setInt(1,ro_no);
            ResultSet resultSet = callableStatement.executeQuery();
            System.out.printf("----------------------------------%n");
            System.out.printf("     Student's Marksheet          %n");
            System.out.printf("----------------------------------%n");
            System.out.printf("| %-4s | %-15s | %3s |%n", "CODE", " SUBJECT NAME", "Marks");
            System.out.printf("----------------------------------%n");
            while(resultSet.next())
            {
                System.out.printf("| %-4d | %-15s | %5d |%n", resultSet.getInt("subject_id"), resultSet.getString("subject_name"), resultSet.getInt("marks"));
            }
            System.out.printf("----------------------------------%n");
        }
    } //End of getMarksbyRollNumber()

    public int getCountofSubjectsByRollNumber(int ro_no) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();)
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.getCountofSubjectsByRollNumber(ro_no));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
    } // End of getCountofSubjectsByRollNumber()

    public int getTotalofSubjectsScorcesByRollNumber(int ro_no) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();)
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.getTotalofSubjectsScoresByRollNumber(ro_no));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
    } // End of getTotalofSubjectsScorcesByRollNumber()

    public void getAllMarksByID() throws SQLException {
        try(Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); Statement statement1 = connection.createStatement(); Statement statement2 = connection.createStatement(); Statement statement3 = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllMarksheets())) {
            System.out.printf("-------------------------------------------------------------------------------%n");
            System.out.printf("                                 Marks's Information                           %n");
            System.out.printf("-------------------------------------------------------------------------------%n");
            System.out.printf("| %-4s | %-16s | %-3S | %-16s | %-3S | %-16s |%n", "ID", " STUDENT NAME", "STD", "SUBJECT NAME", "MARKS", "TEACHER");
            System.out.printf("-------------------------------------------------------------------------------%n");
            while(resultSet.next()) {
                int m_id = resultSet.getInt("marksheet_id");
                System.out.printf("| %-4d ", m_id);
                ResultSet result1 = statement1.executeQuery(QueryUtil.getStudentNamethroughInnerJoinRollNumber(m_id));
                if(result1.next())
                {
                    System.out.printf("| %-16s ",result1.getString("name"));
                }
                System.out.printf("| %-3d ", resultSet.getInt("standard_id"));
                ResultSet result2 = statement2.executeQuery(QueryUtil.getSubjectNamethroughInnerJoinSubjectID(m_id));
                if(result2.next())
                {
                    System.out.printf("| %-16s ",result2.getString("subject_name"));
                }
                System.out.printf("| %-5d ", resultSet.getInt("marks"));
                ResultSet result3 = statement3.executeQuery(QueryUtil.getTeacherNamethroughInnerJoinTeacherID(m_id));
                if(result3.next())
                {
                    System.out.printf("| %-16s |%n",result3.getString("teacher_name"));
                }

            }
            System.out.printf("-------------------------------------------------------------------------------%n");
        }
    } // End of getAllMarksByID()













}
