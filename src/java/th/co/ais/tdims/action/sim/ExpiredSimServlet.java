/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.model.Config;
import th.co.ais.tdims.model.ExpiredSim;
import th.co.ais.tdims.model.Pagination;
import th.co.ais.tdims.model.Team;
import th.co.ais.tdims.util.CharacterUtil;

public class ExpiredSimServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ExpiredSimServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int team = CharacterUtil.removeNullTo(request.getParameter("team"), 0);
            String system = request.getParameter("system") != null ? (String)request.getParameter("system") : null;
            String mobile = request.getParameter("mobile") != null ? (String)request.getParameter("mobile") : null;
            SimDao simDao = new SimDao();
            TeamDao teamDao = new TeamDao();
            ConfigDao configDao = new ConfigDao();
            List<Team> teamList = teamDao.getTeamAll();
            Team chooseTeam = teamDao.getTeam(team);
            List<Config> systemList = configDao.getConfigList("SYSTEM");
            
            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 300);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            List<ExpiredSim> es = simDao.getExpiredSim(team, system, mobile, limit, offset);
            
            String pageUrl = request.getContextPath() + "/ExpiredSimServlet?"+request.getQueryString();
            int countRecordAll = simDao.getCountExpiredSim(team, system, mobile);
            Pagination pagination = new Pagination(pageUrl,countRecordAll, limit, offset);
            request.setAttribute("team", team);
            request.setAttribute("teamName", chooseTeam.getTeamName());
            request.setAttribute("system", system);
            request.setAttribute("simList", es);
            request.setAttribute("teamList", teamList);
            request.setAttribute("systemList", systemList);
            request.setAttribute("pagination", pagination);
            request.setAttribute("isFromSendMail", "N");  
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ExpiredSimList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/expired-list.jsp");
        dispatcher.forward(request, response);
    }
}
