package jaxp.entity;

import java.util.ArrayList;
import java.util.List;

public class University {
    List<Student> studentList;

    public University() {
        studentList = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
