package jaxp.entity;

import java.util.List;

public class Course implements StringForTreeView {
    private String courceName;
    private String courceAuthor;
    private String courceCreationDate;
    private List<Task> taskList;
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


    @Override
    public String toString() {
        return courceName;
    }

    @Override
    public String treeViewtoString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ");
        stringBuilder.append(courceName);
        stringBuilder.append("\n");
        stringBuilder.append("Author: ");
        stringBuilder.append(courceAuthor);
        stringBuilder.append("\n");
        stringBuilder.append("Creation Date: ");
        stringBuilder.append(courceCreationDate);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
