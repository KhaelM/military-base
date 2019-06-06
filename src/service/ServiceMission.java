package service;

import dao.DAOMission;
import model.Mission;

/**
 * ServiceMission
 */
public class ServiceMission {

    private DAOMission daoMission;

    public static String descriptionEtat(Integer etat) throws Exception {
        switch (etat.intValue()) {
            case Mission.ETAT_DISPO:
                return "disponible";
            case Mission.ETAT_EN_COURS:
                return "en cours";        
            case Mission.ETAT_SUCCES:
                return "succès";
            case Mission.ETAT_ECHEC:
                return "échec";
            default:
                throw new Exception("Etat inexistant.");
        }
    }

    public ServiceMission(DAOMission daoMission) {
        this.daoMission = daoMission;
    }

    public Mission[] trouverToutesLesMission() {
        return daoMission.read();
    }

    public Mission trouverMission(Long id) {
        return daoMission.read(id);
    }

}