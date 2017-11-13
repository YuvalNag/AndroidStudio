package com.example.coshri.myapp5778.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.coshri.myapp5778.model.entities.Course;
import com.example.coshri.myapp5778.model.entities.Lecturer;
import com.example.coshri.myapp5778.model.entities.Student;
import com.example.coshri.myapp5778.model.entities.StudentCourse;

import java.util.List;

/**
 * Created by coshri on 07/11/2017.
 */

public interface DB_manager {

    long addStudent(ContentValues student);
    boolean removeStudent(long id);
    boolean updateStudent(long id, ContentValues values);
    List<Student> getStudents();

    long addLecturer(ContentValues lecturer);
    boolean removeLecturer(long id);
    boolean updateLecturer(long id, ContentValues values);
    List<Lecturer> getLecturers();

    long addCourse(ContentValues course);
    boolean removeCourse(long id);
    boolean updateCourse(long id, ContentValues values);
    List<Course> getCourses();

    long addStudentCourse(ContentValues studentCourse);

}
