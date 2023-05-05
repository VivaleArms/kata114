package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl daoJDBCImpl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        daoJDBCImpl.createUsersTable();
    }

    public void dropUsersTable() {
        daoJDBCImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        daoJDBCImpl.saveUser(name, lastName, age);
    }
    public void removeUserById(long id) {
        daoJDBCImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return daoJDBCImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        daoJDBCImpl.cleanUsersTable();
    }
}
