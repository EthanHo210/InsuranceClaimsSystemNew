package JavaFiles;

import java.util.Date;

public class Claim {
    private int claimId;
    private int userId;
    private String status;
    private Date dateFilled;
    private Date dateProcessed;
    private String description;

    public Claim(int claimId, int userId, String status, Date dateFilled, Date dateProcessed, String description) {
        this.claimId = claimId;
        this.userId = userId;
        this.status = status;
        this.dateFilled = dateFilled;
        this.dateProcessed = dateProcessed;
        this.description = description;
    }

    // Getters
    public int getClaimId() {
        return claimId;
    }

    public int getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateFilled() {
        return dateFilled;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateFilled(Date dateFilled) {
        this.dateFilled = dateFilled;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
