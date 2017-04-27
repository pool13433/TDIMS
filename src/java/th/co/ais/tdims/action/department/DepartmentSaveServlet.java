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
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

public class DepartmentSaveServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(DepartmentSaveServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Profile profile = (Profile)request.getSession().getAttribute("USER_PROFILE");
            Department dep = new Department();
            dep.setDepId(CharacterUtil.removeNull(request.getParameter("depId")));
            dep.setDepName(CharacterUtil.removeNull(request.getParameter("depName")));
            dep.setDepDesc(CharacterUtil.removeNull(request.getParameter("depDesc")));
            dep.setCreateBy(profile.getProfileId());
            DepartmentDao depDao = new DepartmentDao();
            
            logger.info("depId ::=="+dep.getDepId());
            int exe = 0;
            if(dep.getDepId().equals("")){
                logger.info(" create ");
                exe = depDao.createDepartment(dep);
            }else{
                logger.info(" update ");
                dep.setUpdateBy(profile.getProfileId());
                exe = depDao.updateDepartment(dep);
            }
            
            MessageUI message = null;
            if (exe == 0) {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการบันทึกข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "บันทึกข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save Department error", e);
        }
        response.sendRedirect(request.getContextPath() + "/DepertmentListServlet");
    }
}
