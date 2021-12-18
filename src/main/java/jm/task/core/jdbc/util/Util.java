package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/learning";
    private static String user = "root";
    private static String password = "root";
    private static String dialect = "org.hibernate.dialect.MySQLDialect";

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, driver);
                properties.put(Environment.URL, url);
                properties.put(Environment.USER, user);
                properties.put(Environment.PASS, password);
                properties.put(Environment.DIALECT,dialect);

                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);


            } catch (Exception e){
                System.out.println("SessionFactory failed");
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    public static Connection getConnection () throws ClassNotFoundException, SQLException {
        Class.forName(driver);

        return DriverManager.getConnection (url,
                user, password);
    }


}
