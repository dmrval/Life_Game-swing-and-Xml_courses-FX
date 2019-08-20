package jaxp.entity;

public class Student {
    String fullName;
    String city;
    String email;
    String trainingStartDate;
    boolean signedAcontract;
    Program program;

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


}
