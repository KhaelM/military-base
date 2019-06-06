package service;

import dao.DAOPosteSoldatSurMission;
import model.PosteSoldatSurMission;

/**
 * ServicePosteSoldatSurMission
 */
public class ServicePosteSoldatSurMission {

    private DAOPosteSoldatSurMission daoPosteSoldatSurMission;

    public ServicePosteSoldatSurMission(DAOPosteSoldatSurMission daoPosteSoldatSurMission) {
        this.daoPosteSoldatSurMission = daoPosteSoldatSurMission;
    }

    public PosteSoldatSurMission[] trouverSoldatsSurMission(Long idMission) {
        return daoPosteSoldatSurMission.read(idMission);
    }
}