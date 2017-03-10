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
import th.co.ais.tdims.model.Department;
import th.co.ais.tdims.util.CharacterUtil;

public class DepartmentSaveServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(DepartmentSaveServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Department dep = new Department();
            dep.setDepId(CharacterUtil.removeNull(request.getParameter("depId")));
            dep.setDepName(CharacterUtil.removeNull(request.getParameter("depName")));
            dep.setDepDesc(CharacterUtil.removeNull(request.getParameter("depDesc")));

            DepartmentDao depDao = new DepartmentDao();
            
            logger.info("depId ::=="+dep.getDepId());
            
            if(dep.getDepId().equals("")){
                logger.info(" create ");
                depDao.createDepartment(dep);
            }else{
                logger.info(" update ");
                depDao.updateDepartment(dep);
            }
            request.setAttribute("message", "save Department success");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save Department error", e);
            request.setAttribute("message", "save Department error");
        }
        response.sendRedirect(request.getContextPath() + "/DepertmentListServlet");
    }
}
