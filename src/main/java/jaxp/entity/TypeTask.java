package jaxp.entity;

public class TypeTask {
    private String type;
    private String description;


    public TypeTask(String theoryType, String practicalType) {
        if (!theoryType.equals("")) {
            type = "Theory";
            description = theoryType;
        } else if (!practicalType.equals("")) {
            type = "Practical";
            description = practicalType;
        }
    }

    String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
