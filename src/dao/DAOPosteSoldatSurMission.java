package dao;

import model.PosteSoldatSurMission;

/**
 * DAOPosteSoldatSurMission
 */
public interface DAOPosteSoldatSurMission {

    PosteSoldatSurMission[] read(Long idMission);
    PosteSoldatSurMission readByMissionAndPoste(Long idMission, Long idPoste);
    PosteSoldatSurMission readByMissionAndSoldat(Long idMission, Long idSoldat);
    void create(PosteSoldatSurMission posteSoldatSurMission);
    void updatePoste(PosteSoldatSurMission posteSoldatSurMission);
}