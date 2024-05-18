package JavaFiles;

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
            stmt.setString(2, password);

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
}
