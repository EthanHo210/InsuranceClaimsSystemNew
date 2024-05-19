/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

public class Policy {
    private int policyId;
    private String policyName;
    private String coverage;

    // Constructor with all fields
    public Policy(int policyId, String policyName, String coverage) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.coverage = coverage;
    }

    // Getters and setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
}
