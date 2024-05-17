package JavaFiles;

public class Claim {
    private int claimId;
    private String date;
    private String status;

    // Constructor with all fields
    public Claim(int claimId, String date, String status) {
        this.claimId = claimId;
        this.date = date;
        this.status = status;
    }

    // Getters and setters
    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
