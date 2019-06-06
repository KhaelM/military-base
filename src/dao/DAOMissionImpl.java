package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mission;

/**
 * DAOMission
 */
public class DAOMissionImpl implements DAOMission {

    private static final String SQL_SELECT_ALL = "SELECT * FROM MISSION";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM MISSION WHERE id_mission = ?";

    private DAOFactory daoFactory;

    public DAOMissionImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static Mission map(ResultSet resultSet) throws SQLException {
        Mission mission = new Mission();
        mission.setId(resultSet.getLong("id_mission"));
        mission.setObjectif(resultSet.getString("objectif"));
        mission.setEtat(resultSet.getInt("etat"));

        return mission;
    }

    @Override
    public Mission[] read() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Mission> missions = new ArrayList<Mission>();
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                missions.add(map(resultSet));    
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return missions.toArray(new Mission[missions.size()]);
    }

    @Override
    public Mission read(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Mission mission = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_ID, false, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                mission = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return mission;
    }
}