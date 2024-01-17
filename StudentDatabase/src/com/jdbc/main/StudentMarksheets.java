package com.jdbc.main;

import com.jdbc.Student.Student;
import com.jdbc.service.DatabaseService;
import com.jdbc.Teacher.Teacher;
import com.jdbc.Subject.Subject;
import com.jdbc.Marksheet.Marksheet;
import java.sql.*;
import java.util.Scanner;

public class StudentMarksheets {
    public static void main(String[] args) {
        StudentMarksheets studentsMarksheets = new StudentMarksheets();
        try(Scanner scanner = new Scanner(System.in);)
        {
            DatabaseService databaseService = new DatabaseService();
            boolean isRunning = true;
            while(isRunning) {
                System.out.println();
                System.out.printf("-------------------------------------------------------------%n");
                System.out.printf("                              CHOICE                         %n");
                System.out.printf("-------------------------------------------------------------%n");
                System.out.printf("| %-6s | %-48s |%n", "NUMBER", "OPERATIONS");
                System.out.printf("-------------------------------------------------------------%n");
                System.out.printf("| %-6s | %-48s |%n", "1", "Displaying Students");
                System.out.printf("| %-6s | %-48s |%n", "2", "Adding Student");
                System.out.printf("| %-6s | %-48s |%n", "3", "Searching Student by Roll No");
                System.out.printf("| %-6s | %-48s |%n", "4", "Searching Student by Name");
                System.out.printf("| %-6s | %-48s |%n", "5", "Updating Student Name");
                System.out.printf("| %-6s | %-48s |%n", "6", "Deleting Student Record");
                System.out.printf("| %-6s | %-48s |%n", "7", "Displaying Teachers");
                System.out.printf("| %-6s | %-48s |%n", "8", "Adding Teacher");
                System.out.printf("| %-6s | %-48s |%n", "9", "Searching Teacher by ID");
                System.out.printf("| %-6s | %-48s |%n", "10", "Searching Teacher by Name");
                System.out.printf("| %-6s | %-48s |%n", "11", "Updating Teacher Name");
                System.out.printf("| %-6s | %-48s |%n", "12", "Deleting Teacher Record");
                System.out.printf("| %-6s | %-48s |%n", "13", "Displaying Subjects Standard wise");
                System.out.printf("| %-6s | %-48s |%n", "14", "Adding Subjects Standard wise");
                System.out.printf("| %-6s | %-48s |%n", "15", "Searching Subjects by Subject Code");
                System.out.printf("| %-6s | %-48s |%n", "16", "Searching Subjects by Subject Name");
                System.out.printf("| %-6s | %-48s |%n", "17", "Updating Subject Name");
                System.out.printf("| %-6s | %-48s |%n", "18", "Deleting Subject Records");
                System.out.printf("| %-6s | %-48s |%n", "19", "Displaying Marks Information");
                System.out.printf("| %-6s | %-48s |%n", "20", "Adding Marks");
                System.out.printf("| %-6s | %-48s |%n", "21", "Updating Marks by Marks ID");
                System.out.printf("| %-6s | %-48s |%n", "22", "Deleting Marks Record");
                System.out.printf("| %-6s | %-48s |%n", "23", "Viewing Marksheet of Student by Roll Number");
                System.out.printf("| %-6s | %-48s |%n", "24", "Exit");
                System.out.printf("-------------------------------------------------------------%n");
                System.out.println();
                System.out.println("Enter Choice");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        databaseService.getAllStudents();
                        break;
                    case 2:
                        System.out.println("Enter Student's Name:");
                        databaseService.insertStudent(new Student(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.println("Enter Roll Number to find the student");
                        databaseService.getStudentByRollNumber(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.println("Enter Name to find the student");
                        databaseService.getStudentbyName(scanner.nextLine());
                        break;
                    case 5:
                        System.out.println("Enter Roll Number for which you want to update Student Record");
                        int updateStudentRollNumber = Integer.parseInt(scanner.nextLine());
                        boolean isFound = databaseService.getStudentByRollNumber(updateStudentRollNumber);
                        if(isFound)
                        {
                            System.out.println("Enter new Name");
                            Student student = new Student(updateStudentRollNumber, scanner.nextLine());
                            databaseService.updateStudent(student);
                        }
                        break;
                    case 6:
                        System.out.println("Enter Roll Number to delete the student record");
                        databaseService.deleteStudentByRollNumber(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 7:
                        databaseService.getAllTeachers();
                        break;
                    case 8:
                        System.out.println("Enter Teacher's Name:");
                        databaseService.insertTeacher(new Teacher(scanner.nextLine()));
                        break;
                    case 9:
                        System.out.println("Enter ID to find the teacher");
                        databaseService.getTeacherByID(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 10:
                        System.out.println("Enter Name to find the teacher");
                        databaseService.getTeacherbyName(scanner.nextLine());
                        break;
                    case 11:
                        System.out.println("Enter ID for which you want to update Teacher Record");
                        int updateTeacherID = Integer.parseInt(scanner.nextLine());
                        boolean isTeacherFound = databaseService.getTeacherByID(updateTeacherID);
                        if(isTeacherFound)
                        {
                            System.out.println("Enter new Name");
                            Teacher teacher = new Teacher(updateTeacherID,scanner.nextLine());
                            databaseService.updateTeacher(teacher);
                        }
                        break;
                    case 12:
                        System.out.println("Enter ID to delete the teacher record");
                        databaseService.deleteTeacherByID(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 13:
                        databaseService.getAllSubjects();
                        break;
                    case 14:
                        System.out.println("Enter Standard for which you want to enter the Subjects");
                        System.out.println("Enter the Subject id");
                        System.out.println("Enter the Subject Name");
                        databaseService.insertSubject(new Subject(Integer.parseInt(scanner.nextLine()),Integer.parseInt(scanner.nextLine()),scanner.nextLine()));
                        break;
                    case 15:
                        System.out.println("Enter Subject Code to find the subject");
                        databaseService.getSubjectByCode(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 16:
                        System.out.println("Enter the Subject Name to view its details");
                        databaseService.getSubjectbyName(scanner.nextLine());
                        break;
                    case 17:
                        System.out.println("Enter Subject Code for which you want to update Subject Record");
                        int updateSubjectCode = Integer.parseInt(scanner.nextLine());
                        boolean isSubjectFound = databaseService.getSubjectByCode(updateSubjectCode);
                        if(isSubjectFound)
                        {
                            System.out.println("Enter new Subject Name");
                            Subject subject = new Subject(updateSubjectCode, scanner.nextLine());
                            databaseService.updateSubject(subject);
                        }
                        break;
                    case 18:
                        System.out.println("Enter Subject Code to delete the subject record");
                        databaseService.deleteSubjectByCode(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 19:
                        databaseService.getAllMarksByID();
                        break;
                    case 20:
                        System.out.println("Enter Marks id for which you want to enter the Marks");
                        System.out.println("Enter the Roll number");
                        System.out.println("Enter the Teacher id");
                        System.out.println("Enter the Standard id");
                        System.out.println("Enter the Subject Code");
                        System.out.println("Enter the Marks of Student in that Subject");
                        databaseService.insertMarksheet(new Marksheet(Integer.parseInt(scanner.nextLine()),Integer.parseInt(scanner.nextLine()),Integer.parseInt(scanner.nextLine()),Integer.parseInt(scanner.nextLine()),Integer.parseInt(scanner.nextLine()),Integer.parseInt(scanner.nextLine())));
                        break;
                    case 21:
                        System.out.println("Enter Marks ID for which you want to update Marks Record");
                        int updateMarksID = Integer.parseInt(scanner.nextLine());
                        boolean isMarksFound = databaseService.getMarksByID(updateMarksID);
                        if(isMarksFound)
                        {
                            System.out.println("Enter new Marks");
                            Marksheet marksheet = new Marksheet(updateMarksID, Integer.parseInt(scanner.nextLine()));
                            databaseService.updateMarks(marksheet);
                        }
                        break;
                    case 22:
                        System.out.println("Enter Marks ID to delete the marks record");
                        databaseService.deleteMarksByID(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 23:
                        System.out.println("Enter Roll Number of Student of which you want to view Marksheet");
                        int roll = Integer.parseInt(scanner.nextLine());
                        databaseService.getStudentByRollNumber(roll);
                        databaseService.getStandardbyRollNumber(roll);
                        databaseService.getMarksbyRollNumber(roll);
                        int Count = databaseService.getCountofSubjectsByRollNumber(roll);
                        int Total = databaseService.getTotalofSubjectsScorcesByRollNumber(roll);
                        System.out.println("Total : "+Total);

                        double Percentage = Total/Count;
                        System.out.println("Percentage : "+Percentage+"%");

                        char Grade = 'a';
                        if(Percentage>=90.0d && Percentage<=100.0d)
                        {
                            Grade = 'O';
                        }
                        else if(Percentage>=80.0d && Percentage<90.0d)
                        {
                            Grade = 'A';
                        }
                        else if(Percentage>=70.0d && Percentage<80.0d)
                        {
                            Grade = 'B';
                        }
                        else if(Percentage>=60.0d && Percentage<70.0d)
                        {
                            Grade = 'C';
                        }
                        else if(Percentage>=50.0d && Percentage<60.0d)
                        {
                            Grade = 'D';
                        }
                        else if(Percentage>=40.0d && Percentage<50.0d)
                        {
                            Grade = 'E';
                        }
                        else if(Percentage>=0.0d && Percentage<40.0d)
                        {
                            Grade = 'F';
                        }

                        System.out.println("Grade : "+Grade);

                        String Result = "";
                        if(Percentage>=40.0d)
                        {
                            Result = Result + "PASS";
                            System.out.println("Result : "+Result);
                        }
                        else {
                            Result = Result + "FAIL";
                            System.out.println("Result : "+Result);
                        }
                        break;
                    case 24:
                        System.out.println("Thank you. Visit Again");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Please Enter a Number from 1 to 24");
                }
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("Something went wrong"+e);
        }
    }


}