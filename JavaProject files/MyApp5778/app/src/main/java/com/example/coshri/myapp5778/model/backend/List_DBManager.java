package com.example.coshri.myapp5778.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.coshri.myapp5778.model.entities.Course;
import com.example.coshri.myapp5778.model.entities.Lecturer;
import com.example.coshri.myapp5778.model.entities.Student;
import com.example.coshri.myapp5778.model.entities.StudentCourse;



import java.util.ArrayList;
import java.util.List;
import static com.example.coshri.myapp5778.model.backend.AcademyConst.*;


public class  List_DBManager implements DB_manager {

    static List<Student> students;
    static List<Course> courses;
    static List<StudentCourse> studentCourses;

    static {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        studentCourses = new ArrayList<>();
    }


@Override
public long addStudent(ContentValues student) {
    Student item = ContentValuesToStudent(student);
    students.add(item);
    return item.getId();
}

    @Override
    public boolean removeStudent(long id) {
        return false;

    }

    @Override
    public boolean updateStudent(long id, ContentValues values) {
        return false;
    }

    @Override
    public List<Student> getStudents() {
        return null;
    }

    @Override
    public long addLecturer(ContentValues lecturer) {
        return 0;
    }

    @Override
    public boolean removeLecturer(long id) {
        return false;
    }

    @Override
    public boolean updateLecturer(long id, ContentValues values) {
        return false;
    }

    @Override
    public List<Lecturer> getLecturers() {
        return null;
    }

@Override
public long addCourse(ContentValues values) {
    Course course = ContentValuesToCourse(values);
    courses.add(course);
    return course.getId();
}

@Override
public boolean removeCourse(long id) {
    Course courseToRemove = null;
    for (Course item : courses)
        if (item.getId() == id) {
            courseToRemove = item;
            break;
        }
    return courses.remove(courseToRemove);
}

@Override
public boolean updateCourse(long id, ContentValues values) {
    Course course = ContentValuesToCourse(values);
    course.setId(id);
    for (int i = 0; i < courses.size(); i++)
        if (courses.get(i).getId() == id) {
            courses.set(i,course);
            return true;
        }
    return false;
}

    @Override
    public List<Course> getCourses() {
        return null;
    }

    @Override
    public long addStudentCourse(ContentValues studentCourse) {
        return 0;
    }
}
