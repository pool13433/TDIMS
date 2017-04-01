/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.model.Pagination;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Administrator
 */
public class SimSearchServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimSearchServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("doGet SimSearchServlet");
        RequestDispatcher dispatcher = null;
        try {
            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 50);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            String pageUrl = request.getContextPath() + "/SimSearchServlet?" + request.getQueryString();
            
            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            String menu = CharacterUtil.removeNull(request.getParameter("menu"));
            String cancelBooking = CharacterUtil.removeNull(request.getParameter("cancelBooking"));            
            String simStr = "";            
            String[] simSelected = request.getParameterValues("simSelected");
            if (simSelected != null && simSelected.length > 0) {
                for (int i = 0; i < simSelected.length; i++) {
                    if (i == 0) {
                        simStr = "'" + simSelected[i] + "'";
                    } else {
                        simStr = simStr + ",'" + simSelected[i] + "'";
                    }                    
                }
            }
            ConfigDao configDao = new ConfigDao();
            request.setAttribute("simSelected", simStr);
            request.setAttribute("usageTypeList", configDao.getConfigList("USAGE_TYPE"));
            request.setAttribute("chargeTypeList", configDao.getConfigList("CHARGE_TYPE"));
            request.setAttribute("siteList",configDao.getConfigList("SITE") );
            
            if ("booking".equals(menu) && !"Y".equals(cancelBooking)) {
                //projectList
                ProjectDao projectDao = new ProjectDao();
                request.setAttribute("projectList", projectDao.getProjectAll());
                //teamList
                TeamDao teamDao = new TeamDao();
                request.setAttribute("teamList", teamDao.getTeamAll());                
                
                dispatcher = request.getRequestDispatcher("/jsp/sim/sim-booking.jsp");
            } else {
                //Combo List
                ProjectDao projectDao = new ProjectDao();
                request.setAttribute("projectList", projectDao.getProjectAll());                
                request.setAttribute("systemList", configDao.getConfigList("SYSTEM"));
                request.setAttribute("envList", configDao.getConfigList("ENV"));
                request.setAttribute("simStatusList", configDao.getConfigList("SIM_STATUS"));
                SimDao simDao = new SimDao();
                Sim sim = new Sim();
                sim.setMobileNo(CharacterUtil.removeNull(request.getParameter("mobileNo")));   
                sim.setSerialNo(CharacterUtil.removeNull(request.getParameter("serialNo")));
                sim.setImsi(CharacterUtil.removeNull(request.getParameter("imsi")));
                sim.setChargeType(CharacterUtil.removeNull(request.getParameter("chargeType")));
                sim.setUsageType(CharacterUtil.removeNull(request.getParameter("usageType")));
                sim.setRegionCode(CharacterUtil.removeNull(request.getParameter("regionCode")));
                sim.setSystem(CharacterUtil.removeNull(request.getParameter("system")));
                sim.setEnviroment(CharacterUtil.removeNull(request.getParameter("env")));   
                sim.setSite(CharacterUtil.removeNull(request.getParameter("site")));
                sim.setSimStatus(CharacterUtil.removeNull(request.getParameter("status")));                
                sim.setUpdateBy(profile.getProfileId());
                sim.setCreateBy(profile.getProfileId());
                request.setAttribute("criteria", sim);
                
                if ("searching".equals(menu)) {
                    String sqlConditionBuilder = simDao.getConditionBuilder(sim);
                    List<Sim> simList = simDao.findSim(sim, limit, offset);
                    int countRecordAll = simDao.getCountSim(sqlConditionBuilder);
                    Pagination pagination = new Pagination(pageUrl, countRecordAll, limit, offset);
                    request.setAttribute("simList", simList);
                    request.setAttribute("pagination", pagination);
                } else {                    
                    if ("Y".equals(cancelBooking)) {                        
                        sim.setRemark("cancel booking");
                        sim.setSimId(simStr);
                        List<Sim> listSim = new ArrayList<Sim>();
                        listSim = simDao.findSimCancel(sim);
                        for (Sim s : listSim) {
                            sim = new Sim();
                            sim.setMobileNo(s.getMobileNo());
                            sim.setSystem(s.getSystem());
                            sim.setEnviroment(s.getEnviroment());
                            sim.setSite(s.getSite());
                            sim.setCreateBy(profile.getProfileId());
                            sim.setTeamId(s.getTeamId());
                            sim.setProjectId(s.getProjectId());
                            sim.setRemark("CANCEL");                            
                            sim.setSimStatus("Available");
                            
                            simDao.simSaveLog(sim);
                        }
                        simDao.resetBookingSim(sim, simStr);
                    }
                    request.setAttribute("simList", null);
                }
                request.setAttribute("cancelBooking", "");
                //request.setAttribute("searchBox", searching);
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
        logger.info("doPost SimSearchServlet -> sim booking");
        String simSelected = request.getParameter("simSelected");        
        try {            
            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 50);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            
            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            String assignTeam = CharacterUtil.removeNull(request.getParameter("team"));
            String email = CharacterUtil.removeNull(request.getParameter("emailContact"));
            String project = CharacterUtil.removeNull(request.getParameter("project"));
            String status = CharacterUtil.removeNull(request.getParameter("status"));
            String validDate = CharacterUtil.removeNull(request.getParameter("validDate"));
            String expireDate = CharacterUtil.removeNull(request.getParameter("expireDate"));
            String remark = CharacterUtil.removeNull(request.getParameter("remark"));
            
            SimDao simDao = new SimDao();            
            Sim sim = new Sim();
            sim.setSimId(simSelected);
            List<Sim> listSim = new ArrayList<Sim>();
            listSim = simDao.findSim(sim, limit, offset);
            for (Sim s : listSim) {
                sim = new Sim();
                sim.setMobileNo(s.getMobileNo());
                sim.setSystem(s.getSystem());
                sim.setEnviroment(s.getEnviroment());
                sim.setSite(s.getSite());
                sim.setCreateBy(profile.getProfileId());
                sim.setTeamId(assignTeam);
                sim.setEmailContact(email);
                sim.setValidDate(validDate);
                sim.setExpireDate(expireDate);
                sim.setProjectId(project);
                sim.setRemark(remark);                
                sim.setSimStatus(status);
                sim.setUpdateBy(profile.getProfileId());
                sim.setUpdateDate(expireDate);
                
                simDao.simSaveLog(sim);
            }
            simDao.bookSim(sim, simSelected);
            
            request.setAttribute("message", "booking sim success");
            
            simDao = new SimDao();            
            request.setAttribute("simList", simDao.getSimAll());
            //Combo List
            ProjectDao projectDao = new ProjectDao();
            request.setAttribute("projectList", projectDao.getProjectAll());
            ConfigDao configDao = new ConfigDao();
            request.setAttribute("systemList", configDao.getConfigList("SYSTEM"));
            request.setAttribute("envList", configDao.getConfigList("ENV"));
            request.setAttribute("simStatusList", configDao.getConfigList("SIM_STATUS"));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "save sim error");
            logger.error("sim booking error");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-search.jsp");
        dispatcher.forward(request, response);
    }
    
}
