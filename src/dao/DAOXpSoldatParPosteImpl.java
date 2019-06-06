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

    private DAOFactory daofactory;

    public DAOXpSoldatParPosteImpl(DAOFactory daoFactory) {
        this.daofactory = daoFactory;
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