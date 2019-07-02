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

    public Soldat seConnecter(String nomUtilisateur, String motDePasse) throws Exception {
        Soldat utilisateur = trouverSoldat(nomUtilisateur);

        if(utilisateur == null) {
            throw new Exception("Nom de soldat inexistant.");
        }

        if(!utilisateur.getMotDePasse().equals(motDePasse)) {
            throw new Exception("Mot de passe incorrect.");
        }

        return utilisateur;
    }

    public String mettreAJourMission(Long idSoldat, Long idMission, Integer etat) throws Exception {
        String message = new String();
        Soldat soldat = trouverSoldat(idSoldat);
        if(soldat == null) {
            throw new Exception("Veuillez vous connecter.");
        }

        if(soldat.getCategorie().intValue() != 1) {
            throw new Exception("Vous n'êtes pas autorisé à accéder à cette page.");
        }

        Mission mission = daoMission.read(idMission);
        if(mission == null) {
            throw new Exception("La référence à cette mission n'existe pas.");
        }

        
        String descriptionEtatMission = Mission.descriptionEtat(etat);
        if(mission.getEtat().equals(etat)) {
            throw new Exception("L'état actuel de la mission " + mission.getObjectif() + " est déjà: " + descriptionEtatMission + ".");
        }
        
        if(etat.intValue() == Mission.ETAT_DISPO) {
            throw new Exception("Impossible de passer à l'état " + Mission.descriptionEtat(Integer.valueOf(0)) + ".");
        }

        if(mission.getEtat().intValue() == Mission.ETAT_DISPO && etat.intValue() != Mission.ETAT_EN_COURS) {
            throw new Exception("Une mission disponible ne peut être que démarré.");
        }

        if(mission.getEtat().intValue() == Mission.ETAT_EN_COURS && etat.intValue() != Mission.ETAT_SUCCES && etat.intValue() != Mission.ETAT_ECHEC) {
            throw new Exception("Une mission en cours peut juste être soit réussi soit échoué.");
        }

        if((mission.getEtat().intValue() == Mission.ETAT_SUCCES || mission.getEtat().intValue() == Mission.ETAT_ECHEC) && (etat.intValue() != Mission.ETAT_ECHEC && etat.intValue() != Mission.ETAT_SUCCES)) {
            if(mission.getEtat().intValue() == Mission.ETAT_SUCCES) {
                throw new Exception("Une mission réussie ne peut passer qu'à l'état échoué.");
            } else {
                throw new Exception("Une mission échouée ne peut passer qu'à l'état réussie.");
            }
        }

        XpMissionParPoste[] postesSurMission = daoXpMissionParPoste.read(idMission);
        PosteSoldatSurMission[] soldatsSurMission = new PosteSoldatSurMission[postesSurMission.length];
        for (int i = 0; i < postesSurMission.length; i++) {
            soldatsSurMission[i] = daoPosteSoldatSurMission.readByMissionAndPoste(idMission, postesSurMission[i].getIdPoste());
            if(soldatsSurMission[i] == null) {
                throw new Exception("Chaque poste doit être rempli avant de démarrer la mission.");
            }
        }
        
        mission.setEtat(etat);
        daoMission.updateEtat(mission);

        if(etat.intValue() == Mission.ETAT_ECHEC || etat.intValue() == Mission.ETAT_SUCCES) {
            XpSoldatParPoste xpSoldatParPoste = null;
            int sign = etat.intValue() == Mission.ETAT_ECHEC ? -1 : 1;
            for (int i = 0; i < soldatsSurMission.length; i++) {
                xpSoldatParPoste = daoXpSoldatParPoste.read(soldatsSurMission[i].getIdSoldat(), soldatsSurMission[i].getIdPoste());
                if(xpSoldatParPoste != null) {
                    xpSoldatParPoste.setXp(Integer.valueOf(xpSoldatParPoste.getXp().intValue() + sign * postesSurMission[i].getXpGain().intValue()));
                    daoXpSoldatParPoste.updateXp(xpSoldatParPoste);
                } else {
                    xpSoldatParPoste = new XpSoldatParPoste();
                    xpSoldatParPoste.setIdPoste(soldatsSurMission[i].getIdPoste());
                    xpSoldatParPoste.setIdSoldat(soldatsSurMission[i].getIdSoldat());
                    xpSoldatParPoste.setXp(sign * postesSurMission[i].getXpGain());
                    daoXpSoldatParPoste.create(xpSoldatParPoste);
                }
            }
        }

        message = "la mission `" + mission.getObjectif() + "` a été ";
        switch (etat.intValue()) {
            case Mission.ETAT_EN_COURS:
                message += "démarré.";         
                break;
            case Mission.ETAT_SUCCES:
                message += "un succès.";
                break;
            case Mission.ETAT_ECHEC:
                message += "un échec.";
                break;
        }

        return message;
    }

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

    public Soldat inscrireSoldat(String nomUtilisateur, String motDePasse) throws Exception {
        if(nomUtilisateur == null || nomUtilisateur.length() == 0) {
            throw new Exception("Nom d'utilisateur non renseigné.");
        }

        if(motDePasse == null || motDePasse.length() == 0) {
            throw new Exception("Mot de passe non renseigné.");
        }
        
        Soldat soldat = trouverSoldat(nomUtilisateur);
        if(soldat != null) {
            throw new Exception("Nom d'utilisateur déjà utilisé.");
        }

        soldat = new Soldat(nomUtilisateur, motDePasse);
        
        daoSoldat.create(soldat);

        return soldat;
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
        if(posteSoldatSurMission != null && !posteSoldatSurMission.getIdSoldat().equals(idSoldat)) {
            throw new Exception("Le poste " + poste.getNom() + " est déjà occupé dans la mission " + mission.getObjectif()+".");
        }
        
        PosteSoldatSurMission posteActuelSoldat = daoPosteSoldatSurMission.readByMissionAndSoldat(idMission, idSoldat); 
        if(posteActuelSoldat != null) { 
            if(posteActuelSoldat.getIdPoste().equals(idPoste)) {
                throw new Exception("Vous occupez déjà le poste " + poste.getNom() + " dans la mission " + mission.getObjectif() + ".");
            }
            Long ancienIdPoste = posteActuelSoldat.getIdPoste();
            posteActuelSoldat.setIdPoste(idPoste);
            daoPosteSoldatSurMission.updatePoste(posteActuelSoldat);
            return "Vous êtes passer de " + daoPoste.read(ancienIdPoste).getNom() + " à " + poste.getNom();
        } else {
            posteActuelSoldat = new PosteSoldatSurMission();
            posteActuelSoldat.setIdMission(idMission);
            posteActuelSoldat.setIdPoste(idPoste);
            posteActuelSoldat.setIdSoldat(idSoldat);
            daoPosteSoldatSurMission.create(posteActuelSoldat);
        }
        return "Vous occupez désormais le poste de " + poste.getNom() + " sur la mission " + mission.getObjectif() +".";
    }
}