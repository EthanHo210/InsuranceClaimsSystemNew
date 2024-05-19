/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PolicyHolderDAO {

    public void createPolicyHolder(PolicyHolder policyHolder) {
        String sql = "INSERT INTO JavaFiles.PolicyHolder (name, dob, address) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, policyHolder.getName());
            pstmt.setDate(2, policyHolder.getDob());
            pstmt.setString(3, policyHolder.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PolicyHolder getPolicyHolder(int id) {
        String sql = "SELECT * FROM JavaFiles.PolicyHolder WHERE id = ?";
        PolicyHolder policyHolder = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                policyHolder = new PolicyHolder(rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policyHolder;
    }

    public List<PolicyHolder> getAllPolicyHolders() {
        String sql = "SELECT * FROM JavaFiles.PolicyHolder";
        List<PolicyHolder> policyHolders = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                policyHolders.add(new PolicyHolder(rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policyHolders;
    }

    public void updatePolicyHolder(PolicyHolder policyHolder) {
        String sql = "UPDATE JavaFiles.PolicyHolder SET name = ?, dob = ?, address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, policyHolder.getName());
            pstmt.setDate(2, policyHolder.getDob());
            pstmt.setString(3, policyHolder.getAddress());
            pstmt.setInt(4, policyHolder.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePolicyHolder(int id) {
        String sql = "DELETE FROM JavaFiles.PolicyHolder WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
