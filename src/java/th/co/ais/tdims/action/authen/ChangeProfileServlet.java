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
import static th.co.ais.tdims.action.authen.RegisterServlet.logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.PositionDao;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

public class ChangeProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PositionDao positionDao = new PositionDao();
            request.setAttribute("positionList", positionDao.getPositionAll());
            request.setAttribute("genderList", new ConfigDao().getConfigList("PROFILE_GENDER"));
            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            request.setAttribute("profile", profile);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("change profile error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/authen/profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        try {
            String firstName = CharacterUtil.removeNull(request.getParameter("firstName"));
            String lastName = CharacterUtil.removeNull(request.getParameter("lastName"));
            String mobile = CharacterUtil.removeNull(request.getParameter("mobile"));
            String email = CharacterUtil.removeNull(request.getParameter("email"));
            String position = CharacterUtil.removeNull(request.getParameter("position"));
            String gender = CharacterUtil.removeNull(request.getParameter("gender"));
            String profileId = CharacterUtil.removeNull(request.getParameter("profileId"));

            ProfileDao profileDao = new ProfileDao();

            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            profile.setEmail(email);
            profile.setFirstName(firstName);
            profile.setGender(gender);
            profile.setLastName(lastName);
            profile.setMobile(mobile);
            profile.setPosition(position);
            profile.setUpdateBy(1);
            profile.setProfileId(profileId);
            int exe = profileDao.updateProfile(profile);
            if (exe == 0) {
                request.setAttribute("status", "Change Profile Error");
            } else {
                profile = profileDao.getUser(profile.getUsername(), profile.getPassword());
                request.setAttribute("status", "Change Profile Success");
            }
            dispatcher = request.getRequestDispatcher("/jsp/dashboard.jsp?menu=dashboard");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save profile error", e);
        }

    }

}
