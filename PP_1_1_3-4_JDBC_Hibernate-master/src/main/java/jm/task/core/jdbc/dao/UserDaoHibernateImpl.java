package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sf = Util.getSessionFactory();
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS User\n" +
                    "(\n" +
                    "    id       INT NOT NULL AUTO_INCREMENT,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "    name     varchar(100) null,\n" +
                    "    lastName varchar(100) null,\n" +
                    "    age      int          null\n" +
                    ");";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS user";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();

            userList = session.createQuery("FROM User").getResultList();

            transaction.commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        List<User> userList = new ArrayList<User>();
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();

            userList = session.createQuery("FROM User").getResultList();

            for (User user : userList) {
                session.delete(user);
            }

            transaction.commit();
        }
    }
}
