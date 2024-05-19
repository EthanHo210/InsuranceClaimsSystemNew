/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

import java.util.Date;

public class User {
    private int userId;
    private String username;
    private String email;
    private String phone;
    private String address;
    private int roleId;
    private int claimId;
    private String claimStatus;
    private String claimDescription;
    private Date claimDateFiled;
    private Date claimDateProcessed;
    private int documentId;
    private String originalName;
    private String systemName;
    private Date documentUploadDate;
    private int beneficiaryId;
    private String relationship;
    private double dependentRate;
    private int logId;
    private String action;
    private String entityAffected;
    private Date logTimestamp;
    private int paymentId;
    private int paymentYear;
    private double totalAmount;
    private String roleName;
    private int permissionId;
    private String permission;

    public User(int userId, String username, String email, String phone, String address, int roleId, int claimId, String claimStatus, String claimDescription, Date claimDateFiled, Date claimDateProcessed, int documentId, String originalName, String systemName, Date documentUploadDate, int beneficiaryId, String relationship, double dependentRate, int logId, String action, String entityAffected, Date logTimestamp, int paymentId, int paymentYear, double totalAmount, String roleName, int permissionId, String permission) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.claimId = claimId;
        this.claimStatus = claimStatus;
        this.claimDescription = claimDescription;
        this.claimDateFiled = claimDateFiled;
        this.claimDateProcessed = claimDateProcessed;
        this.documentId = documentId;
        this.originalName = originalName;
        this.systemName = systemName;
        this.documentUploadDate = documentUploadDate;
        this.beneficiaryId = beneficiaryId;
        this.relationship = relationship;
        this.dependentRate = dependentRate;
        this.logId = logId;
        this.action = action;
        this.entityAffected = entityAffected;
        this.logTimestamp = logTimestamp;
        this.paymentId = paymentId;
        this.paymentYear = paymentYear;
        this.totalAmount = totalAmount;
        this.roleName = roleName;
        this.permissionId = permissionId;
        this.permission = permission;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public Date getClaimDateFiled() {
        return claimDateFiled;
    }

    public void setClaimDateFiled(Date claimDateFiled) {
        this.claimDateFiled = claimDateFiled;
    }

    public Date getClaimDateProcessed() {
        return claimDateProcessed;
    }

    public void setClaimDateProcessed(Date claimDateProcessed) {
        this.claimDateProcessed = claimDateProcessed;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Date getDocumentUploadDate() {
        return documentUploadDate;
    }

    public void setDocumentUploadDate(Date documentUploadDate) {
        this.documentUploadDate = documentUploadDate;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public double getDependentRate() {
        return dependentRate;
    }

    public void setDependentRate(double dependentRate) {
        this.dependentRate = dependentRate;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntityAffected() {
        return entityAffected;
    }

    public void setEntityAffected(String entityAffected) {
        this.entityAffected = entityAffected;
    }

    public Date getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(Date logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentYear() {
        return paymentYear;
    }

    public void setPaymentYear(int paymentYear) {
        this.paymentYear = paymentYear;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
