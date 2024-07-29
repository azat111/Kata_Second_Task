package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Azat","aa",(byte)2);
        userDao.saveUser("Azat","aa",(byte)3);
        userDao.saveUser("Azat","aa",(byte)4);
        userDao.saveUser("Azat","aa",(byte)5);
        userDao.getAllUsers().stream().forEach(System.out::println);
        userDao.createUsersTable();
        userDao.dropUsersTable();
    }
}
