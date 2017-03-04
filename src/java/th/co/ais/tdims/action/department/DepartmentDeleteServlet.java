/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package th.co.ais.tdims.action.department;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.DepartmentDao;
import th.co.ais.tdims.util.CharacterUtil;

public class DepartmentDeleteServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(DepartmentDeleteServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            int exe = new DepartmentDao().deleteDepartment(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("depId"))));
            request.setAttribute("message", "delete department success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete sim error", e);
            request.setAttribute("message", "delete department error");
        }
        response.sendRedirect(request.getContextPath() + "/DepertmentListServlet");
        
    }

}
