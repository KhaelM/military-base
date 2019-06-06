package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.DAOPoste;
import dao.DAOSoldat;
import dao.DAOXpSoldatParPoste;
import init.DAOInit;
import model.Poste;
import model.Soldat;
import model.XpSoldatParPoste;
import service.ServicePoste;
import service.ServiceSoldat;
import service.ServiceXpSoldatParPoste;

/**
 * Profil
 */
public class Profil extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String ATTR_POSTES = "postes";
    private static final String ATTR_XP_SOLDAT_PAR_POSTES = "xpSoldatParPoste";
    private static final String VUE = "/WEB-INF/view/profil.jsp";
    
    private DAOSoldat daoSoldat;
    private DAOPoste daoPoste;
    private DAOXpSoldatParPoste daoXpSoldatParPoste;

    @Override
    public void init() throws ServletException {
        this.daoSoldat = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDAOSoldat();
        this.daoPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoPoste();
        this.daoXpSoldatParPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoXpSoldatParPoste();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceSoldat serviceSoldat = new ServiceSoldat(daoSoldat);
        HttpSession session = req.getSession();
        String nomUtilisateur = (String) session.getAttribute("nom_utilisateur");
        Soldat soldat = serviceSoldat.trouverSoldat(nomUtilisateur);
    
        if(soldat == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        ServiceXpSoldatParPoste serviceXpSoldatParPoste = new ServiceXpSoldatParPoste(daoXpSoldatParPoste);
        XpSoldatParPoste[] xpSoldatParPostes = serviceXpSoldatParPoste.trouverXpParPostes(soldat.getId());
        
        ServicePoste servicePoste = new ServicePoste(daoPoste);
        Poste[] postes = new Poste [xpSoldatParPostes.length];
        for (int i = 0; i < xpSoldatParPostes.length; i++) {
            postes[i] = servicePoste.trouverPoste(xpSoldatParPostes[i].getIdPoste());
        }

        req.setAttribute(ATTR_POSTES, postes);
        req.setAttribute(ATTR_XP_SOLDAT_PAR_POSTES, xpSoldatParPostes);

        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}