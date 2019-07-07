package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.DAOMission;
import dao.DAOPoste;
import dao.DAOPosteSoldatSurMission;
import dao.DAOSoldat;
import dao.DAOXpMissionParPoste;
import dao.DAOXpSoldatParPoste;
import init.DAOInit;
import service.ServiceSoldat;

/**
 * MiseAJourMission
 */
public class MiseAJourMission extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private DAOSoldat daoSoldat;
    private DAOMission daoMission;
    private DAOPoste daoPoste;
    private DAOPosteSoldatSurMission daoPosteSoldatSurMission;
    private DAOXpMissionParPoste daoXpMissionParPoste;
    private DAOXpSoldatParPoste daoXpSoldatParPoste;
    
    @Override
    public void init() throws ServletException {
        this.daoSoldat = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDAOSoldat();        
        this.daoMission = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoMission();        
        this.daoPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoPoste();        
        this.daoPosteSoldatSurMission = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoPosteSoldatSurMission();        
        this.daoXpMissionParPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoXpMissionParPoste();        
        this.daoXpSoldatParPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoXpSoldatParPoste();    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vue = "/detailsmission";
        String erreur = null;
        String succes = null;
        ServiceSoldat serviceSoldat = new ServiceSoldat(daoSoldat, daoMission, daoPoste, daoPosteSoldatSurMission, daoXpMissionParPoste, daoXpSoldatParPoste);
        HttpSession session = req.getSession();
        Long idSoldat = null;
        Long idMission = null;
        Integer etat = null;
        
        try {
            idSoldat = Long.valueOf((Long)session.getAttribute("id_utilisateur"));
            idMission = Long.parseLong(req.getParameter("id_mission"));
            etat = Integer.parseInt(req.getParameter("action"));
            succes = serviceSoldat.mettreAJourMission(idSoldat, idMission, etat);
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            req.setAttribute(ATT_ERREUR, erreur);
            req.setAttribute(ATT_SUCCES, succes);
            
            this.getServletContext().getRequestDispatcher(vue+"?id_mission="+ idMission).forward(req, resp);
        }
    }
}