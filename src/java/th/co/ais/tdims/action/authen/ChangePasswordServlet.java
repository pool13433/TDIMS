/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.authen;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

public class ChangePasswordServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ChangePasswordServlet.class);


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        try {
            String passwordOld = CharacterUtil.removeNull(request.getParameter("passwordOld"));
            String passwordNew = CharacterUtil.removeNull(request.getParameter("passwordNew"));
            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            // Check Password Old
            ProfileDao dao = new ProfileDao();
            Profile user = dao.getUser(profile.getUsername(), passwordOld);
            if (user == null) {
                request.setAttribute("status", "The old password is incorrect");
                dispatcher = request.getRequestDispatcher("/jsp/authen/password.jsp");
            } else {
                int exe = dao.updatePassword(Integer.parseInt(user.getProfileId()), passwordNew);
                if (exe == 0) {
                    request.setAttribute("status", "Change password Failed");
                } else {
                    request.setAttribute("status", "Change Password Success");                    
                }
                dispatcher = request.getRequestDispatcher("/jsp/dashboard.jsp?menu=dashboard");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save Change Password error", e);
        }
    }

}
