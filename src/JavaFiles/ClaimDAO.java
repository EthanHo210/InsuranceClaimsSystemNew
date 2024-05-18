package JavaFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClaimDAO {
    public Claim getClaimByUserId(int userId) {
        Claim claim = null;
        String sql = "SELECT * FROM Claims WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    claim = new Claim(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("claim_id"),
                            rs.getString("status"),
                            rs.getString("description"),
                            rs.getDate("date_filed"),
                            rs.getDate("date_processed")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claim;
    }
}
