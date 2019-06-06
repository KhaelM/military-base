package service;

import dao.DAOXpMissionParPoste;
import model.XpMissionParPoste;

/**
 * ServiceXpMissionParPoste
 */
public class ServiceXpMissionParPoste {

    private DAOXpMissionParPoste daoXpMissionParPoste;

    public ServiceXpMissionParPoste(DAOXpMissionParPoste daoXpMissionParPoste) {
        this.daoXpMissionParPoste = daoXpMissionParPoste;
    }

    public XpMissionParPoste[] trouverXpMissionParPoste(Long idMission) {
        return daoXpMissionParPoste.read(idMission);
    }
}