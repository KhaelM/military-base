package service;


import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import dao.DAOFactory;
import dao.DAOMission;
import dao.DAOSoldat;
import dao.DAOXpMissionParPoste;
import model.Mission;
import model.Poste;
import model.Soldat;
import model.XpMissionParPoste;

/**
 * ServiceMission
 */
public class ServiceMission {

    private DAOMission daoMission;
    private DAOSoldat daoSoldat;
    private DAOXpMissionParPoste daoXpMissionParPoste;

    public ServiceMission(DAOMission daoMission) {
        this.daoMission = daoMission;
    }

    public ServiceMission(DAOMission daoMission, DAOSoldat daoSoldat, DAOXpMissionParPoste daoXpMissionParPoste) {
        this.daoMission = daoMission;
        this.daoSoldat = daoSoldat;
        this.daoXpMissionParPoste = daoXpMissionParPoste;
    }

    public Mission[] trouverToutesLesMission() {
        return daoMission.read();
    }

    public Mission trouverMission(Long id) {
        return daoMission.read(id);
    }

    public void creerMission(Long idUtilisateur, String objectifMission, Poste[] postes, HttpServletRequest request, DAOFactory daoFactory) throws Exception {
        if(idUtilisateur == null) {
            throw new Exception("Veuillez vous connecter pour accéder à cette page.");
        }

        if(objectifMission == "" || objectifMission == null) {
            throw new Exception("L'objectif ne peut pas être vide.");
        }

        Soldat soldat = daoSoldat.read(idUtilisateur);
        if (soldat.getCategorie().intValue() != 1) {
            throw new Exception("Vous n'avez pas le droit d'accéder à cette page.");
        }

    
        Mission mission = new Mission(objectifMission);
        Connection connection = daoFactory.getConnection();
        connection.setAutoCommit(false);

        try {
            daoMission.create(mission, connection);
    
            Long idPoste = null;
            Integer xpMin = null;
            Integer xpGain = null;
    
            for (Poste poste : postes) {
                idPoste = poste.getId();
                xpGain = Integer.valueOf(request.getParameter("xp_gain_"+idPoste.toString()));
                xpMin = Integer.valueOf(request.getParameter("xp_requis_"+idPoste.toString()));
                XpMissionParPoste xpMissionParPoste = new XpMissionParPoste(mission.getId(), idPoste, xpMin, xpGain);
                daoXpMissionParPoste.create(xpMissionParPoste, connection);            
            }
        } catch(Exception e) {
            connection.rollback();
            throw e;
        }
    }

}