/**
 Ho Anh Khoa, Nguyen Minh Phu & Nguyen Linh Chi <Group 13> <Group 13>
 */
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

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Implement your login logic here
        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticate(username, password);

        if (user != null) {
            SessionManager.setCurrentUser(user);
            loginMessageLabel.setText("Login successful!");
            // Transition to the appropriate dashboard
            loadDashboard(user);
        } else {
            loginMessageLabel.setText("Invalid username or password");
        }
    }

    private void loadDashboard(User user) {
        try {
            String fxmlFile;
            switch (user.getRoleId()) {
                case 199:
                    fxmlFile = "/resources/fxml/PolicyHolderDashboard.fxml";
                    break;
                case 200:
                    fxmlFile = "/resources/fxml/DependentDashboard.fxml";
                    break;
                case 201:
                    fxmlFile = "/resources/fxml/PolicyOwnerDashboard.fxml";
                    break;
                case 202:
                    fxmlFile = "/resources/fxml/InsuranceSurveyorDashboard.fxml";
                    break;
                case 203:
                    fxmlFile = "/resources/fxml/InsuranceManagerDashboard.fxml";
                    break;
                case 204:
                    fxmlFile = "/resources/fxml/SystemAdminDashboard.fxml";
                    break;
                default:
                    fxmlFile = "/resources/fxml/Login.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent dashboard = loader.load();

            if (user.getRoleId() == 199) {  // For Dependent role
                PolicyHolderDashboardController controller = loader.getController();
                controller.setUsername(user.getUsername());
            }else if (user.getRoleId() == 200){
                DependentDashboardController controller = loader.getController();
                controller.setUsername(user.getUsername());
            }else if (user.getRoleId() == 201){
                PolicyOwnerDashboardController controller = loader.getController();
                controller.setUsername(user.getUsername());
            }else if (user.getRoleId() == 202){
                InsuranceSurveyorDashboardController controller = loader.getController();
                controller.setUsername(user.getUsername());
            }else if (user.getRoleId() == 203){
                InsuranceManagerDashboardController controller = loader.getController();
                controller.setUsername(user.getUsername());
            }else if (user.getRoleId() == 204){
                SystemAdminDashboardController controller = loader.getController();
                controller.setUsername(user.getUsername());
            }

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(dashboard));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}