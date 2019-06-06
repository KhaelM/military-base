package model;

/**
 * XpSoldatParPoste
 */
public class XpSoldatParPoste {

    private Long id;
    private Long idPoste;
    private Long idSoldat;
    private Integer xp;

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

    /**
     * @return the xp
     */
    public Integer getXp() {
        return xp;
    }
    /**
     * @param xp the xp to set
     */
    public void setXp(Integer xp) {
        this.xp = xp;
    }
}