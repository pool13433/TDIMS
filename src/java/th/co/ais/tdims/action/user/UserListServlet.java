/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.dao.TestcastDao;

/**
 *
 * @author Worawat
 */
public class UserListServlet extends HttpServlet {

   final static Logger logger = Logger.getLogger(UserListServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {            
            
            ProfileDao profileDao = new ProfileDao();
            
            request.setAttribute("profileList", profileDao.getAllUser());
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("profileList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/user-list.jsp");
        dispatcher.forward(request, response);
    }

}