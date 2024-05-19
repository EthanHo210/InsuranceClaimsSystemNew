package JavaFiles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class InsuranceManagerDashboardController {

    @FXML
    private TableView<User> dependentTable;
    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, Integer> roleIdColumn;
    @FXML
    private TableColumn<User, Integer> claimIdColumn;
    @FXML
    private TableColumn<User, String> claimStatusColumn;
    @FXML
    private TableColumn<User, String> claimDescriptionColumn;
    @FXML
    private TableColumn<User, Date> claimDateFiledColumn;
    @FXML
    private TableColumn<User, Date> claimDateProcessedColumn;
    @FXML
    private TableColumn<User, Integer> documentIdColumn;
    @FXML
    private TableColumn<User, String> originalNameColumn;
    @FXML
    private TableColumn<User, String> systemNameColumn;
    @FXML
    private TableColumn<User, Date> documentUploadDateColumn;
    @FXML
    private TableColumn<User, Integer> beneficiaryIdColumn;
    @FXML
    private TableColumn<User, String> relationshipColumn;
    @FXML
    private TableColumn<User, Double> dependentRateColumn;
    @FXML
    private TableColumn<User, Integer> logIdColumn;
    @FXML
    private TableColumn<User, String> actionColumn;
    @FXML
    private TableColumn<User, String> entityAffectedColumn;
    @FXML
    private TableColumn<User, Date> logTimestampColumn;
    @FXML
    private TableColumn<User, Integer> paymentIdColumn;
    @FXML
    private TableColumn<User, Integer> paymentYearColumn;
    @FXML
    private TableColumn<User, Double> totalAmountColumn;
    @FXML
    private TableColumn<User, String> roleNameColumn;
    @FXML
    private TableColumn<User, Integer> permissionIdColumn;
    @FXML
    private TableColumn<User, String> permissionColumn;

    private String currentUsername; // Add this line to define the currentUsername field

    @FXML
    public void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleIdColumn.setCellValueFactory(new PropertyValueFactory<>("roleId"));
        claimIdColumn.setCellValueFactory(new PropertyValueFactory<>("claimId"));
        claimStatusColumn.setCellValueFactory(new PropertyValueFactory<>("claimStatus"));
        claimDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("claimDescription"));
        claimDateFiledColumn.setCellValueFactory(new PropertyValueFactory<>("claimDateFiled"));
        claimDateProcessedColumn.setCellValueFactory(new PropertyValueFactory<>("claimDateProcessed"));
        documentIdColumn.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        originalNameColumn.setCellValueFactory(new PropertyValueFactory<>("originalName"));
        systemNameColumn.setCellValueFactory(new PropertyValueFactory<>("systemName"));
        documentUploadDateColumn.setCellValueFactory(new PropertyValueFactory<>("documentUploadDate"));
        beneficiaryIdColumn.setCellValueFactory(new PropertyValueFactory<>("beneficiaryId"));
        relationshipColumn.setCellValueFactory(new PropertyValueFactory<>("relationship"));
        dependentRateColumn.setCellValueFactory(new PropertyValueFactory<>("dependentRate"));
        logIdColumn.setCellValueFactory(new PropertyValueFactory<>("logId"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        entityAffectedColumn.setCellValueFactory(new PropertyValueFactory<>("entityAffected"));
        logTimestampColumn.setCellValueFactory(new PropertyValueFactory<>("logTimestamp"));
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        paymentYearColumn.setCellValueFactory(new PropertyValueFactory<>("paymentYear"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        roleNameColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        permissionIdColumn.setCellValueFactory(new PropertyValueFactory<>("permissionId"));
        permissionColumn.setCellValueFactory(new PropertyValueFactory<>("permission"));

        loadDependentData(); // Load data initially
    }

    public void setUsername(String username) {
        this.currentUsername = username;
        loadDependentData();
    }

    public void loadDependentData() {
        ObservableList<User> dependents = FXCollections.observableArrayList();
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
                "WHERE u.username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, currentUsername); // Use currentUsername here
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int roleId = rs.getInt("role_id");
                    int claimId = rs.getInt("claim_id");
                    String claimStatus = rs.getString("claim_status");
                    String claimDescription = rs.getString("claim_description");
                    Date claimDateFiled = rs.getDate("claim_date_filed");
                    Date claimDateProcessed = rs.getDate("claim_date_processed");
                    int documentId = rs.getInt("document_id");
                    String originalName = rs.getString("original_name");
                    String systemName = rs.getString("system_name");
                    Date documentUploadDate = rs.getDate("document_upload_date");
                    int beneficiaryId = rs.getInt("beneficiary_id");
                    String relationship = rs.getString("relationship");
                    double dependentRate = rs.getDouble("dependent_rate");
                    int logId = rs.getInt("log_id");
                    String action = rs.getString("action");
                    String entityAffected = rs.getString("entity_affected");
                    Date logTimestamp = rs.getTimestamp("log_timestamp");
                    int paymentId = rs.getInt("payment_id");
                    int paymentYear = rs.getInt("payment_year");
                    double totalAmount = rs.getDouble("total_amount");
                    String roleName = rs.getString("role_name");
                    int permissionId = rs.getInt("permission_id");
                    String permission = rs.getString("permission");

                    dependents.add(new User(
                            userId,
                            username,
                            email,
                            phone,
                            address,
                            roleId,
                            claimId,
                            claimStatus,
                            claimDescription,
                            claimDateFiled,
                            claimDateProcessed,
                            documentId,
                            originalName,
                            systemName,
                            documentUploadDate,
                            beneficiaryId,
                            relationship,
                            dependentRate,
                            logId,
                            action,
                            entityAffected,
                            logTimestamp,
                            paymentId,
                            paymentYear,
                            totalAmount,
                            roleName,
                            permissionId,
                            permission
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dependentTable.setItems(dependents);
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        // Show thank you alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for using the program.");
        alert.show();

        // Close the application after 0.5 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> Platform.exit());
        pause.play();
    }
}
