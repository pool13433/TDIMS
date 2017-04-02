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
import th.co.ais.tdims.model.Team;
import th.co.ais.tdims.util.CharacterUtil;


public class TeamFormServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(TeamFormServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TeamDao teamDao = new TeamDao();
            
            String teamId = CharacterUtil.removeNull(request.getParameter("teamId"));
            Team team = new Team();
            if(!teamId.equals("")){
                team = teamDao.getTeam(teamId);
            }

            request.setAttribute("team", team);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("team form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/team/team-form.jsp");
        dispatcher.forward(request, response);
    }
}
