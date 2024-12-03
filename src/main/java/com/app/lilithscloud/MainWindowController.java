package com.app.lilithscloud;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.application.HostServices;

public class MainWindowController {

    private MainApp mainApp;
    private HostServices hostServices;

    @FXML
    private void goToSecondWindow() {
        try {
            mainApp.showLoginWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showInfo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);

        Hyperlink githubLink = new Hyperlink("My GitHub");
        githubLink.setOnAction(e -> {
            hostServices.showDocument("https://github.com/LilithTC");
        });

        Text text = new Text("| Mady by STV.LTH");

        TextFlow textFlow = new TextFlow(githubLink, text);
        alert.getDialogPane().setContent(textFlow);

        alert.showAndWait();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}