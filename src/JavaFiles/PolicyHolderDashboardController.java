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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PolicyHolderDashboardController {

    @FXML
    private TableView<Claim> claimsTable;
    @FXML
    private TableColumn<Claim, Integer> claimIdColumn;
    @FXML
    private TableColumn<Claim, String> dateColumn;
    @FXML
    private TableColumn<Claim, String> statusColumn;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    public void initialize() {
        claimIdColumn.setCellValueFactory(new PropertyValueFactory<>("claimId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateFilled"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load data into the table
        loadClaimsData();
    }

    private void loadClaimsData() {
        ObservableList<Claim> claims = FXCollections.observableArrayList();
        String sql = "SELECT claim_id, user_id, status, date_filled, date_processed, description FROM Claims"; // Adjust the SQL query as per your table structure

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int claimId = rs.getInt("claim_id");
                int userId = rs.getInt("user_id");
                String status = rs.getString("status");

                Date dateFilled = parseDate(rs.getString("date_filled"));
                Date dateProcessed = parseDate(rs.getString("date_processed"));
                String description = rs.getString("description");

                claims.add(new Claim(claimId, userId, status, dateFilled, dateProcessed, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        claimsTable.setItems(claims);
    }

    private Date parseDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
