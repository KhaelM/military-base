package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.XpMissionParPoste;

/**
 * DAOXpMissionParPoste
 */
public class DAOXpMissionParPosteImpl implements DAOXpMissionParPoste {

    private final static String SQL_SELECT_BY_MISSION_ID = "SELECT * FROM XP_MISSION_PAR_POSTE WHERE id_mission = ?";
    private final static String SQL_SELECT_BY_MISSION_AND_POSTE = "SELECT * FROM XP_MISSION_PAR_POSTE WHERE id_mission = ? AND id_poste = ?";

    private DAOFactory daoFactory;

    public DAOXpMissionParPosteImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static XpMissionParPoste map(ResultSet resultSet) throws SQLException {
        XpMissionParPoste xpMissionParPoste = new XpMissionParPoste();
        xpMissionParPoste.setId(resultSet.getLong("id_xp_mission_par_poste"));
        xpMissionParPoste.setIdMission(resultSet.getLong("id_mission"));
        xpMissionParPoste.setIdPoste(resultSet.getLong("id_poste"));
        xpMissionParPoste.setXpMin(resultSet.getInt("xp_min"));
        xpMissionParPoste.setXpGain(resultSet.getInt("xp_gain"));

        return xpMissionParPoste;
    }

    @Override
    public XpMissionParPoste readByMissionAndPoste(Long idMission, Long idPoste) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        XpMissionParPoste xpMissionParPoste = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_MISSION_AND_POSTE, false, idMission, idPoste);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                xpMissionParPoste = map(resultSet);
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return xpMissionParPoste;
    }

    @Override
    public XpMissionParPoste[] read(Long idMission) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<XpMissionParPoste> xpMissionParPostes = new ArrayList<XpMissionParPoste>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_MISSION_ID, false, idMission);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                xpMissionParPostes.add(map(resultSet));
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return xpMissionParPostes.toArray(new XpMissionParPoste[xpMissionParPostes.size()]);
    }
    
}