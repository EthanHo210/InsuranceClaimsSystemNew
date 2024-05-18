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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Add getRole method
    public int getRole() {
        return roleId;
    }
}
