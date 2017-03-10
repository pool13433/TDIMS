/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.EnvironmentDao;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author POOL_LAPTOP
 */
public class SimFormServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ConfigDao configDao = new ConfigDao();
            ProjectDao projecDao = new ProjectDao();
            SimDao simDao = new SimDao();
            
            String simId = CharacterUtil.removeNull(request.getParameter("simId"));
            Sim sim = null;
            if(simId.equals("")){ // NEW
                sim = new Sim();
            }else{
                sim = simDao.getSim(Integer.parseInt(simId));
            }

            request.setAttribute("chargeTypeList", configDao.getConfigList("CHARGE_TYPE"));
            request.setAttribute("usageTypeList", configDao.getConfigList("USAGE_TYPE"));
            request.setAttribute("simStatusList", configDao.getConfigList("SIM_STATUS"));
            request.setAttribute("systemList", configDao.getConfigList("SYSTEM"));
            request.setAttribute("ownerList", new ProfileDao().getAllUser());
            request.setAttribute("envList", new EnvironmentDao().getAllEnvirenment());
            request.setAttribute("projectList", projecDao.getProjectAll());
            request.setAttribute("sim", sim);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("sim form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-form.jsp");
        dispatcher.forward(request, response);
    }

}
