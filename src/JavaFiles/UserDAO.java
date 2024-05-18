package JavaFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User authenticate(String username, String password) {

        // SQL query with placeholders for parameters
        String sql = "SELECT * FROM users WHERE username = ? AND hashed_password = ?";

        // Establish a connection and prepare the statement
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute the query and process the result set
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User found: " + rs.getString("username"));
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("hashed_password"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("role_id")
                    );
                } else {
                    System.out.println("User not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
