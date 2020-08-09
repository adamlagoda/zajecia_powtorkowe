package jdbc.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private final Properties properties;

    public ConnectionFactory(String filename) {
        this.properties = getDataBaseProperties(filename);
    }

    private Properties getDataBaseProperties(String filename) {
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream propertiesStream = classLoader.getResourceAsStream(filename);
            if (propertiesStream == null) {
                throw new IllegalArgumentException("Can't find file: " + filename);
            }
            properties.load(propertiesStream);
        } catch (IOException e) {
            logger.error("Error during fetching properties for database", e);
            return null;
        }

        return properties;
    }

    public Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = null;
        try {
            dataSource = new MysqlDataSource();
            dataSource.setServerName(properties.getProperty("jdbc.server"));
            dataSource.setDatabaseName(properties.getProperty("jdbc.name"));
            dataSource.setUser(properties.getProperty("jdbc.user"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setPort(Integer.parseInt(properties.getProperty("jdbc.port")));
            //dataSource.setAllowMultiQueries(true);
            dataSource.setServerTimezone("Europe/Warsaw");
            //dataSource.setUseSSL(false);
            dataSource.setCharacterEncoding("UTF-8");
        } catch (SQLException e) {
            logger.error("Error during creating MysqlDataSource", e);
        }
        logger.info("Connecting to a selected database...");
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory("database.properties");
        connectionFactory.getConnection();
    }
}