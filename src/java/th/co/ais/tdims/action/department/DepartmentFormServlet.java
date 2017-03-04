/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package th.co.ais.tdims.action.department;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.DepartmentDao;
import th.co.ais.tdims.model.Department;
import th.co.ais.tdims.util.CharacterUtil;

public class DepartmentFormServlet extends HttpServlet {
    
    final static Logger logger = Logger.getLogger(DepartmentFormServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DepartmentDao depDao = new DepartmentDao();
            
            String depId = CharacterUtil.removeNull(request.getParameter("depId"));
            Department dep = new Department();
            if(!depId.equals("")){ // NEW
                dep = depDao.getDepertment(depId);
            }

            request.setAttribute("dep", dep);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("config form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/department/department-form.jsp");
        dispatcher.forward(request, response);
    }
}
