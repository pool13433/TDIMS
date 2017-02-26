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
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.PositionDao;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author POOL_LAPTOP
 */
public class RegisterServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            PositionDao positionDao = new PositionDao();
            request.setAttribute("positionList", positionDao.getPositionAll());
            request.setAttribute("genderList", new ConfigDao().getConfigList("PROFILE_GENDER"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("register error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageForword = "/jsp/login.jsp";
        try {
            String username = CharacterUtil.removeNull(request.getParameter("username"));
            String password = CharacterUtil.removeNull(request.getParameter("password"));
            String firstName = CharacterUtil.removeNull(request.getParameter("firstName"));
            String lastName = CharacterUtil.removeNull(request.getParameter("lastName"));
            String mobile = CharacterUtil.removeNull(request.getParameter("mobile"));
            String email = CharacterUtil.removeNull(request.getParameter("email"));
            String position = CharacterUtil.removeNull(request.getParameter("position"));
            String gender = CharacterUtil.removeNull(request.getParameter("gender"));

            ProfileDao profileDao = new ProfileDao();

            Profile profile = new Profile();
            profile.setEmail(email);
            profile.setFirstName(firstName);
            profile.setGender(gender);
            profile.setLastName(lastName);
            profile.setMobile(mobile);
            profile.setPassword(password);
            profile.setPosition(position);
            profile.setStatus("MEMBER");
            profile.setUsername(username);
            profile.setCreateBy(1);
            profile.setUpdateBy(1);
            int exe = profileDao.createProfile(profile);
            if (exe == 0) {
                request.setAttribute("status", "Register Error");
                pageForword = "/jsp/register.jsp";
            } else {
                request.setAttribute("status", "Register Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save register error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageForword);
        dispatcher.forward(request, response);
    }

}
