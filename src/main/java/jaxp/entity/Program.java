package jaxp.entity;

import java.util.List;

public class Program {
    String progName;
    String progAuthor;
    String creationDate;
    List<Course> courseList;

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public String getProgAuthor() {
        return progAuthor;
    }

    public void setProgAuthor(String progAuthor) {
        this.progAuthor = progAuthor;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "\t" + "Program{" +
                "progName='" + progName + '\'' +
                ", progAuthor='" + progAuthor + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", courseList=" + courseList +
                '}';
    }

}
