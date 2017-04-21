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
import th.co.ais.tdims.model.Pagination;
import th.co.ais.tdims.util.CharacterUtil;

public class ConfigListServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ConfigListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 10);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            ConfigDao configDao = new ConfigDao();
            
            int countConfigRecordAll = configDao.getCountConfig();
            String pageUrl = request.getContextPath() + "/ConfigListServlet?menu=configuration";
            Pagination pagination = new Pagination(pageUrl, countConfigRecordAll, limit, offset);

            request.setAttribute("pagination", pagination);
            request.setAttribute("confList", configDao.getAllConfig(limit,offset));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("confList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/config/config-list.jsp");
        dispatcher.forward(request, response);
    }

}
