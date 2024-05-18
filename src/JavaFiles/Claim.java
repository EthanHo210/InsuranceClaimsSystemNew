package JavaFiles;

import java.util.Date;

public class Claim {
    private int claimId;
    private String status;
    private Date dateFilled;
    private Date dateProcessed;

    // Constructor with all fields
    public Claim(int claimId, String status, Date dateFilled, Date dateProcessed) {
        this.claimId = claimId;
        this.status = status;
        this.dateFilled = dateFilled;
        this.dateProcessed = dateProcessed;
    }

    // Getters and setters
    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateFilled() {
        return dateFilled;
    }

    public void setDateFilled(Date dateFilled) {
        this.dateFilled = dateFilled;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }
}
