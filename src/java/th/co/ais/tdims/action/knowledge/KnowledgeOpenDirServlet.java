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

public class KnowledgeOpenDirServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(KnowledgeOpenDirServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("KnowledgeOpenDirServlet");
        String dirFile = request.getParameter("pathDir");
        System.out.println(" path : "+dirFile);
        Desktop desktop = Desktop.getDesktop();
        File dirToOpen = null;
        try {
            dirToOpen = new File(dirFile);
            desktop.open(dirToOpen);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("KnowledgeOpenDir Error", e);
        }
        response.sendRedirect(request.getContextPath() + "/KnowledgeListServlet");     
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


}
