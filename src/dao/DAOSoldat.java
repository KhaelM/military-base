package dao;

import model.Soldat;

/**
 * DAOSoldat
 */
public interface DAOSoldat {

    void create(Soldat soldat);
    Soldat read(String username);
    Soldat read(Long id);
}