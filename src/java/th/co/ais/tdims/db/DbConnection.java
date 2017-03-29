/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DbConnection {

    final static Logger logger = Logger.getLogger(DbConnection.class);

    public Connection open() {
        Properties prop = this.getProperties();
        try {
            Class.forName(prop.getProperty("mysql.db.driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }
        
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(prop.getProperty("mysql.db.host"), prop.getProperty("mysql.db.username"), prop.getProperty("mysql.db.password"));
            //System.out.println("MySQL Connection Success!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return connection;
    }

    private Properties getProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("database.properties");
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static void main(String[] args) {
        new DbConnection().open();
    }
}
