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

public class InsuranceManagerDashboardController {

    @FXML
    private TableView<Claim> allClaimsTable;
    @FXML
    private TableColumn<Claim, Integer> claimIdColumn;
    @FXML
    private TableColumn<Claim, String> dateColumn;
    @FXML
    private TableColumn<Claim, String> statusColumn;

    @FXML
    public void initialize() {
        claimIdColumn.setCellValueFactory(new PropertyValueFactory<>("claimId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load data into the table
        loadAllClaimsData();
    }

    private void loadAllClaimsData() {
        ObservableList<Claim> claims = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Claims"; // Adjust the SQL query as per your table structure

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                claims.add(new Claim(rs.getInt("claimId"), rs.getString("date"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        allClaimsTable.setItems(claims);
    }
}
