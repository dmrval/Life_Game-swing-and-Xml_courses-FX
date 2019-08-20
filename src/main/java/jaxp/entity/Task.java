package jaxp.entity;

public class Task {
    String taskName;
    int assessment;
    int hourse;
    TypeTask typeTask;


    public Task() {
    }

    public Task(String taskName, int assessment, int hourse, TypeTask typeTask) {
        this.taskName = taskName;
        this.assessment = assessment;
        this.hourse = hourse;
        this.typeTask = typeTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public int getHourse() {
        return hourse;
    }

    public void setHourse(int hourse) {
        this.hourse = hourse;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }


}
