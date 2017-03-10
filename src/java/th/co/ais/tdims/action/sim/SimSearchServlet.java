/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Administrator
 */
public class SimSearchServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(SimSearchServlet.class);
    private String DUMMY_USER = "1";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("SimSearchServlet");
        RequestDispatcher dispatcher = null;
        try {
            String menu = CharacterUtil.removeNull(request.getParameter("menu"));
            if("booking".equals(menu)){
            //projectList
            ProjectDao projectDao = new ProjectDao();
            request.setAttribute("projectList", projectDao.getProjectAll());
            //teamList
            TeamDao teamDao = new TeamDao();
            request.setAttribute("teamList", teamDao.getTeamAll());
            
            String[] simSelected = request.getParameterValues("simSelected");
            String simStr = ""; 
            if(simSelected.length > 0){
                for(int i=0 ; i<simSelected.length ; i++){
                    if(i==0){
                        simStr = "'"+simSelected[i]+"'";
                    }else{
                        simStr = simStr+",'"+simSelected[i]+"'";
                    }                
                }
            }
            
            request.setAttribute("simSelected", simStr);
            
            dispatcher = request.getRequestDispatcher("/jsp/sim/sim-booking.jsp");
            }else{
                String searching = CharacterUtil.removeNull(request.getParameter("searchBox"));
                SimDao simDao = new SimDao();
                if(!"".equals(searching)){
                    request.setAttribute("simList", simDao.findSim(searching));
                }else{
                    request.setAttribute("simList", simDao.getSimAll());
                }

                request.setAttribute("searchBox", searching);
                dispatcher = request.getRequestDispatcher("/jsp/sim/sim-search.jsp");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ProjectSearch Error", e);
        }
        
        dispatcher.forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("sim booking");
        String simSelected = request.getParameter("simSelected"); 
        try {      
            
            String assignTeam = CharacterUtil.removeNull(request.getParameter("team"));
            String subTeam[] = assignTeam.split("|");
            if(subTeam.length > 1){
                assignTeam = subTeam[1];
            }
            String email = CharacterUtil.removeNull(request.getParameter("emailContact"));
            String project = CharacterUtil.removeNull(request.getParameter("project"));
            String status = CharacterUtil.removeNull(request.getParameter("status"));
            String validDate = CharacterUtil.removeNull(request.getParameter("validDate"));
            String expireDate = CharacterUtil.removeNull(request.getParameter("expireDate"));
            String remark = CharacterUtil.removeNull(request.getParameter("remark"));

            Sim sim = new Sim();
            sim.setTeamId(assignTeam);
            sim.setEmailContact(email);
            sim.setValidDate(validDate);
            sim.setExpireDate(expireDate);
            sim.setProjectId(project);
            sim.setRemark(remark);          
            sim.setSimStatus(status);
            sim.setUpdateBy(DUMMY_USER);
            sim.setUpdateDate(expireDate);            
            
            SimDao simDao = new SimDao();   
            simDao.bookSim(sim, simSelected);
            request.setAttribute("message", "booking sim success");
            
            simDao = new SimDao();            
            request.setAttribute("simList", simDao.getSimAll());
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "save sim error");
            logger.error("sim booking error");
        }
       RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-search.jsp");
       dispatcher.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
