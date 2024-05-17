package JavaFiles;

public class User {
    private int userId;
    private String username;
    private String hashedPassword;
    private String email;
    private String phone;
    private String address;
    private int roleId;

    // Constructor matching the expected parameters
    public User(int userId, String username, String hashedPassword, String email, String phone, String address, int roleId) {
        this.userId = userId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
    }

    // Another constructor for cases where not all fields are needed
    public User(int userId, String username, String hashedPassword, int roleId) {
        this.userId = userId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.roleId = roleId;
    }

    // Getters and setters for each field
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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

    // Add getRole method
    public int getRole() {
        return roleId;
    }
}
