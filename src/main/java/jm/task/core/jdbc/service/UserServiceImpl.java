package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    Connection conn = Util.getMySQLConnection();

    public UserServiceImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() {
        PreparedStatement ps = null;
        String sql="create table User\n" +
                "(\n" +
                "    id       INT NOT NULL AUTO_INCREMENT,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "    name     varchar(100) null,\n" +
                "    lastName varchar(100) null,\n" +
                "    age      int          null\n" +
                ");";
        try {
            ps = conn.prepareStatement(sql);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        PreparedStatement ps = null;
        String sql="drop table user";
        try{
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement=null;
        String sql="insert into User(name,lastName,age) values(?,?,?)";
        try{
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement=null;
        String sql="delete from User where id=?";
        try{
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql="select * from User";
        List<User> userList = new ArrayList<>();
        try{
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("select * from User");

            while(rs.next()){
                User user=new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement=null;
        String sql="truncate table user";
        try{
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
