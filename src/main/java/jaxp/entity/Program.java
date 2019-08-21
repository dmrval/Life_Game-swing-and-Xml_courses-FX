package jaxp.entity;

import java.util.List;

public class Program implements StringForTreeView {
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
        return progName;
    }

    @Override
    public String treeViewtoString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Programm: ");
        stringBuilder.append(progName);
        stringBuilder.append("\n");
        stringBuilder.append("Programm Author: ");
        stringBuilder.append(progAuthor);
        stringBuilder.append("\n");
        stringBuilder.append("Creation Date: ");
        stringBuilder.append(creationDate);
        return stringBuilder.toString();
    }
}
