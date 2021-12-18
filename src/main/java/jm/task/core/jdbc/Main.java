package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();


       usi.saveUser("John","Seena", (byte) 40);
        usi.saveUser("Johny","Depp", (byte) 45);
        usi.saveUser("John","Dorian", (byte) 99);
        usi.saveUser("John","Hancock", (byte) 29);

        List<User> users = usi.getAllUsers();
        users.forEach(System.out::println);

        usi.cleanUsersTable();
        usi.dropUsersTable();


    }
}

