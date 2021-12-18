package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl hDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        hDao.createUsersTable();
    }

    public void dropUsersTable() {
        hDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        hDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        hDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return hDao.getAllUsers();
    }

    public void cleanUsersTable() {
        hDao.cleanUsersTable();
    }
}
