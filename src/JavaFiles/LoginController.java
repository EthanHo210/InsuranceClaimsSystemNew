package JavaFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Username entered: " + username);
        System.out.println("Password entered: " + password);

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username and password cannot be empty.");
            return;
        }

        User user = userDAO.authenticate(username, password);

        if (user != null) {
            System.out.println("User authenticated: " + user.getUsername());
            SessionManager.setCurrentUser(user); // Set the current user in session
            switch (user.getRole()) {
                case 199:
                    loadDashboard("fxml/PolicyHolderDashboard.fxml");
                    break;
                case 200:
                    loadDashboard("fxml/DependentDashboard.fxml");
                    break;
                case 201:
                    loadDashboard("fxml/PolicyOwnerDashboard.fxml");
                    break;
                case 202:
                    loadDashboard("fxml/InsuranceSurveyorDashboard.fxml");
                    break;
                case 203:
                    loadDashboard("fxml/InsuranceManagerDashboard.fxml");
                    break;
                case 204:
                    loadDashboard("fxml/SystemAdminDashboard.fxml");
                    break;
                default:
                    messageLabel.setText("Unknown role: " + user.getRole());
                    break;
            }
        } else {
            System.out.println("Invalid username or password");
            messageLabel.setText("Invalid username or password");
        }
    }


    private void loadDashboard(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load the dashboard.");
        }
    }
}
