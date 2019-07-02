package model;

/**
 * Soldat
 */
public class Soldat {

    private Long id;
    private String nomUtilisateur;
    private String motDePasse;
    private Integer categorie;

    public Soldat() {}

    public Soldat(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.categorie = Integer.valueOf(0);
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
     * @return the motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }
    /**
     * @param motDePasse the motDePasse to set
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * @return the nomUtilisateur
     */
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }
    /**
     * @param nomUtilisateur the nomUtilisateur to set
     */
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    /**
     * @return the categorie
     */
    public Integer getCategorie() {
        return categorie;
    }
    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }
}