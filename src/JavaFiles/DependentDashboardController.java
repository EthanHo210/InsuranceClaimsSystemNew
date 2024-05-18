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

public class DependentDashboardController {

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

    private String currentUsername;

    @FXML
    public void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // Initial data load is not done here since it depends on a specific username
    }

    public void setUsername(String username) {
        this.currentUsername = username;
        loadDependentData();
    }

    @FXML
    public void loadDependentData() {
        ObservableList<User> dependents = FXCollections.observableArrayList();
        String sql = "SELECT user_id, username, email, phone FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, currentUsername);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");

                    dependents.add(new User(userId, username, null, email, phone, null, 200));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dependentTable.setItems(dependents);
    }
}