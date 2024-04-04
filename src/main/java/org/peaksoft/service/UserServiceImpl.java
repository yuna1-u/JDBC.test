package org.peaksoft.service;

import org.peaksoft.model.User;
import org.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Service<User> {

    public void createTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS users(
                id       SERIAL PRIMARY KEY NOT NULL,
                name     VARCHAR NOT NULL,
                last_name VARCHAR,
                age      INTEGER,
                card_id TEXT UNIQUE );
                """;
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        String query = "DROP TABLE IF EXISTS users;";
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(User user) {
        String query = """
                INSERT INTO users(name,last_name,age,card_id)
                VALUES(?,?,?,?);
                """;
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setLong(4, user.getCardId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeById(long id) {
        String query = "DELETE FROM users WHERE id = " + id;
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                user.setCardId(resultSet.getLong("card_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanTable() {
        String query = "TRUNCATE TABLE users;";

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getById(long id) {

        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                user.setCardId(resultSet.getLong("card_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
