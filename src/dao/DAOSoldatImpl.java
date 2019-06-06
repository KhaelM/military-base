package dao;

import model.Soldat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DaoSoldat
 */
public class DAOSoldatImpl implements DAOSoldat {
    
    private static final String SQL_INSERT = "INSERT INTO SOLDAT (nom_utilisateur, mot_de_passe, categorie) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM SOLDAT WHERE nom_utilisateur = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM SOLDAT WHERE id_soldat = ?";


    private DAOFactory daofactory;

    public DAOSoldatImpl(DAOFactory daofactory) {
        this.daofactory = daofactory;
    }

    private static Soldat map(ResultSet resultSet) throws SQLException {
        Soldat soldat = new Soldat();
        soldat.setId(resultSet.getLong("id_soldat"));
        soldat.setNomUtilisateur(resultSet.getString("nom_utilisateur"));
        soldat.setMotDePasse(resultSet.getString("mot_de_passe"));
        soldat.setCategorie(resultSet.getInt("categorie"));

        return soldat;
    }

    @Override
    public Soldat read(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Soldat soldat = null;
        ResultSet resultSet = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_ID , false, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                soldat = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }
        return soldat;
    }

    @Override
    public void create(Soldat soldat) {
        Connection connection = null;
        ResultSet generatedKeys = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_INSERT, true, soldat.getNomUtilisateur(), soldat.getMotDePasse(), soldat.getCategorie());
            int rowsCreated = preparedStatement.executeUpdate();
            
            if(rowsCreated == 0) {
                throw new DAOException("Echec lors de la création d'un soldat. Aucune ligne ajoutée à la table.");
            }
            
            generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                soldat.setId(generatedKeys.getLong(1));
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
    public Soldat read(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Soldat soldat = null;
        ResultSet resultSet = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = DAOUtility.getInitialisedPreparedStatement(connection, SQL_SELECT_BY_USERNAME, false, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                soldat = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtility.closeQuietly(resultSet, preparedStatement, connection);
        }
        return soldat;
    }
}