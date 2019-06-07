package dao;

import model.XpSoldatParPoste;

/**
 * DAOXpSoldatParPoste
 */
public interface DAOXpSoldatParPoste {

    XpSoldatParPoste[] read(Long idSoldat);
    XpSoldatParPoste read(Long idSoldat, Long idPoste);
    void create(XpSoldatParPoste xpSoldatParPoste);
    void updateXp(XpSoldatParPoste xpSoldatParPoste);
}