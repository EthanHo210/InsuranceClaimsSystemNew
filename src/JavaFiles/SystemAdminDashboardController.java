package JavaFiles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public class SystemAdminDashboardController {
    @FXML
    private TableView<Claim> dependentTable;
    @FXML
    private TableColumn<Claim, Integer> userIdColumn;
    @FXML
    private TableColumn<Claim, String> usernameColumn;
    @FXML
    private TableColumn<Claim, String> emailColumn;
    @FXML
    private TableColumn<Claim, String> phoneColumn;
    @FXML
    private TableColumn<Claim, String> addressColumn;
    @FXML
    private TableColumn<Claim, Integer> claimIdColumn;
    @FXML
    private TableColumn<Claim, String> statusColumn;
    @FXML
    private TableColumn<Claim, String> descriptionColumn;
    @FXML
    private TableColumn<Claim, Date> dateFiledColumn;
    @FXML
    private TableColumn<Claim, Date> dateProcessedColumn;

    private String currentUsername;

    @FXML
    public void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        claimIdColumn.setCellValueFactory(new PropertyValueFactory<>("claimId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateFiledColumn.setCellValueFactory(new PropertyValueFactory<>("dateFiled"));
        dateProcessedColumn.setCellValueFactory(new PropertyValueFactory<>("dateProcessed"));
    }

    public void setUsername(String username) {
        this.currentUsername = username;
        loadDependentData();
    }

    @FXML
    public void loadDependentData() {
        ObservableList<Claim> claims = FXCollections.observableArrayList();
        String sql = "SELECT users.user_id, users.username, users.email, users.phone, users.address, claims.claim_id, claims.status, claims.description, claims.date_filed, claims.date_processed " +
                "FROM users " +
                "INNER JOIN claims ON users.user_id = claims.user_id " +
                "WHERE users.username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, currentUsername);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int claimId = rs.getInt("claim_id");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    Date dateFiled = rs.getDate("date_filed");
                    Date dateProcessed = rs.getDate("date_processed");


                    claims.add(new Claim(userId, username, email, phone, address, claimId, status, description, dateFiled, dateProcessed));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dependentTable.setItems(claims);
    }
}