package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS  users (id INT NOT NULL AUTO_INCREMENT
                , name VARCHAR(30)
                , lastName VARCHAR(30)
                , age INT
                , PRIMARY KEY (id)
                )""";


        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement();) {
            stmt.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users
                """;
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement();) {
            stmt.execute(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = (?) ";

        try (Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = """
                SELECT * FROM users
                """;
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement();
            ResultSet resSet = stmt.executeQuery(sql);) {

           while (resSet.next()) {
               String name = resSet.getString("name");
               String lastName = resSet.getString("lastName");
               byte age = resSet.getByte("age");

               usersList.add(new User(name, lastName, age));
           }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement();) {
            stmt.execute(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
