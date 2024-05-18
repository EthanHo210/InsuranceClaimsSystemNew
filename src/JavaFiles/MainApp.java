package JavaFiles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Insurance Claims Management System");
            primaryStage.show();

            // Add listener to session to load the appropriate dashboard after login
            SessionManager.currentUserProperty().addListener((obs, oldUser, newUser) -> {
                if (newUser != null) {
                    loadDashboard(primaryStage, newUser);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadDashboard(Stage stage, User user) {
        try {
            String fxmlFile;
            switch (user.getRole()) {
                case 199:
                    fxmlFile = "/fxml/PolicyHolderDashboard.fxml";
                    break;
                case 200:
                    fxmlFile = "/fxml/DependentDashboard.fxml";
                    break;
                case 201:
                    fxmlFile = "/fxml/PolicyOwnerDashboard.fxml";
                    break;
                case 202:
                    fxmlFile = "/fxml/InsuranceSurveyorDashboard.fxml";
                    break;
                case 203:
                    fxmlFile = "/fxml/InsuranceManagerDashboard.fxml";
                    break;
                case 204:
                    fxmlFile = "/fxml/SystemAdminDashboard.fxml";
                    break;
                default:
                    throw new IllegalStateException("Unknown role ID: " + user.getRole());
            }
            Parent dashboard = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage.setScene(new Scene(dashboard));
            stage.setTitle("Insurance Claims Management System - " + user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}