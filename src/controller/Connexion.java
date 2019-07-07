package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Soldat;
import service.ServiceSoldat;

import dao.DAOSoldat;
import init.DAOInit;
import dao.DAOFactory;

/**
 * Login
 */
public class Connexion extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String ATT_NOM_UTILISATEUR = "nom_utilisateur";

    private DAOSoldat daoSoldat;

    @Override
    public void init() throws ServletException {
        this.daoSoldat = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDAOSoldat();    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATT_NOM_UTILISATEUR, "");        
        req.setAttribute(ATT_ERREUR, null);
        req.setAttribute(ATT_SUCCES, null);
        req.setAttribute(ATT_VIEW, "connexion.jsp");
        req.setAttribute(ATT_TITLE, "Connectez-vous");
        req.setAttribute(ATT_BACKGROUND_ID, "connexion");
        req.setAttribute(ATT_ACTIVE_MENU, "connexion");

        this.getServletContext().getRequestDispatcher(vue).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceSoldat serviceSoldat = new ServiceSoldat(this.daoSoldat);
        String username = req.getParameter("nom_utilisateur");
        String password = req.getParameter("mot_de_passe");
        String erreur = null;
        String succes = null;

        try {
            Soldat soldat = serviceSoldat.seConnecter(username, password);
            succes = "Connexion réussie.";
            HttpSession session = req.getSession();
            session.setAttribute("nom_utilisateur", username);
            session.setAttribute("id_utilisateur", soldat.getId());
            session.setAttribute("categorie", soldat.getCategorie());
            
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            req.setAttribute(ATT_NOM_UTILISATEUR, username);
            req.setAttribute(ATT_ERREUR, erreur);
            req.setAttribute(ATT_SUCCES, succes);
            req.setAttribute(ATT_VIEW, "connexion.jsp");
            req.setAttribute(ATT_TITLE, "Connexion");
            req.setAttribute(ATT_BACKGROUND_ID, "connexion");
            req.setAttribute(ATT_ACTIVE_MENU, "connexion");

            this.getServletContext().getRequestDispatcher(vue).forward(req, resp);
        }
    }
}