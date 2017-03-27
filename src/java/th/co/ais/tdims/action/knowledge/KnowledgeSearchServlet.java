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


public class KnowledgeSearchServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(KnowledgeSearchServlet.class);
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("KnowledgeSearchServlet");
        String dirFile = request.getParameter("pathDir");
        String searchBox = request.getParameter("searchBox");
        boolean projectId = request.getParameter("projectId") != null;
        boolean createBy = request.getParameter("createBy") != null;
        String searchFlag = request.getParameter("searchFlag");
        System.out.println(" projectId : "+projectId + " createBy : "+createBy );
        
        try {
            KnowledgeDao knowledgeDao = new KnowledgeDao();            
            request.setAttribute("knowledgeList",knowledgeDao.getKnowledgeAll()); 
            
        System.out.println(" path : "+dirFile);
        if(dirFile!= null){
            Desktop desktop = Desktop.getDesktop();
            File dirToOpen = null;
            try {
                dirToOpen = new File(dirFile);
                desktop.open(dirToOpen);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("KnowledgeOpenDir Error", e);
            }
        }
        
        if(searchFlag != null){
            if(!projectId  && !createBy){
                //ListAll
                request.setAttribute("knowledgeList",knowledgeDao.getKnowledgeAll());
            }else{           
                request.setAttribute("knowledgeList",knowledgeDao.findKnowledge(createBy, projectId, searchBox));
            }
            if(createBy){
                request.setAttribute("createBy",createBy); 
            }
            if(projectId){
                request.setAttribute("projectId",request.getParameter("projectId")); 
            }
            request.setAttribute("searchBox",searchBox); 
            request.setAttribute("searchFlag",null); 
        }

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
            
        } catch (Exception e) {
            
        }
    }
}
