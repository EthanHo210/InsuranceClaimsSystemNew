package JavaFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
                            rs.getInt("claim_id"),
                            rs.getString("status"),
                            rs.getDate("date_filled"),
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
