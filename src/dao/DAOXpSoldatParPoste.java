package dao;

import model.XpSoldatParPoste;

/**
 * DAOXpSoldatParPoste
 */
public interface DAOXpSoldatParPoste {

    XpSoldatParPoste[] read(Long idSoldat);
    XpSoldatParPoste read(Long idSoldat, Long idPoste);
}