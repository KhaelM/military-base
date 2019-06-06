package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class Connexion extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String ATT_NOM_UTILISATEUR = "nom_utilisateur";
    private static final String ATT_ERREUR = "erreur";
    private static final String ATT_SUCCES = "succes";
    private static final String VUE = "/WEB-INF/view/connexion.jsp";

    private DAOSoldat daoSoldat;

    @Override
    public void init() throws ServletException {
        this.daoSoldat = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDAOSoldat();    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATT_NOM_UTILISATEUR, "");        
        req.setAttribute(ATT_ERREUR, "");
        req.setAttribute(ATT_SUCCES, "");
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceSoldat serviceSoldat = new ServiceSoldat(this.daoSoldat);
        String username = req.getParameter("nom_utilisateur");
        String password = req.getParameter("mot_de_passe");
        String erreur = new String();
        String succes = new String();

        Soldat soldat = serviceSoldat.trouverSoldat(username);
        if(soldat == null || !(soldat.getMotDePasse().equals(password))) {
            erreur = "Mot de passe et/ou nom d'utilisateur incorrect.";
        } else {
            succes = "Vous êtes connecté";
            HttpSession session = req.getSession();
            session.setAttribute("nom_utilisateur", username);
            session.setAttribute("id_utilisateur", soldat.getId());
            session.setAttribute("categorie", soldat.getCategorie());
        }

        req.setAttribute(ATT_NOM_UTILISATEUR, username);
        req.setAttribute(ATT_ERREUR, erreur);
        req.setAttribute(ATT_SUCCES, succes);

        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }
}