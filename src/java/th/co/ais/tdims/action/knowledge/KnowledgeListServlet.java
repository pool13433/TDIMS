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

public class KnowledgeListServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(KnowledgeListServlet.class);
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("KnowledgeListServlet");
        String dirFile = request.getParameter("pathDir");
        try {
            KnowledgeDao knowledgeDao = new KnowledgeDao();            
            request.setAttribute("knowledgeList",knowledgeDao.getKnowledgeAll()); 
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("KnowledgeList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/knowledge/knowledge-list.jsp");
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
