/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.team;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.TeamDao;


public class TeamListServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(TeamListServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {            
            request.setAttribute("teamList", new TeamDao().getTeamAll());
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("team list Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/team/team-list.jsp");
        dispatcher.forward(request, response);
    }
}
