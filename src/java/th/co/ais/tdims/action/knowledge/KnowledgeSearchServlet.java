/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.knowledge;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.KnowledgeDao;
import th.co.ais.tdims.dao.ModuleDao;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.model.Knowledge;
import th.co.ais.tdims.util.CharacterUtil;


public class KnowledgeSearchServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(KnowledgeSearchServlet.class);
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("KnowledgeSearchServlet");
        String menu = CharacterUtil.removeNull(request.getParameter("menu"));
        //String changed = request.getParameter("changed");        
        String path = request.getParameter("path");
        request.setAttribute("path",path); 
        String fileName = request.getParameter("file_name");
        request.setAttribute("file_name",fileName); 
        String teamId = request.getParameter("team");
        request.setAttribute("team",teamId); 
        String type = request.getParameter("type");
        request.setAttribute("type",type); 
        String details = request.getParameter("details");
        request.setAttribute("details",details); 
        try {
            KnowledgeDao knowledgeDao = new KnowledgeDao();   
            if("searching".equals(menu)){
                Knowledge knowledge = new Knowledge();
                knowledge.setFileName(fileName);
                knowledge.setTeamId(teamId);
                knowledge.setType(type);
                knowledge.setDetails(details);
                request.setAttribute("knowledgeList",knowledgeDao.findKnowledge(knowledge));
            }
            
        if(path!= null){
            Desktop desktop = Desktop.getDesktop();
            File dirToOpen = null;
            try {
                dirToOpen = new File(path);
                desktop.open(dirToOpen);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("KnowledgeOpenDir Error", e);
            }
        }

            TeamDao teamDao = new TeamDao();
            request.setAttribute("teamList", teamDao.getTeamAll());
            ModuleDao moduleDao = new ModuleDao();
            request.setAttribute("typeList", moduleDao.getModuleAll());
            
            /*
            System.out.println(" team id: "+teamId);
            if(!"".equals(teamId)){
                ModuleDao m = new ModuleDao();
                request.setAttribute("typeList", m.getModuleByTeam(teamId));
            }
            */
           
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("KnowledgeSearch Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/knowledge/knowledge-search.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           
            String id = request.getParameter("teamId");
            ModuleDao m = new ModuleDao();
            request.setAttribute("typeList", m.getModuleByTeam(id));
        } catch (Exception e) {
            logger.error("doPost KnowledgeSearchServlet "+e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/knowledge/knowledge-search.jsp");
        dispatcher.forward(request, response);
    }
}
