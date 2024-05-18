package JavaFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeneficiaryDAO {

    public Beneficiary getBeneficiaryByUserId(int userId) {
        Beneficiary beneficiary = null;
        String sql = "SELECT * FROM Beneficiaries WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    beneficiary = new Beneficiary();
                    beneficiary.setUserId(rs.getInt("user_id"));
                    beneficiary.setRelationship(rs.getString("relationship"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beneficiary;
    }
}
