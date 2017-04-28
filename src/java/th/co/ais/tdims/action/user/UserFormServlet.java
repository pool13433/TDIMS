/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.user;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.DepartmentDao;
import th.co.ais.tdims.dao.EnvironmentDao;
import th.co.ais.tdims.dao.ModuleDao;
import th.co.ais.tdims.dao.PositionDao;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.TestcaseDao;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.model.Testcase;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Worawat
 */
public class UserFormServlet extends HttpServlet {


    final static Logger logger = Logger.getLogger(UserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProfileDao pro = new ProfileDao();
            String profileId = CharacterUtil.removeNull(request.getParameter("profileId"));
            Profile profile = null;
            if(profileId.equals("")){ // NEW
                profile = new Profile();
                logger.info("if");
            }else{
               profile = pro.getUserBYId(Integer.parseInt(profileId));
                logger.info("esle");
            }
               PositionDao positionDao = new PositionDao();
            request.setAttribute("positionList", positionDao.getPositionAll());
            request.setAttribute("genderList", new ConfigDao().getConfigList("PROFILE_GENDER"));
            request.setAttribute("roleList", new ConfigDao().getConfigList("ROLE"));
            request.setAttribute("depList", new DepartmentDao().getAllDepartment());
            request.setAttribute("profile", profile);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("testcase form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/user-form.jsp");
        dispatcher.forward(request, response);
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           
            String json = new Gson().toJson(new PositionDao().getPositionByDep(request.getParameter("depId")));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            System.out.println(" JSON :"+json);
            
        } catch (Exception e) {
            logger.error("doPost UsernameFormServlet "+e.getMessage());
        }
        
    }

}
