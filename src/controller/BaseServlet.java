package controller;

import javax.servlet.http.HttpServlet;

/**
 * BaseServlet
 */
public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected final String ATT_VIEW = "view";
    protected final String ATT_TITLE = "title";
    protected final String ATT_ACTIVE_MENU = "active_menu";
    protected final String ATT_BACKGROUND_ID = "background_id";
    protected final String ATT_ERREUR = "erreur";
    protected final String ATT_SUCCES = "succes";

    protected String vue = "/WEB-INF/view/template.jsp";

    /**
     * @return the vue
     */
    public String getVue() {
        return vue;
    }

    /**
     * @param vue the vue to set
     */
    public void setVue(String vue) {
        this.vue = vue;
    }
}