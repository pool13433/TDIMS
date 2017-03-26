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
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Worawat
 */
public class UserSaveServlet extends HttpServlet {

  final static Logger logger = Logger.getLogger(UserSaveServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageForword = "/jsp/login.jsp";
        try {
            
            Profile profile2 = (Profile)request.getSession().getAttribute("USER_PROFILE");
            int profileNow = Integer.parseInt(profile2.getProfileId());
            String profileId = CharacterUtil.removeNull(request.getParameter("profileId"));
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
            profile.setCreateBy(profileNow);
            profile.setUpdateBy(profileNow);
            
            
             logger.info("testcaseId ::=="+profileId);
            if(profileId.equals("")){
                logger.info(" create ");
                profileDao.createProfile(profile);
            }else{
                logger.info(" update ");
                profile.setProfileId(profileId);
                profileDao.updateProfileAll(profile);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save register error", e);
        }
        response.sendRedirect(request.getContextPath() + "/UserListServlet");
    }

}