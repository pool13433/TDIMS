/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.project;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.model.Project;
import th.co.ais.tdims.util.CharacterUtil;

public class ProjectFormServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ProjectFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProjectDao projecDao = new ProjectDao();

            String projectId = CharacterUtil.removeNull(request.getParameter("projectId"));
            Project project = null;
            if (projectId.equals("")) { // NEW
                project = new Project();
            } else {
                project = projecDao.getProject(Integer.parseInt(projectId));
            }
            ConfigDao configDao = new ConfigDao();
            request.setAttribute("projectStatusList", configDao.getConfigList("PROJECT_STATUS"));
            request.setAttribute("project", project);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("project form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/project-form.jsp");
        dispatcher.forward(request, response);
    }
}
