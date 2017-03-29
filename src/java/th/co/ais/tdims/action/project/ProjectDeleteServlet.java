/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.util.CharacterUtil;

public class ProjectDeleteServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ProjectDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String projectId = CharacterUtil.removeNull(request.getParameter("projectId"));

            int exe = new ProjectDao().deleteProject(Integer.parseInt(projectId));
            request.setAttribute("message", "delete project success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete project error", e);
            request.setAttribute("message", "delete project error");
        }
        response.sendRedirect(request.getContextPath() + "/ProjectListServlet");
    }
}
