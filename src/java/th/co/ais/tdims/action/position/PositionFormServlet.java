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
import th.co.ais.tdims.dao.DepartmentDao;
import th.co.ais.tdims.dao.PositionDao;
import th.co.ais.tdims.model.Position;
import th.co.ais.tdims.util.CharacterUtil;

public class PositionFormServlet extends HttpServlet {
    
    final static Logger logger = Logger.getLogger(PositionFormServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String posId = CharacterUtil.removeNull(request.getParameter("posId"));
            Position  position = new Position();
            PositionDao positionDao = new PositionDao();
            if(!posId.equals("")){ // NEW
                position = positionDao.getPosition(posId);
            }

            DepartmentDao dep = new DepartmentDao();
            request.setAttribute("position", position);
            request.setAttribute("departmentList", dep.getAllDepartment());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("position form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/position/position-form.jsp");
        dispatcher.forward(request, response);
    }
}
