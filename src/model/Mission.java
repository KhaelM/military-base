package model;

/**
 * Mission
 */
public class Mission {

    public final static int ETAT_DISPO = 0;
    public final static int ETAT_EN_COURS = 1;
    public final static int ETAT_SUCCES = 2;
    public final static int ETAT_ECHEC = 3;

    private Long id;
    private String objectif;
    private Integer etat;

    public static String descriptionEtat(Integer etat) throws Exception {
        switch (etat.intValue()) {
            case Mission.ETAT_DISPO:
                return "disponible";
            case Mission.ETAT_EN_COURS:
                return "en cours";        
            case Mission.ETAT_SUCCES:
                return "succès";
            case Mission.ETAT_ECHEC:
                return "échec";
            default:
                throw new Exception("Etat inexistant.");
        }
    }

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