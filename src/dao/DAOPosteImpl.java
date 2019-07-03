package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Poste;

/**
 * DAOPoste
 */
public class DAOPosteImpl implements DAOPoste {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM POSTE WHERE id_poste = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM POSTE";

    private DAOFactory daoFactory;

    public DAOPosteImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private Poste map(ResultSet resultSet) throws SQLException {
        Poste poste = new Poste();
        poste.setId(resultSet.getLong("id_poste"));
        poste.setNom(resultSet.getString("nom"));

        return poste;
    }

    @Override
    public Poste[] readAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Poste> postes = new ArrayList<Poste>();
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                postes.add(map(resultSet));    
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return postes.toArray(new Poste[postes.size()]);
    }

    @Override
    public Poste read(Long idPoste) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Poste poste = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_ID, false, idPoste);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                poste = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return poste;
    }
}