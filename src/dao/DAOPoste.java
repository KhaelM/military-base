package dao;

import model.Poste;

/**
 * DAOPoste
 */
public interface DAOPoste {

    Poste read(Long idPoste);
    Poste[] readAll();
}