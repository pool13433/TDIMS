/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package th.co.ais.tdims.action.team;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.util.CharacterUtil;

public class TeamDeleteServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(TeamDeleteServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            int exe = new TeamDao().deleteTeam(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("teamId"))));
            request.setAttribute("message", "delete team success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete team error", e);
            request.setAttribute("message", "delete team error");
        }
        response.sendRedirect(request.getContextPath() + "/TeamListServlet");
        
    }



}
