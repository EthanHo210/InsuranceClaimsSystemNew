/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */

package JavaFiles;

import java.util.Date;

public class Claim {
    private int userId;
    private String username;
    private String email;
    private String phone;
    private String address;
    private int claimId;
    private String status;
    private String description;
    private Date dateFiled;
    private Date dateProcessed;

    public Claim(int userId, String username, String email, String phone, String address, int claimId, String status, String description, Date dateFiled, Date dateProcessed) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.claimId = claimId;
        this.status = status;
        this.description = description;
        this.dateFiled = dateFiled;
        this.dateProcessed = dateProcessed;
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getClaimId() { return claimId; }
    public void setClaimId(int claimId) { this.claimId = claimId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateFiled() { return dateFiled; }
    public void setDateFiled(Date dateFiled) { this.dateFiled = dateFiled; }

    public Date getDateProcessed() { return dateProcessed; }
    public void setDateProcessed(Date dateProcessed) { this.dateProcessed = dateProcessed; }
}
