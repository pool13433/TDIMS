/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.model.Pagination;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

public class SimListServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 300);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            SimDao simDao = new SimDao();
            
            List<Sim> simList = simDao.getSimRecordLimit(limit, offset);
            int countSimRecordAll = simDao.getCountSim(null);
            String pageUrl = request.getContextPath() + "/SimListServlet?menu=sim";
            Pagination pagination = new Pagination(pageUrl, countSimRecordAll, limit, offset);

            request.setAttribute("pagination", pagination);
            request.setAttribute("simList", simList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SimList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-list.jsp");
        dispatcher.forward(request, response);
    }

}
