package model;

/**
 * PosteSoldatSurMission
 */
public class PosteSoldatSurMission {

    private Long id;
    private Long idMission;
    private Long idSoldat;
    private Long idPoste;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idMission
     */
    public Long getIdMission() {
        return idMission;
    }
    /**
     * @param idMission the idMission to set
     */
    public void setIdMission(Long idMission) {
        this.idMission = idMission;
    }

    /**
     * @return the idPoste
     */
    public Long getIdPoste() {
        return idPoste;
    }
    /**
     * @param idPoste the idPoste to set
     */
    public void setIdPoste(Long idPoste) {
        this.idPoste = idPoste;
    }

    /**
     * @return the idSoldat
     */
    public Long getIdSoldat() {
        return idSoldat;
    }
    /**
     * @param idSoldat the idSoldat to set
     */
    public void setIdSoldat(Long idSoldat) {
        this.idSoldat = idSoldat;
    }
}