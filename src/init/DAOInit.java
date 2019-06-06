package init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.DAOFactory;

/**
 * DaoFactoryInitialisation
 */
public class DAOInit implements ServletContextListener {

    public static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        this.daoFactory = DAOFactory.getInstance();
        servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}