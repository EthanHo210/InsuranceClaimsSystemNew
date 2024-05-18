package JavaFiles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DependentDashboardController {

    @FXML
    private TableView<Dependent> dependentsTable;
    @FXML
    private TableColumn<Dependent, Integer> dependentIdColumn;
    @FXML
    private TableColumn<Dependent, String> nameColumn;
    @FXML
    private TableColumn<Dependent, String> relationshipColumn;

    @FXML
    private Label fullName;
    @FXML
    private Label email;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label address;
    @FXML
    private Label claimId;
    @FXML
    private Label claimStatus;
    @FXML
    private Label dateFilled;
    @FXML
    private Label dateProcessed;
    @FXML
    private Label beneficiary;

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getCurrentUser();
        Claim claim = fetchClaimForUser(currentUser);
        Beneficiary beneficiaryInfo = fetchBeneficiaryForUser(currentUser);

        fullName.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhone());
        address.setText(currentUser.getAddress());
        claimId.setText(claim != null ? String.valueOf(claim.getClaimId()) : "");
        claimStatus.setText(claim != null ? claim.getStatus() : "");
        dateFilled.setText(claim != null ? claim.getDateFilled().toString() : "");
        dateProcessed.setText(claim != null && claim.getDateProcessed() != null ? claim.getDateProcessed().toString() : "");
        beneficiary.setText(beneficiaryInfo != null ? beneficiaryInfo.getRelationship() : "");

        loadDependentsData();
        loadClaimantInformation();
    }

    private void loadDependentsData() {
        ObservableList<Dependent> dependents = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Users"; // Adjust the SQL query as per your table structure

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                dependents.add(new Dependent(rs.getInt("dependentId"), rs.getString("name"), rs.getString("relationship")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dependentsTable.setItems(dependents);
    }

    private void loadClaimantInformation() {
        User currentUser = SessionManager.getCurrentUser();
        Claim claim = fetchClaimForUser(currentUser);
        Beneficiary beneficiary = fetchBeneficiaryForUser(currentUser);
        fullName.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhone());
        address.setText(currentUser.getAddress());
        claimId.setText(claim != null ? String.valueOf(claim.getClaimId()) : "");
        claimStatus.setText(claim != null ? claim.getStatus() : "");
        dateFilled.setText(claim != null ? claim.getDateFilled().toString() : "");
        dateProcessed.setText(claim != null && claim.getDateProcessed() != null ? claim.getDateProcessed().toString() : "");
        this.beneficiary.setText(beneficiary != null ? beneficiary.getRelationship() : "");
    }

    private Claim fetchClaimForUser(User user) {
        String sql = "SELECT claim_id, user_id, status, date_filled, date_processed FROM claims WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getUserId());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int claimId = rs.getInt("claim_id");
                int userId = rs.getInt("user_id");
                String status = rs.getString("status");

                java.sql.Date sqlDateFilled = rs.getDate("date_filled");
                java.util.Date dateFilled = sqlDateFilled != null ? new java.util.Date(sqlDateFilled.getTime()) : null;

                java.sql.Date sqlDateProcessed = rs.getDate("date_processed");
                java.util.Date dateProcessed = sqlDateProcessed != null ? new java.util.Date(sqlDateProcessed.getTime()) : null;

                String description = ""; // Provide an appropriate description if required

                return new Claim(claimId, userId, status, dateFilled, dateProcessed, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Beneficiary fetchBeneficiaryForUser(User user) {
        // Implement the logic to fetch the beneficiary for the user
        // This can be from the database using a DAO class
        BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();
        return beneficiaryDAO.getBeneficiaryByUserId(user.getUserId());
    }
}
