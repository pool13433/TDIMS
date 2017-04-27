/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package th.co.ais.tdims.action.position;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.PositionDao;

public class PositionListServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(PositionListServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PositionDao positionDao = new PositionDao();
            request.setAttribute("positionList", positionDao.getPositionAll());
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PositionListServlet error", e);
        }
        
        request.setAttribute("message", request.getParameter("message"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/position/position-list.jsp");
        dispatcher.forward(request, response);
    }
}
