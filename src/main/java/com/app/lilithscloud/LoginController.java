package com.app.lilithscloud;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private MainApp mainApp;

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        int userId = DatabaseConnection.loginUser(username, password);
        String statusMessage = userId != -1 ? "you have successfully logged in." : "you have successfully logged out.";

        // Обновляем интерфейс в потоке JavaFX
        Platform.runLater(() -> {
            statusLabel.setText(statusMessage);
            if (userId != -1) {
                // Переходим на страницу Cloud при успешном входе
                try {
                    mainApp.showCloudWindow(userId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void goToRegisterWindow() {
        try {
            mainApp.showRegisterWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}