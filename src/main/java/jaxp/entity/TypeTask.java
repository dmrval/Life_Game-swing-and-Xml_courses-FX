package jaxp.entity;

public class TypeTask {
    String type;
    String description;


    public TypeTask(String theoryType, String practicalType) {
        if (!theoryType.equals("")) {
            type = "Theory";
            description = theoryType;
        } else if (!practicalType.equals("")) {
            type = "Practical";
            description = practicalType;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
