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

public class PolicyOwnerDashboardController {

    @FXML
    private TableView<Policy> policiesTable;
    @FXML
    private TableColumn<Policy, Integer> policyIdColumn;
    @FXML
    private TableColumn<Policy, String> policyNameColumn;
    @FXML
    private TableColumn<Policy, String> coverageColumn;

    @FXML
    public void initialize() {
        policyIdColumn.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        policyNameColumn.setCellValueFactory(new PropertyValueFactory<>("policyName"));
        coverageColumn.setCellValueFactory(new PropertyValueFactory<>("coverage"));

        // Load data into the table
        loadPoliciesData();
    }

    private void loadPoliciesData() {
        ObservableList<Policy> policies = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Policies"; // Adjust the SQL query as per your table structure

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                policies.add(new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getString("coverage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        policiesTable.setItems(policies);
    }
}
