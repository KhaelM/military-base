package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PosteSoldatSurMission;

/**
 * DAOPosteSoldatSurMission
 */
public class DAOPosteSoldatSurMissionImpl implements DAOPosteSoldatSurMission {

    private static final String SQL_SELECT_BY_ID_MISSION = "SELECT * FROM POSTE_SOLDAT_SUR_MISSION WHERE id_mission = ?";
    private static final String SQL_SELECT_BY_MISSION_AND_POSTE = "SELECT * FROM POSTE_SOLDAT_SUR_MISSION WHERE id_mission = ? AND id_poste = ?";
    private static final String SQL_SELECT_BY_MISSION_AND_SOLDAT = "SELECT * FROM POSTE_SOLDAT_SUR_MISSION WHERE id_mission = ? AND id_soldat = ?";
    private static final String SQL_INSERT = "INSERT INTO POSTE_SOLDAT_SUR_MISSION (id_mission, id_soldat, id_poste) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_POSTE = "UPDATE POSTE_SOLDAT_SUR_MISSION SET id_poste = ? WHERE id_poste_soldat_sur_mission = ?";

    private DAOFactory daoFactory;

    public DAOPosteSoldatSurMissionImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static PosteSoldatSurMission map(ResultSet resultSet) throws SQLException {
        PosteSoldatSurMission posteSoldatSurMission = new PosteSoldatSurMission();
        posteSoldatSurMission.setId(resultSet.getLong("id_poste_soldat_sur_mission"));
        posteSoldatSurMission.setIdMission(resultSet.getLong("id_mission"));
        posteSoldatSurMission.setIdSoldat(resultSet.getLong("id_soldat"));
        posteSoldatSurMission.setIdPoste(resultSet.getLong("id_poste"));

        return posteSoldatSurMission;
    }

    @Override
    public PosteSoldatSurMission readByMissionAndSoldat(Long idMission, Long idSoldat) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PosteSoldatSurMission posteSoldatSurMission= null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_MISSION_AND_SOLDAT, false, idMission, idSoldat);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                posteSoldatSurMission = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return posteSoldatSurMission;
    }

    @Override
    public void updatePoste(PosteSoldatSurMission posteSoldatSurMission) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_UPDATE_POSTE, false, posteSoldatSurMission.getIdPoste(), posteSoldatSurMission.getId());
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
    public PosteSoldatSurMission readByMissionAndPoste(Long idMission, Long idPoste) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PosteSoldatSurMission posteSoldatSurMission= null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_MISSION_AND_POSTE, false, idMission, idPoste);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                posteSoldatSurMission = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return posteSoldatSurMission;
    }

    @Override
    public void create(PosteSoldatSurMission posteSoldatSurMission) {
        Connection connection = null;
        ResultSet generatedKeys = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_INSERT, true, posteSoldatSurMission.getIdMission(), posteSoldatSurMission.getIdSoldat(), posteSoldatSurMission.getIdPoste());
            int rowsCreated = preparedStatement.executeUpdate();
            
            if(rowsCreated == 0) {
                throw new DAOException("Echec lors de la création d'un soldat. Aucune ligne ajoutée à la table.");
            }
            
            generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                posteSoldatSurMission.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("Échec lors de la création d'un soldat. Aucun id auto-généré retourné.");
            }


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(generatedKeys, preparedStatement, connection);
        }
    }

    @Override
    public PosteSoldatSurMission[] read(Long idMission) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<PosteSoldatSurMission> posteSoldatSurMissionList = new ArrayList<PosteSoldatSurMission>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_ID_MISSION, false, idMission);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                posteSoldatSurMissionList.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }

        return posteSoldatSurMissionList.toArray(new PosteSoldatSurMission[posteSoldatSurMissionList.size()]);
    }


}