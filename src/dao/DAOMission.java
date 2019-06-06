package dao;

import model.Mission;

/**
 * DAOMission
 */
public interface DAOMission {

    Mission[] read();
    Mission read(Long id);
}