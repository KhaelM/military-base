package service;

import dao.DAOPoste;
import model.Poste;

/**
 * ServicePoste
 */
public class ServicePoste {

    private DAOPoste daoPoste;

    public ServicePoste(DAOPoste daoPoste) {
        this.daoPoste = daoPoste;
    }

    public Poste trouverPoste(Long idPoste) {
        return daoPoste.read(idPoste);
    }
}