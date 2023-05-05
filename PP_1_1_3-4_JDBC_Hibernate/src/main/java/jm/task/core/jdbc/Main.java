package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь


        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 21);
        userDao.saveUser("Name2", "LastName2", (byte) 22);
        userDao.saveUser("Name3", "LastName3", (byte) 23);
        userDao.saveUser("Name4", "LastName4", (byte) 24);

        userDao.removeUserById(5);
        List<User> allUsers = userDao.getAllUsers();
        System.out.println(allUsers);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
