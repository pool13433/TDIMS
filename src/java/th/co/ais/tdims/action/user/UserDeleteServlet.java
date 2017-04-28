/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.dao.TestcaseDao;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Worawat
 */
public class UserDeleteServlet extends HttpServlet {

final static Logger logger = Logger.getLogger(UserDeleteServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {            
            String profileId = CharacterUtil.removeNull(request.getParameter("profileId"));
            ProfileDao profileDao = new ProfileDao();
            
             int exe = profileDao.deleteProfile(Integer.parseInt(profileId));
            request.setAttribute("message", "delete testcase success");
            
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("testcase Error", e);
        }
        response.sendRedirect(request.getContextPath() + "/UserListServlet");
    }

}
