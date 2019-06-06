package model;

/**
 * XpMissionParPoste
 */
public class XpMissionParPoste {

    private Long id;
    private Long idMission;
    private Long idPoste;
    private Integer xpMin;
    private Integer xpGain;

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
     * @return the xpGain
     */
    public Integer getXpGain() {
        return xpGain;
    }
    /**
     * @param xpGain the xpGain to set
     */
    public void setXpGain(Integer xpGain) {
        this.xpGain = xpGain;
    }

    /**
     * @return the xpMin
     */
    public Integer getXpMin() {
        return xpMin;
    }
    /**
     * @param xpMin the xpMin to set
     */
    public void setXpMin(Integer xpMin) {
        this.xpMin = xpMin;
    }
}