package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOMission;
import dao.DAOPoste;
import dao.DAOPosteSoldatSurMission;
import dao.DAOSoldat;
import dao.DAOXpMissionParPoste;
import dao.DAOXpSoldatParPoste;
import init.DAOInit;
import model.Mission;
import model.Poste;
import model.PosteSoldatSurMission;
import model.Soldat;
import model.XpMissionParPoste;
import model.XpSoldatParPoste;
import service.ServiceMission;
import service.ServicePoste;
import service.ServicePosteSoldatSurMission;
import service.ServiceSoldat;
import service.ServiceXpMissionParPoste;
import service.ServiceXpSoldatParPoste;
import dao.DAOFactory;

/**
 * DetailsMission
 */
public class DetailsMission extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String ATTR_XP_MISSION_PAR_POSTES = "xpMissionParPostes";
    private static final String ATTR_POSTES = "postes";
    private static final String ATTR_SOLDATS = "soldats";
    private static final String ATTR_MISSION = "mission";
    private static final String ATTR_XP_SOLDATS = "xpsSoldats";
    private static final String ATTR_DESC_ETAT_MISSION = "desc_etat_mission";


    private DAOMission daoMission;
    private DAOPoste daoPoste;
    private DAOSoldat daoSoldat;
    private DAOXpMissionParPoste daoXpMissionParPoste;
    private DAOPosteSoldatSurMission daoPosteSoldatSurMission;
    private DAOXpSoldatParPoste daoXpSoldatParPoste;

    @Override
    public void init() throws ServletException {
        this.daoMission  = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoMission();
        this.daoPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoPoste();
        this.daoSoldat = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDAOSoldat();
        this.daoXpMissionParPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoXpMissionParPoste();
        this.daoPosteSoldatSurMission = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoPosteSoldatSurMission();
        this.daoXpSoldatParPoste = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoXpSoldatParPoste();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vue = "/WEB-INF/view/detailsmission.jsp";
        String erreur = new String();
        String succes = new String();
        Mission mission = null;            
        Soldat[] soldats = null;
        XpSoldatParPoste[] xpsSoldats = null;
        Poste[] postes = null;
        XpMissionParPoste[] xpMissionParPostes = null;
        Long idMission = null;
        String descEtatMission = null;

        if(req.getParameter("id_mission") != null) {

            try {
                if(req.getAttribute("erreur") != null) {
                    erreur = (String) req.getAttribute("erreur");
                }

                if(req.getAttribute("succes") != null) {
                    succes = (String) req.getAttribute("succes");                    
                }

                idMission = Long.valueOf(req.getParameter("id_mission"));
                ServiceMission serviceMission = new ServiceMission(daoMission);
                mission = serviceMission.trouverMission(idMission);
                if(mission == null) {
                    throw new Exception("Mission non trouvé.");
                }
                
                ServiceXpMissionParPoste serviceXpMissionParPoste = new ServiceXpMissionParPoste(daoXpMissionParPoste);
                xpMissionParPostes = serviceXpMissionParPoste.trouverXpMissionParPoste(idMission);
                
                ServicePoste servicePoste = new ServicePoste(daoPoste);
                postes = new Poste[xpMissionParPostes.length];
                for (int i = 0; i < postes.length; i++) {
                    postes[i] = servicePoste.trouverPoste(xpMissionParPostes[i].getIdPoste());
                }
                
                ServicePosteSoldatSurMission servicePosteSoldatSurMission = new ServicePosteSoldatSurMission(daoPosteSoldatSurMission);
                PosteSoldatSurMission[] posteSoldatSurMissionListe = servicePosteSoldatSurMission.trouverSoldatsSurMission(idMission);
                ServiceSoldat serviceSoldat = new ServiceSoldat(daoSoldat);
                soldats = new Soldat[postes.length];
                ServiceXpSoldatParPoste serviceXpSoldatParPoste = new ServiceXpSoldatParPoste(daoXpSoldatParPoste);
                xpsSoldats = new XpSoldatParPoste[soldats.length];
                
                for (int i = 0; i < postes.length; i++) {
                    for (int j = 0; j < posteSoldatSurMissionListe.length; j++) {
                        if(xpMissionParPostes[i].getIdPoste() == posteSoldatSurMissionListe[j].getIdPoste()) {
                            soldats[i] = serviceSoldat.trouverSoldat(posteSoldatSurMissionListe[j].getIdSoldat());
                            xpsSoldats[i] = serviceXpSoldatParPoste.obtenirXp(soldats[i].getId(), xpMissionParPostes[i].getIdPoste());
                            if(xpsSoldats[i] == null) {
                                xpsSoldats[i] = new XpSoldatParPoste();
                                xpsSoldats[i].setXp(Integer.valueOf(0));
                            }
                            break;
                        }
                    }
                }

                descEtatMission = Mission.descriptionEtat(mission.getEtat());
            } catch(Exception e) {
                erreur = e.getMessage();
            } finally {
                req.setAttribute(ATTR_MISSION, mission);
                req.setAttribute(ATTR_XP_MISSION_PAR_POSTES, xpMissionParPostes);
                req.setAttribute(ATTR_POSTES, postes);
                req.setAttribute(ATTR_SOLDATS, soldats);
                req.setAttribute(ATT_ERREUR, erreur);
                req.setAttribute(ATT_SUCCES, succes);
                req.setAttribute(ATTR_XP_SOLDATS, xpsSoldats);
                req.setAttribute(ATTR_DESC_ETAT_MISSION, descEtatMission);

                this.getServletContext().getRequestDispatcher(vue).forward(req, resp);
            }
        } else {
            resp.sendRedirect("listemissions");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}