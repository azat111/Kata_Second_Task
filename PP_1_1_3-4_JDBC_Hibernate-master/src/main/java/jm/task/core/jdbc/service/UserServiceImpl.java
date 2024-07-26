package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Connection conn = Util.getMySQLConnection();

    public UserServiceImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE User\n" +
                "(\n" +
                "    id       INT NOT NULL AUTO_INCREMENT,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "    name     varchar(100) null,\n" +
                "    lastName varchar(100) null,\n" +
                "    age      int          null\n" +
                ");";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE user";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into User(name,lastName,age) values(?,?,?)";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM User WHERE id=?";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM User");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

