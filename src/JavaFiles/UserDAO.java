package JavaFiles;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND hashed_password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Use the hashed password

            System.out.println("Executing query: " + stmt.toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("hashed_password"),
                        rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getInt("role_id"));
            } else {
                System.out.println("No user found with provided credentials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
