package jaxp.entity;

public class Task implements StringForTreeView {
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


    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public String treeViewtoString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ");
        stringBuilder.append(taskName);
        stringBuilder.append("\n");
        stringBuilder.append("Assessment: ");
        stringBuilder.append(assessment);
        stringBuilder.append("\n");
        stringBuilder.append("Hourse: ");
        stringBuilder.append(hourse);
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        if (typeTask!= null ) {
            stringBuilder.append("Task type: ");
            stringBuilder.append(typeTask.type);
            stringBuilder.append("\n");
            stringBuilder.append("Task description: ");
            stringBuilder.append(typeTask.description);
        }
        return stringBuilder.toString();
    }
}
