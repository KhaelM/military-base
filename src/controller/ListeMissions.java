package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.DAOMission;
import init.DAOInit;
import model.Mission;
import service.ServiceMission;


/**
 * ChoixMission
 */
public class ListeMissions extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String ATT_MISSIONS = "missions";

    private DAOMission daoMission;

    @Override
    public void init() throws ServletException {
        this.daoMission = ((DAOFactory) this.getServletContext().getAttribute(DAOInit.ATT_DAO_FACTORY)).getDaoMission();    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vue = "/WEB-INF/view/listemissions.jsp";
        ServiceMission serviceMission = new ServiceMission(daoMission);
        Mission[] missions = serviceMission.trouverToutesLesMission();
        req.setAttribute(ATT_MISSIONS, missions);

        this.getServletContext().getRequestDispatcher(vue).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}