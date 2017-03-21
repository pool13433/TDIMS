/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.model.ExpiredSim;
import th.co.ais.tdims.util.SendMailHandle;

/**
 *
 * @author satan
 */
public class SendMailServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(SendMailServlet.class);
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            ConfigDao config = new ConfigDao();
            Map<String, String> mailMap = config.getConfigMap("MAIL");
            String userName = (String)(mailMap.get("MAIL_USER"));
            String password = (String)(mailMap.get("MAIL_PASSWORD"));
            String subject = (String)(mailMap.get("MAIL_SUBJECT"));
            String content = (String)(mailMap.get("MAIL_CONTENT"));
            //String userName = config.getConfigMap();
            SendMailHandle sm = new SendMailHandle(userName, password);
            String[] expireds = request.getParameterValues("expireds");
            for (String expired : expireds) {
                sm.sendMail(userName, expired, subject, content);
            }
            
            SimDao simDao = new SimDao();
            List<ExpiredSim> es = simDao.getExpiredSim();
            List<Map<String, Object>> fullExpiredSimList = new ArrayList<Map<String, Object>>();
            if (es != null && es.size() > 0) {
                String teamTemp = null;
                String systemTemp = null;
                Map<String, Object> simMap = new HashMap<String, Object>();
                List<ExpiredSim> newList = new ArrayList<ExpiredSim>();
                for (ExpiredSim sim : es) {
                    if (teamTemp == null) {
                        teamTemp = sim.getTeamName();
                        simMap.put("team", teamTemp);
                    }
                    if (systemTemp == null) {
                        systemTemp = sim.getSystem();
                        simMap.put("system", systemTemp);
                    }
                    
                    if (!sim.getTeamName().equals(teamTemp)) {
                        simMap.put("subSimList", newList);
                        fullExpiredSimList.add(simMap);
                        simMap = new HashMap<String, Object>();
                        newList = new ArrayList<ExpiredSim>();
                        teamTemp = sim.getTeamName();
                        simMap.put("team", teamTemp);
                        simMap.put("system", systemTemp);
                    }
                    if (!sim.getTeamName().equals(teamTemp) && !sim.getSystem().equals(systemTemp)) {
                        simMap.put("subSimList", newList);
                        fullExpiredSimList.add(simMap);
                        simMap = new HashMap<String, Object>();
                        newList = new ArrayList<ExpiredSim>();
                        systemTemp = sim.getSystem();
                        simMap.put("team", teamTemp);
                        simMap.put("system", systemTemp);
                    }
                    
                    newList.add(sim);
                }
                simMap.put("subSimList", newList);
                fullExpiredSimList.add(simMap);
            }
            request.setAttribute("simList", fullExpiredSimList);
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ExpiredSimList Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/expired-list.jsp");
        dispatcher.forward(request, response);
    }
}
