package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * DAOUtility
 */
public class DAOUtility {

    public static void closeQuietly( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    public static void closeQuietly( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    public static void closeQuietly( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture de la connection : " + e.getMessage() );
            }
        }
    }

    public static void closeQuietly( Statement statement, Connection connection ) {
        closeQuietly( statement );
        closeQuietly( connection );
    }

    public static void closeQuietly( ResultSet resultSet, Statement statement, Connection connection ) {
        closeQuietly( resultSet );
        closeQuietly( statement );
        closeQuietly( connection );
    }

    public static PreparedStatement getInitialisedPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i+1, objects[i]);
        }

        return preparedStatement;
    }
}