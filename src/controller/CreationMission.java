package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOMission;
import dao.DAOPoste;
import dao.DAOSoldat;
import dao.DAOXpMissionParPoste;
import dao.DAOFactory;
import init.DAOInit;
import model.Poste;
import service.ServiceMission;

/**
 * CreationMission
 */
public class CreationMission extends BaseServlet {

    private static final long serialVersionUID = 1L;


    private static final String ATT_POSTES = "postes";

    private DAOMission daoMission;
    private DAOPoste daoPoste;
    private DAOXpMissionParPoste daoXpMissionParPoste;
    private DAOSoldat daoSoldat;
    
    @Override
    public void init() throws ServletException {
        this.daoSoldat = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDAOSoldat();
        this.daoMission = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoMission();
        this.daoPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoPoste();
        this.daoXpMissionParPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoXpMissionParPoste();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vue = "/WEB-INF/view/creationMission.jsp";
        Poste[] postes = null;
        HttpSession session = req.getSession();
        String erreur = null;
        String prevUrl = req.getParameter("prevUrl");
        
        try {
            if(session.getAttribute("id_utilisateur") == null) {
                throw new Exception("Vous devez vous connecter pour accéder à cette page.");
            }
            if((int) session.getAttribute("categorie") != 1) {
                throw new Exception("Vous n'êtes pas autorisé à accéder à cette page.");
            }
            postes = daoPoste.readAll();
        } catch (Exception e) {
            erreur = e.getMessage();
            vue = "/"+prevUrl;
        } finally {
            req.setAttribute(ATT_ERREUR, erreur);
            req.setAttribute(ATT_POSTES, postes);
            
            this.getServletContext().getRequestDispatcher(vue).forward(req, resp);        
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vue = "/WEB-INF/view/creationMission.jsp";
        String erreur = new String();
        String succes = new String();
        Poste[] postes = daoPoste.readAll();
        Long idUtilisateur = null;        
        String objectifMission = null;
        HttpSession session = req.getSession();

        ServiceMission serviceMission = new ServiceMission(daoMission, daoSoldat, daoXpMissionParPoste);
        try {
            idUtilisateur = (Long) session.getAttribute("id_utilisateur");
            objectifMission = req.getParameter("objectif_mission");
            serviceMission.creerMission(idUtilisateur, objectifMission, postes, req, ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)));                        
            succes = "Mission créé";
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            req.setAttribute(ATT_ERREUR, erreur);
            req.setAttribute(ATT_SUCCES, succes);
            req.setAttribute(ATT_POSTES, postes);
            
            this.getServletContext().getRequestDispatcher(vue).forward(req, resp);
        }
    }
}