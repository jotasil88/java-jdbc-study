package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Properties properties = getProperties();
				String dburl = properties.getProperty("dburl");
				connection = DriverManager.getConnection(dburl, properties);
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}

	private static Properties getProperties() {
		try (FileInputStream fis = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			return properties;
		} catch (IOException e) {
			throw new DBException(e.getMessage());
		}
	}
}
