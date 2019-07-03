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

    private static final String SQL_INSERT = "INSERT INTO MISSION (objectif, etat) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM MISSION";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM MISSION WHERE id_mission = ?";
    private static final String SQL_UPDATE_ETAT = "UPDATE MISSION SET etat = ? WHERE id_mission = ?";

    private DAOFactory daoFactory;

    public DAOMissionImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Mission mission, Connection connection) {
        ResultSet generatedKeys = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_INSERT, true, mission.getObjectif(), mission.getEtat());
            int rowsCreated = preparedStatement.executeUpdate();
            
            if(rowsCreated == 0) {
                throw new DAOException("Echec lors de la création de la mission. Aucune ligne ajoutée à la table.");
            }
            
            generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                mission.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("Échec lors de la création de la mission. Aucun id auto-généré retourné.");
            }


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(generatedKeys);
            DAOUtility.closeQuietly(preparedStatement);
        }
    }

    @Override
    public void create(Mission mission) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            create(mission, connection);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(connection);
        }
    }

    @Override
    public void updateEtat(Mission mission) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_UPDATE_ETAT, false, mission.getEtat(), mission.getId());
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