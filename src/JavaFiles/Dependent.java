/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

public class Dependent {
    private int dependentId;
    private String name;
    private String relationship;

    // Constructor with all fields
    public Dependent(int dependentId, String name, String relationship) {
        this.dependentId = dependentId;
        this.name = name;
        this.relationship = relationship;
    }

    // Getters and setters
    public int getDependentId() {
        return dependentId;
    }

    public void setDependentId(int dependentId) {
        this.dependentId = dependentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
