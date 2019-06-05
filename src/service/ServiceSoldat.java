package service;

import dao.DAOMission;
import dao.DAOPoste;
import dao.DAOPosteSoldatSurMission;
import dao.DAOSoldat;
import dao.DAOXpMissionParPoste;
import dao.DAOXpSoldatParPoste;
import model.Mission;
import model.Poste;
import model.PosteSoldatSurMission;
import model.Soldat;
import model.XpMissionParPoste;
import model.XpSoldatParPoste;

/**
 * ServiceSoldat
 */
public class ServiceSoldat {

    private DAOSoldat daoSoldat;
    private DAOMission daoMission;
    private DAOPoste daoPoste;
    private DAOPosteSoldatSurMission daoPosteSoldatSurMission;
    private DAOXpMissionParPoste daoXpMissionParPoste;
    private DAOXpSoldatParPoste daoXpSoldatParPoste;

    public ServiceSoldat(DAOSoldat daoSoldat, DAOMission daoMission, DAOPoste daoPoste, DAOPosteSoldatSurMission daoPosteSoldatSurMission, DAOXpMissionParPoste daoXpMissionParPoste, DAOXpSoldatParPoste daoXpSoldatParPoste) {
        this.daoSoldat = daoSoldat;
        this.daoMission = daoMission;
        this.daoPoste = daoPoste;
        this.daoPosteSoldatSurMission = daoPosteSoldatSurMission;
        this.daoXpMissionParPoste = daoXpMissionParPoste;
        this.daoXpSoldatParPoste = daoXpSoldatParPoste;
    }

    public ServiceSoldat(DAOSoldat daoSoldat) {
        this.daoSoldat = daoSoldat;
    }

    public void inscrireSoldat(Soldat soldat) throws Exception {
        daoSoldat.create(soldat);
    }

    public Soldat trouverSoldat(String nomUtilisateur) {
        return daoSoldat.read(nomUtilisateur);
    }
    
    public Soldat trouverSoldat(Long idSoldat) {
        return daoSoldat.read(idSoldat);
    }

    public String postuler(Long idSoldat, Long idMission, Long idPoste) throws Exception {
        Soldat soldat = trouverSoldat(idSoldat);
        if(soldat == null) {
            throw new Exception("Veuillez vous connecter pour pouvoir postuler.");
        }

        Mission mission = daoMission.read(idMission);
        if(mission == null) {
            throw new Exception("La référence à la mission demandée n'existe pas.");
        }
        if(mission.getEtat() != 0) {
            throw new Exception("La mission demandée n'est plus disponible.");
        }


        Poste poste = daoPoste.read(idPoste);
        if(poste == null) {
            throw new Exception("La référence au poste demandé n'existe pas.");
        }

        XpMissionParPoste xpRequis = daoXpMissionParPoste.readByMissionAndPoste(idMission, idPoste);
        if(xpRequis == null) {
            throw new Exception("Le poste " + poste.getNom() + " n'existe pas sur cette mission.");
        }

        XpSoldatParPoste xpSoldatSurPoste = daoXpSoldatParPoste.read(idSoldat, idPoste);
        long xpSoldat = (xpSoldatSurPoste == null) ? 0 : xpSoldatSurPoste.getXp().longValue();  
        if(xpSoldat < xpRequis.getXpMin().longValue()) {
            throw new Exception("Expérience non suffisante pour le poste " + poste.getNom() +" dans la mission " + mission.getObjectif() + ". Xp requis: " + xpRequis.getXpMin() + ", votre xp: " + xpSoldat+ ".");
        }

        PosteSoldatSurMission posteSoldatSurMission = daoPosteSoldatSurMission.readByMissionAndPoste(idMission, idPoste);
        if(posteSoldatSurMission != null) {
            if(!posteSoldatSurMission.getIdSoldat().equals(idSoldat)) {
                throw new Exception("Le poste " + poste.getNom() + " est déjà occupé dans la mission " + mission.getObjectif()+".");
            } 
            posteSoldatSurMission = daoPosteSoldatSurMission.readByMissionAndSoldat(idMission, idSoldat);
            if(posteSoldatSurMission.getIdPoste().equals(idPoste)) {
                throw new Exception("Vous occupez déjà le poste " + poste.getNom() + " dans la mission " + mission.getObjectif() + ".");
            }
            Long ancienIdPoste = posteSoldatSurMission.getIdPoste();
            posteSoldatSurMission.setIdPoste(idPoste);
            daoPosteSoldatSurMission.updatePoste(posteSoldatSurMission);
            return "Vous êtes passer de " + daoPoste.read(ancienIdPoste).getNom() + " à " + poste.getNom();
        } else {
            posteSoldatSurMission = new PosteSoldatSurMission();
            posteSoldatSurMission.setIdMission(idMission);
            posteSoldatSurMission.setIdPoste(idPoste);
            posteSoldatSurMission.setIdSoldat(idSoldat);
            daoPosteSoldatSurMission.create(posteSoldatSurMission);
        }
        return "Vous occupez désormais le poste de " + poste.getNom() + " sur la mission " + mission.getObjectif() +".";
    }
}