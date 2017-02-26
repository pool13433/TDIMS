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
import th.co.ais.tdims.dao.ProjectDao;

/**
 *
 * @author POOL_LAPTOP
 */
public class ProjectListServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ProjectListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            ProjectDao projectDao = new ProjectDao();

            request.setAttribute("projectList", projectDao.getProjectAll());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ProjectList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/project-list.jsp");
        dispatcher.forward(request, response);
    }
}
