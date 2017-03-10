/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package th.co.ais.tdims.action.config;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.model.Config;
import th.co.ais.tdims.util.CharacterUtil;
 
public class ConfigFormServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ConfigFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ConfigDao configDao = new ConfigDao();
            
            String conId = CharacterUtil.removeNull(request.getParameter("conId"));
            Config config = new Config();
            if(!conId.equals("")){ // NEW
                config = configDao.getConfig(conId);
            }

            request.setAttribute("config", config);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("config form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/config/config-form.jsp");
        dispatcher.forward(request, response);
    }
 
}
