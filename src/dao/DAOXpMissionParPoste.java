package dao;

import model.XpMissionParPoste;

/**
 * DAOXpMissionParPoste
 */
public interface DAOXpMissionParPoste {

    XpMissionParPoste[] read(Long idMission);
    XpMissionParPoste readByMissionAndPoste(Long idMission, Long idPoste);
}