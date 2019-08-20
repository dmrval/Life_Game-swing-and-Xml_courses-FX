package jaxp.entity;

import java.util.List;

public class Course {
    String courceName;
    String courceAuthor;
    String courceCreationDate;
    List<Task> taskList;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public String getCourceAuthor() {
        return courceAuthor;
    }

    public void setCourceAuthor(String courceAuthor) {
        this.courceAuthor = courceAuthor;
    }

    public String getCourceCreationDate() {
        return courceCreationDate;
    }

    public void setCourceCreationDate(String courceCreationDate) {
        this.courceCreationDate = courceCreationDate;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }


}
