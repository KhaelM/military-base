package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.XpSoldatParPoste;

/**
 * DAOXpSoldatParPoste
 */
public class DAOXpSoldatParPosteImpl implements DAOXpSoldatParPoste {

    private final static String SQL_SELECT_BY_SOLDAT_ID = "SELECT * FROM XP_SOLDAT_PAR_POSTE WHERE id_soldat = ?";
    private final static String SQL_SELECT_BY_SOLDAT_AND_POSTE = "SELECT * FROM XP_SOLDAT_PAR_POSTE WHERE id_soldat = ? AND id_poste = ?";
    private static final String SQL_INSERT = "INSERT INTO XP_SOLDAT_PAR_POSTE (id_poste, id_soldat, xp) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_XP = "UPDATE XP_SOLDAT_PAR_POSTE SET xp = ? WHERE id_xp_soldat_par_poste = ?";


    private DAOFactory daofactory;

    public DAOXpSoldatParPosteImpl(DAOFactory daoFactory) {
        this.daofactory = daoFactory;
    }

    @Override
    public void updateXp(XpSoldatParPoste xpSoldatParPoste) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_UPDATE_XP, false, xpSoldatParPoste.getXp(), xpSoldatParPoste.getId());
            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated == 0) {
                throw new DAOException("Aucune donnée mise à jour.");
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(preparedStatement, connection);
        }
    }

    @Override
    public void create(XpSoldatParPoste xpSoldatParPoste) {
        Connection connection = null;
        ResultSet generatedKeys = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_INSERT, true, xpSoldatParPoste.getIdPoste(), xpSoldatParPoste.getIdSoldat(), xpSoldatParPoste.getXp());
            int rowsCreated = preparedStatement.executeUpdate();
            
            if(rowsCreated == 0) {
                throw new DAOException("Echec lors de la création d'un soldat. Aucune ligne ajoutée à la table.");
            }
            
            generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                xpSoldatParPoste.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("Échec lors de la création d'un soldat. Aucun id auto-généré retourné.");
            }


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(generatedKeys, preparedStatement, connection);
        }
    }

    private XpSoldatParPoste map(ResultSet resultSet) throws SQLException {
        XpSoldatParPoste xpSoldatParPoste = new XpSoldatParPoste();
        xpSoldatParPoste.setId(resultSet.getLong("id_xp_soldat_par_poste"));
        xpSoldatParPoste.setIdPoste(resultSet.getLong("id_poste"));
        xpSoldatParPoste.setIdSoldat(resultSet.getLong("id_soldat"));
        xpSoldatParPoste.setXp(resultSet.getInt("xp"));

        return xpSoldatParPoste;
    }

    @Override
    public XpSoldatParPoste read(Long idSoldat, Long idPoste) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        XpSoldatParPoste xpSoldatParPoste = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_SOLDAT_AND_POSTE, false, idSoldat, idPoste);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                xpSoldatParPoste = map(resultSet);            
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return xpSoldatParPoste;
    }

    @Override
    public XpSoldatParPoste[] read(Long idSoldat) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<XpSoldatParPoste> xpSoldatParPostes = new ArrayList<XpSoldatParPoste>();

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_SOLDAT_ID, false, idSoldat);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                xpSoldatParPostes.add(map(resultSet));            
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return xpSoldatParPostes.toArray(new XpSoldatParPoste[xpSoldatParPostes.size()]);
    }

}