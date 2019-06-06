package model;

/**
 * Mission
 */
public class Mission {

    private Long id;
    private String objectif;
    private Integer etat;

    /**
     * @return the etat
     */
    public Integer getEtat() {
        return etat;
    }
    /**
     * @param etat the etat to set
     */
    public void setEtat(Integer etat) {
        this.etat = etat;
    }

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
     * @return the objectif
     */
    public String getObjectif() {
        return objectif;
    }
    /**
     * @param objectif the objectif to set
     */
    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }
}