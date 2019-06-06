package dao;

/**
 * DAOException
 */
public class DAOException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable throwable) {
        super(throwable);
    }
}