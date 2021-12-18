package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists users (id bigint auto_increment, name VARCHAR (30) not null, lastName VARCHAR (30) not null, age tinyint not null, constraint table_name_pk primary key (id))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table if exists users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("insert into users (name, lastName, age) values (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

            System.out.printf("User с именем – %s добавлен в базу данных\n",name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from users where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            ArrayList<User> users = new ArrayList<>();

            while (resultSet.next()){
                users.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("truncate table users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
