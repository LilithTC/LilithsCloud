package com.app.lilithscloud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.HostServices;

public class MainApp extends Application {

    private Stage primaryStage;
    private HostServices hostServices;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.hostServices = getHostServices();
        showMainWindow();
    }

    public void showMainWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liliths Cloud | Main Page ");
        primaryStage.setResizable(false); // Устанавливаем невозможность изменения размера окна
        primaryStage.show();

        // Получаем контроллер для настройки перехода к окну входа
        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setMainApp(this);
        mainWindowController.setHostServices(hostServices);
    }

    public void showLoginWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liliths Cloud | Login");
        primaryStage.setResizable(false); //изменения размера окна
        primaryStage.show();

        //контроллер для настройки перехода к окну регистрации
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
    }

    public void showRegisterWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liliths Cloud | Register");
        primaryStage.setResizable(false); //изменения размера окна
        primaryStage.show();

        //контроллер для настройки перехода к окну входа
        RegisterController registerController = loader.getController();
        registerController.setMainApp(this);
    }

    public void showCloudWindow(int userId) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cloud.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liliths Cloud | Cloud");
        primaryStage.setResizable(false); //изменения размера окна
        primaryStage.show();

        // Получаем контроллер для настройки выхода из аккаунта
        CloudController cloudController = loader.getController();
        cloudController.setMainApp(this);
        cloudController.setUserId(userId);
    }

    public static void main(String[] args) {
        launch(args);
    }
}