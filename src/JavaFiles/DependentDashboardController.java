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
    private TableView<Dependent> dependentsTable;
    @FXML
    private TableColumn<Dependent, Integer> dependentIdColumn;
    @FXML
    private TableColumn<Dependent, String> nameColumn;
    @FXML
    private TableColumn<Dependent, String> relationshipColumn;

    @FXML
    public void initialize() {
        dependentIdColumn.setCellValueFactory(new PropertyValueFactory<>("dependentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        relationshipColumn.setCellValueFactory(new PropertyValueFactory<>("relationship"));

        // Load data into the table
        loadDependentsData();
    }

    private void loadDependentsData() {
        ObservableList<Dependent> dependents = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Dependents"; // Adjust the SQL query as per your table structure

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
}
