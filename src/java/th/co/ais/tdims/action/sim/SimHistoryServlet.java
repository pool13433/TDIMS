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
import th.co.ais.tdims.model.SimHistory;
import th.co.ais.tdims.util.CharacterUtil;

public class SimHistoryServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(SimHistoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String menu = CharacterUtil.removeNull(request.getParameter("menu"));
            String mobile = CharacterUtil.removeNull(request.getParameter("mobileNo"));
            String dateFrom = CharacterUtil.removeNull(request.getParameter("date_from"));
            String dateTo = CharacterUtil.removeNull(request.getParameter("date_to"));
            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 5);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            if("searching".equals(menu)){
                SimDao simDao = new SimDao();
                String pageUrl = request.getContextPath() + "/SimHistoryServlet?" + request.getQueryString();
                String sqlConditionBuilder = simDao.getConditionBuilderSimHis(mobile, dateFrom, dateTo);
                int countRecordAll = simDao.getCountSimHis(sqlConditionBuilder);
                List<SimHistory> simHistoryList = simDao.findSimHistory(mobile, dateFrom, dateTo, limit, offset);
                Pagination pagination = new Pagination(pageUrl, countRecordAll, limit, offset);
                request.setAttribute("pagination", pagination);
                request.setAttribute("simHistoryList", simHistoryList);
                request.setAttribute("mobileNo", mobile);
                request.setAttribute("date_from", dateFrom);
                request.setAttribute("date_to", dateTo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("doGet SimHistoryServlet error : "+e.getMessage());
                    
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-history.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
        } catch (Exception e) {
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-history.jsp");
        dispatcher.forward(request, response);
    }

    
}
