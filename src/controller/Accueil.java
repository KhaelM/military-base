package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Accueil
 */
public class Accueil extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String VUE = "/WEB-INF/view/template.jsp";
    private static final String ATT_VIEW = "view";
    private static final String ATT_TITLE = "title";
    private static final String ATT_ACTIVE_MENU = "active_menu";
    private static final String ATT_BACKGROUND_ID = "background_id";

    private static final String ATT_MESSAGE_ACCUEIL = "message_accueil";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String messageAccueil = new String();
        if(session.getAttribute("id_utilisateur") != null) {
            messageAccueil = "Bon retour parmi les vivant";
        } else {
            messageAccueil = "Bienvenue cher visiteur";
        }
        
        req.setAttribute(ATT_ACTIVE_MENU, "accueil");
        req.setAttribute(ATT_BACKGROUND_ID, "accueil");
        req.setAttribute(ATT_MESSAGE_ACCUEIL, messageAccueil);
        req.setAttribute(ATT_VIEW, "accueil.jsp");
        req.setAttribute(ATT_TITLE, "Bienvenue à bord");
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}