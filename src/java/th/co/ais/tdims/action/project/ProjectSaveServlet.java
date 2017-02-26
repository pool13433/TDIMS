/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.project;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.action.sim.SimSaveServlet;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.model.Project;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author POOL_LAPTOP
 */
public class ProjectSaveServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimSaveServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info(" save project ");
        try {
            String projName = CharacterUtil.removeNull(request.getParameter("projName"));
            String projDesc = CharacterUtil.removeNull(request.getParameter("projDesc"));
            String projStatus = CharacterUtil.removeNull(request.getParameter("projStatus"));
            String projId = CharacterUtil.removeNull(request.getParameter("projId"));

            Project project = new Project();
            project.setProjDesc(projDesc);
            project.setProjName(projName);
            project.setProjStatus(projStatus);
            
            ProjectDao simDao = new ProjectDao();

            logger.info("projId ::==" + projId);
            if (projId.equals("")) {
                logger.info(" create ");
                simDao.createProject(project);
            } else {
                logger.info(" update ");
                project.setProjId(projId);
                simDao.updateProject(project);
            }
            request.setAttribute("message", "save project success");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save project error", e);
            request.setAttribute("message", "save project error");
        }
        response.sendRedirect(request.getContextPath() + "/ProjectListServlet");
    }
}
