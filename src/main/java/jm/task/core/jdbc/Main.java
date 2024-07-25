package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.dropUsersTable();
        userService.dropUsersTable();
        //userDao.createUsersTable();
        //userDao.saveUser("Ivan","EE", (byte) 22);
        //userDao.removeUserById(1);
        //userDao.cleanUsersTable();

        //System.out.println(userDao.getAllUsers());
    }
}

