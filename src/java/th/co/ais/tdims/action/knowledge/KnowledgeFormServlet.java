/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.knowledge;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.EnvironmentDao;
import th.co.ais.tdims.dao.KnowledgeDao;
import th.co.ais.tdims.dao.ModuleDao;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.dao.TestcastDao;
import th.co.ais.tdims.model.Knowledge;
import th.co.ais.tdims.model.Testcase;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Worawat
 */
public class KnowledgeFormServlet extends HttpServlet {

   
    final static Logger logger = Logger.getLogger(KnowledgeFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProjectDao projectDao = new ProjectDao();
            KnowledgeDao knowledgeDao = new KnowledgeDao();
          
           String id = CharacterUtil.removeNull(request.getParameter("knlId"));
            Knowledge knowledge = null;
            if(id.equals("")){ // NEW
                knowledge = new Knowledge();
            }else{
               knowledge = knowledgeDao.getKnowledge(Integer.parseInt(id));
            }
            request.setAttribute("projectList", projectDao.getProjectAll());
            request.setAttribute("knowledge", knowledge);
             TeamDao teamDao = new TeamDao();
            request.setAttribute("teamList", teamDao.getTeamAll());
            ModuleDao moduleDao = new ModuleDao();
            request.setAttribute("typeList", moduleDao.getModuleAll());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("testcase form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/knowledge/knowledge-form.jsp");
        dispatcher.forward(request, response);
    }

}