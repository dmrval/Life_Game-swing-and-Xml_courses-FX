package jaxp.entity;

public class Student implements StringForTreeView {
    private String fullName;
    private String city;
    private String email;
    private String trainingStartDate;
    private boolean signedAcontract;
    private Program program;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrainingStartDate() {
        return trainingStartDate;
    }

    public void setTrainingStartDate(String trainingStartDate) {
        this.trainingStartDate = trainingStartDate;
    }

    public boolean isSignedAcontract() {
        return signedAcontract;
    }

    public void setSignedAcontract(boolean signedAcontract) {
        this.signedAcontract = signedAcontract;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public String treeViewtoString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Fullname: ");
        stringBuilder.append(fullName);
        stringBuilder.append("\n");
        stringBuilder.append("Region: ");
        stringBuilder.append(city);
        stringBuilder.append("\n");
        stringBuilder.append("Email: ");
        stringBuilder.append(email);
        stringBuilder.append("\n");
        stringBuilder.append("Start Date: ");
        stringBuilder.append(trainingStartDate);
        stringBuilder.append("\n");
        stringBuilder.append("Contract signing: ");
        if (signedAcontract) {
            stringBuilder.append("Yes");
        } else {
            stringBuilder.append("No");
        }
        return stringBuilder.toString();
    }
}
