package dao;

import java.sql.Connection;

import model.Mission;

/**
 * DAOMission
 */
public interface DAOMission {

    Mission[] read();
    Mission read(Long id);
    void updateEtat(Mission mission);
    void create(Mission mission, Connection connection);
    void create(Mission mission);
}