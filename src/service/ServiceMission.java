package service;

import dao.DAOMission;
import model.Mission;

/**
 * ServiceMission
 */
public class ServiceMission {

    private DAOMission daoMission;

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