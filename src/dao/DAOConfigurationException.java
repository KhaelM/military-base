package dao;

/**
 * DaoConfigException
 */
public class DAOConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DAOConfigurationException(String message) {
        super(message);
    }

    public DAOConfigurationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}