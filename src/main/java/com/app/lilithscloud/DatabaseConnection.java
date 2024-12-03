package com.app.lilithscloud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    // URL для подключения к базе данных
    private static final String URL = "jdbc:mysql://;
    // Имя пользователя для подключения
    private static final String USER = "";
    // Пароль для подключения
    private static final String PASSWORD = "";

    public static void registerUser(String username, String password, String email) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();
            System.out.println("User registered successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int loginUser(String username, String password) {
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Возвращаем -1, если вход не удался
    }

    public static void uploadFile(String filename, byte[] fileData, int userId) throws SQLException {
        String sql = "INSERT INTO files (filename, file_data, user_id) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, filename);
            statement.setBytes(2, fileData);
            statement.setInt(3, userId);

            statement.executeUpdate();
            System.out.println("File uploaded successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<String> getFileList(int userId) throws SQLException {
        List<String> fileList = new ArrayList<>();
        String sql = "SELECT filename FROM files WHERE user_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    fileList.add(resultSet.getString("filename"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return fileList;
    }

    public static byte[] downloadFile(String filename, int userId) throws SQLException {
        String sql = "SELECT file_data FROM files WHERE filename = ? AND user_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, filename);
            statement.setInt(2, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBytes("file_data");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return null;
    }

    public static void deleteFile(String filename, int userId) throws SQLException {
        String sql = "DELETE FROM files WHERE filename = ? AND user_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, filename);
            statement.setInt(2, userId);

            statement.executeUpdate();
            System.out.println("File deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}