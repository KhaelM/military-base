package dao;

import java.sql.Connection;

import model.XpMissionParPoste;

/**
 * DAOXpMissionParPoste
 */
public interface DAOXpMissionParPoste {

    XpMissionParPoste[] read(Long idMission);
    XpMissionParPoste readByMissionAndPoste(Long idMission, Long idPoste);
    void create(XpMissionParPoste xpMissionParPoste, Connection connection);
    void create(XpMissionParPoste xpMissionParPoste);
}