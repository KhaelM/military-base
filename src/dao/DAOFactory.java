package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
    private static final String FILE_RELATIVE_PATH       = "/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME        = "username";
    private static final String PROPERTY_PASSWORD        = "password";

    private String url;
    private String username;
    private String password;

    public DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        String url;
        String username;
        String password;
        String driver;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream file = classLoader.getResourceAsStream(FILE_RELATIVE_PATH);

        if(file == null) {
            throw new DAOConfigurationException("Le fichier properties " + FILE_RELATIVE_PATH + " n'a pas été trouvé.");
        }

        try {
            properties.load(file);
            url = properties.getProperty(PROPERTY_URL);
            username = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);
            driver = properties.getProperty(PROPERTY_DRIVER);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible de charger le fichier properties " + FILE_RELATIVE_PATH, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver " + driver + " est introuvable dans le classpath.");
        }

        return new DAOFactory(url, username, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public DAOSoldat getDAOSoldat() {
        return new DAOSoldatImpl(this);
    }

    public DAOMission getDaoMission() {
        return new DAOMissionImpl(this);
    }

    public DAOPoste getDaoPoste() {
        return new DAOPosteImpl(this);
    }

    public DAOXpSoldatParPoste getDaoXpSoldatParPoste() {
        return new DAOXpSoldatParPosteImpl(this);
    }

    public DAOXpMissionParPoste getDaoXpMissionParPoste() {
        return new DAOXpMissionParPosteImpl(this);
    }

    public DAOPosteSoldatSurMission getDaoPosteSoldatSurMission() {
        return new DAOPosteSoldatSurMissionImpl(this);
    }
}