package service;

import dao.DAOXpSoldatParPoste;
import model.XpSoldatParPoste;

/**
 * ServiceXpSoldatParPoste
 */
public class ServiceXpSoldatParPoste {

    private DAOXpSoldatParPoste daoXpSoldatParPoste;

    public ServiceXpSoldatParPoste(DAOXpSoldatParPoste daoXpSoldatParPoste) {
        this.daoXpSoldatParPoste = daoXpSoldatParPoste;
    }

    public XpSoldatParPoste[] trouverXpParPostes(Long idSoldat) {
        return daoXpSoldatParPoste.read(idSoldat);
    }

    public XpSoldatParPoste obtenirXp(Long idSoldat, Long idPoste) {
        return daoXpSoldatParPoste.read(idSoldat, idPoste);
    }
}