package com.app.lilithscloud;

import javafx.fxml.FXML;

public class MainWindowController {

    private MainApp mainApp;

    @FXML
    private void goToSecondWindow() {
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