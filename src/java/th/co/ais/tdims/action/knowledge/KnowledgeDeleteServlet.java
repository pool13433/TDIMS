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
import th.co.ais.tdims.dao.KnowledgeDao;

/**
 *
 * @author Worawat
 */
public class KnowledgeDeleteServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(KnowledgeDeleteServlet.class);
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("KnowledgeDeleteServlet");
        int id = Integer.parseInt(request.getParameter("knlId"));
        try {
            KnowledgeDao knowledgeDao = new KnowledgeDao();
            knowledgeDao.deleteKnowledge(id);
       request.setAttribute("message", "delete knowledge success");
            
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("knowledge Error", e);
        }
        response.sendRedirect(request.getContextPath() + "/KnowledgeListServlet");
    }
   
}

