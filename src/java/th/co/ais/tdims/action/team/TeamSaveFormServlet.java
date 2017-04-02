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
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.model.Team;
import th.co.ais.tdims.util.CharacterUtil;

public class TeamSaveFormServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(TeamSaveFormServlet.class);
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Profile profile = (Profile)request.getSession().getAttribute("USER_PROFILE");
            Team team = new Team();
            team.setTeamId(CharacterUtil.removeNull(request.getParameter("teamId")));
            team.setTeamName(CharacterUtil.removeNull(request.getParameter("teamName")));
            team.setTeamEmail(CharacterUtil.removeNull(request.getParameter("teamEmail")));
            team.setCreateBy(profile.getProfileId());
            TeamDao teamDao = new TeamDao();
            
            logger.info("teamId ::=="+ team.getTeamId());
            
            if(team.getTeamId().equals("")){
                logger.info(" create ");
                teamDao.createTeam(team);
            }else{
                logger.info(" update ");
                team.setUpdateBy(profile.getProfileId());
                teamDao.updateTeam(team);
            }
            request.setAttribute("message", "save Team success");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save Team error", e);
            request.setAttribute("message", "save Team error");
        }
        response.sendRedirect(request.getContextPath() + "/TeamListServlet");
    }
    
    
}
