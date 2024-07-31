package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userDao = new UserServiceImpl();
        userDao.createUsersTable();
        userDao.saveUser("Azat1123","aa",(byte)2);
        userDao.saveUser("Azat123","aa",(byte)3);
        userDao.saveUser("Azat123","aa",(byte)4);
        userDao.saveUser("Aza123t","aa",(byte)5);
        userDao.getAllUsers().stream().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
