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
}