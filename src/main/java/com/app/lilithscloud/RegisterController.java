package com.app.lilithscloud;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private MainApp mainApp;

    @FXML
    private void register() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            DatabaseConnection.registerUser(username, password, email);
            String statusMessage = "User registered successfully!";

            // Обновляем интерфейс в потоке JavaFX
            Platform.runLater(() -> {
                statusLabel.setText(statusMessage);
                // Переходим на страницу входа после успешной регистрации
                try {
                    mainApp.showLoginWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            String errorMessage = "Registration failed: " + e.getMessage();
            // Обновляем интерфейс в потоке JavaFX
            Platform.runLater(() -> statusLabel.setText(errorMessage));
        }
    }

    @FXML
    private void goBackToMainWindow() {
        try {
            mainApp.showLoginWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}