package com.app.lilithscloud;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class CloudController {

    @FXML
    private TextField filePathField;

    @FXML
    private ListView<String> fileListView;

    private MainApp mainApp;
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
        loadFileList(); // Загружаем список файлов при установке userId
    }

    @FXML
    private void initialize() {
        // Пустой метод, так как загрузка файлов будет происходить при установке userId
    }

    @FXML
    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void uploadFile() {
        String filePath = filePathField.getText();
        if (filePath.isEmpty()) {
            System.out.println("File not selected.");
            return;
        }

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] fileData = fis.readAllBytes();
            fis.close();

            DatabaseConnection.uploadFile(file.getName(), fileData, userId);
            System.out.println("File uploaded successfully.");

            // Обновляем список файлов после загрузки
            loadFileList();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("Err with uploading file: " + e.getMessage());
        }
    }

    @FXML
    private void downloadFile() {
        String selectedFileName = fileListView.getSelectionModel().getSelectedItem();
        if (selectedFileName == null) {
            System.out.println("File not selected.");
            return;
        }

        try {
            byte[] fileData = DatabaseConnection.downloadFile(selectedFileName, userId);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(selectedFileName);
            File saveFile = fileChooser.showSaveDialog(null);

            if (saveFile != null) {
                FileOutputStream fos = new FileOutputStream(saveFile);
                fos.write(fileData);
                fos.close();
                System.out.println("The file has been downloaded successfully.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Err with downloading file: " + e.getMessage());
        }
    }

    @FXML
    private void deleteFile() {
        String selectedFileName = fileListView.getSelectionModel().getSelectedItem();
        if (selectedFileName == null) {
            System.out.println("File not selected");
            return;
        }

        try {
            DatabaseConnection.deleteFile(selectedFileName, userId);
            System.out.println("File successfully added.");

            // Обновляем список файлов после удаления
            loadFileList();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Err with deleting file: " + e.getMessage());
        }
    }

    @FXML
    private void refreshPage() {
        loadFileList();
    }

    private void loadFileList() {
        try {
            ObservableList<String> fileList = FXCollections.observableArrayList(DatabaseConnection.getFileList(userId));
            Platform.runLater(() -> fileListView.setItems(fileList));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Err with downloading list of files: " + e.getMessage());
        }
    }

    @FXML
    private void logout() {
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