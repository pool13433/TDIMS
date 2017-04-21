/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.module;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ModuleDao;

/**
 *
 * @author POOL_LAPTOP
 */
public class ModuleListServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ModuleListServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("moduleList", new ModuleDao().getModuleAll());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("moduleList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/module/module-list.jsp");
        dispatcher.forward(request, response);
    }

}
