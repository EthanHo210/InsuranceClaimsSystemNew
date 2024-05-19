/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static User authenticate(String username, String password) {

        String sql = "SELECT u.user_id, u.username, u.email, u.phone, u.address, u.role_id, " +
                "c.claim_id, c.status AS claim_status, c.description AS claim_description, c.date_filed AS claim_date_filed, c.date_processed AS claim_date_processed, " +
                "d.document_id, d.original_name, d.system_name, d.upload_date AS document_upload_date, " +
                "b.beneficiary_id, b.relationship, b.dependent_rate, " +
                "l.log_id, l.action, l.entity_affected, l.timestamp AS log_timestamp, " +
                "p.payment_id, p.year AS payment_year, p.total_amount, " +
                "r.role_name, " +
                "per.permission_id, per.permission " +
                "FROM users u " +
                "LEFT JOIN claims c ON u.user_id = c.user_id " +
                "LEFT JOIN documents d ON c.claim_id = d.claim_id " +
                "LEFT JOIN beneficiaries b ON u.user_id = b.policy_owner_id " +
                "LEFT JOIN logs l ON u.user_id = l.user_id " +
                "LEFT JOIN payments p ON u.user_id = p.policy_owner_id " +
                "LEFT JOIN roles r ON u.role_id = r.role_id " +
                "LEFT JOIN permissions per ON u.role_id = per.role_id " +
                "WHERE u.username = ? AND u.hashed_password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);  // Use hashed password for comparison
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("role_id"),
                            rs.getInt("claim_id"),
                            rs.getString("claim_status"),
                            rs.getString("claim_description"),
                            rs.getDate("claim_date_filed"),
                            rs.getDate("claim_date_processed"),
                            rs.getInt("document_id"),
                            rs.getString("original_name"),
                            rs.getString("system_name"),
                            rs.getDate("document_upload_date"),
                            rs.getInt("beneficiary_id"),
                            rs.getString("relationship"),
                            rs.getDouble("dependent_rate"),
                            rs.getInt("log_id"),
                            rs.getString("action"),
                            rs.getString("entity_affected"),
                            rs.getTimestamp("log_timestamp"),
                            rs.getInt("payment_id"),
                            rs.getInt("payment_year"),
                            rs.getDouble("total_amount"),
                            rs.getString("role_name"),
                            rs.getInt("permission_id"),
                            rs.getString("permission")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
