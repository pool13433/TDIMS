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

public class LoginServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = CharacterUtil.removeNull(request.getParameter("username"));
        String password = CharacterUtil.removeNull(request.getParameter("password"));
        try {
            logger.info(" username ::==" + username);
            logger.info(" password ::==" + password);

            ProfileDao dao = new ProfileDao();
            Profile user = dao.getUser(username, password);
            if (user == null) {
                request.setAttribute("status", "cannot find user in system");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("USER_PROFILE", user);
                request.setAttribute("status", "login success");
                response.sendRedirect(request.getContextPath()+"/jsp/dashboard.jsp?menu=dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("login errror", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
