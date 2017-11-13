package com.example.coshri.myapp5778.model.backend;


import android.content.ContentValues;

import com.example.coshri.myapp5778.model.entities.Course;
import com.example.coshri.myapp5778.model.entities.Lecturer;
import com.example.coshri.myapp5778.model.entities.Student;
import com.example.coshri.myapp5778.model.entities.StudentCourse;
import com.example.coshri.myapp5778.model.entities.Year;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mailo on 08/12/2016.
 */

public class AcademyConst {

    public static class StudentConst {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String YEAR = "year";
    }

    public static class LecturerConst {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String SENIORITY = "seniority";
    }

    public static class CourseConst {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String MIN_GRADE = "minGrade";
        public static final String REQUIRED = "required";
        public static final String LECTURER_ID = "lecturerId";
    }

    public static class StudentCourseConst {
        public static final String ID = "_id";
        public static final String STUDENT_ID = "studentId";
        public static final String COURSE_ID = "courseId";
        public static final String REG_DATE = "regDate";
    }

public static ContentValues StudentToContentValues(Student student) {

    ContentValues contentValues = new ContentValues();
    contentValues.put(AcademyConst.StudentConst.ID, student.getId());
    contentValues.put(AcademyConst.StudentConst.NAME, student.getName());
    contentValues.put(AcademyConst.StudentConst.PHONE, student.getPhone());
    contentValues.put(AcademyConst.StudentConst.YEAR, student.getYear().toString());

    return contentValues;
}

    public static ContentValues LecturerToContentValues(Lecturer lecturer) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(AcademyConst.LecturerConst.ID, lecturer.getId());
        contentValues.put(AcademyConst.LecturerConst.NAME, lecturer.getName());
        contentValues.put(AcademyConst.LecturerConst.PHONE, lecturer.getPhone());
        contentValues.put(AcademyConst.LecturerConst.SENIORITY, lecturer.getSeniority());

        return contentValues;
    }

public static ContentValues CourseToContentValues(Course course) {

    ContentValues contentValues = new ContentValues();
    contentValues.put(AcademyConst.CourseConst.ID, course.getId());
    contentValues.put(AcademyConst.CourseConst.NAME, course.getName());
    contentValues.put(AcademyConst.CourseConst.MIN_GRADE, course.getMinGrade());
    contentValues.put(AcademyConst.CourseConst.REQUIRED, course.isRequired());
    contentValues.put(AcademyConst.CourseConst.LECTURER_ID, course.getLecturerId());

    return contentValues;
}

    public static ContentValues StudentCourseToContentValues(StudentCourse studentCourse) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(AcademyConst.StudentCourseConst.ID, studentCourse.getId());
        contentValues.put(AcademyConst.StudentCourseConst.COURSE_ID, studentCourse.getCourseId());
        contentValues.put(AcademyConst.StudentCourseConst.STUDENT_ID, studentCourse.getStudentId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
        String dateString = dateFormat.format(studentCourse.getRegDate());
        contentValues.put(AcademyConst.StudentCourseConst.REG_DATE, dateString);

        return contentValues;
    }


    public static Student ContentValuesToStudent(ContentValues contentValues) {

        Student student = new Student();
        student.setId(contentValues.getAsLong(AcademyConst.StudentConst.ID));
        student.setName(contentValues.getAsString(AcademyConst.StudentConst.NAME));
        student.setPhone(contentValues.getAsString(AcademyConst.StudentConst.PHONE));
        student.setYear(Year.valueOf(contentValues.getAsString(AcademyConst.StudentConst.YEAR)));

        return student;
    }

    public static Lecturer ContentValuesToLecturer(ContentValues contentValues) {

        Lecturer lecturer = new Lecturer();
        lecturer.setId(contentValues.getAsLong(AcademyConst.LecturerConst.ID));
        lecturer.setName(contentValues.getAsString(AcademyConst.LecturerConst.NAME));
        lecturer.setPhone(contentValues.getAsString(AcademyConst.LecturerConst.PHONE));
        lecturer.setSeniority(contentValues.getAsInteger(AcademyConst.LecturerConst.SENIORITY));

        return lecturer;
    }

public static Course ContentValuesToCourse(ContentValues contentValues) {
    Course course = new Course();
    course.setId(contentValues.getAsLong(AcademyConst.CourseConst.ID));
    course.setName(contentValues.getAsString(AcademyConst.CourseConst.NAME));
    course.setMinGrade(contentValues.getAsInteger(AcademyConst.CourseConst.MIN_GRADE));
    course.setRequired(contentValues.getAsBoolean(AcademyConst.CourseConst.REQUIRED));
    course.setLecturerId(contentValues.getAsLong(AcademyConst.CourseConst.LECTURER_ID));


    return course;
}

    public static StudentCourse ContentValuesToStudentCourse(ContentValues contentValues) {

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(contentValues.getAsLong(AcademyConst.StudentCourseConst.ID));
        studentCourse.setCourseId(contentValues.getAsLong(AcademyConst.StudentCourseConst.COURSE_ID));
        studentCourse.setStudentId(contentValues.getAsLong(AcademyConst.StudentCourseConst.STUDENT_ID));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
        String dateString = contentValues.getAsString(AcademyConst.StudentCourseConst.REG_DATE);
        try {
            studentCourse.setRegDate(dateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return studentCourse;
    }

}
