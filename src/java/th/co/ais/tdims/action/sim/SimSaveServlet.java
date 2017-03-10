/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author POOL_LAPTOP
 */
public class SimSaveServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimSaveServlet.class);

    private String DUMMY_USER = "1";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info(" save sim ");
        try {
            String mobileNo = CharacterUtil.removeNull(request.getParameter("mobileNo"));
            String serialNo = CharacterUtil.removeNull(request.getParameter("serialNo"));
            String imsi = CharacterUtil.removeNull(request.getParameter("imsi"));
            String chargeType = CharacterUtil.removeNull(request.getParameter("chargeType"));
            String regionCode = CharacterUtil.removeNull(request.getParameter("regionCode"));
            String env = CharacterUtil.removeNull(request.getParameter("env"));
            String site = CharacterUtil.removeNull(request.getParameter("site"));
            String usageType = CharacterUtil.removeNull(request.getParameter("usageType"));
            String assignTeam = CharacterUtil.removeNull(request.getParameter("assignTeam"));
            String email = CharacterUtil.removeNull(request.getParameter("email"));
            String project = CharacterUtil.removeNull(request.getParameter("project"));
            String status = CharacterUtil.removeNull(request.getParameter("status"));
            String validDate = CharacterUtil.removeNull(request.getParameter("validDate"));
            String expireDate = CharacterUtil.removeNull(request.getParameter("expireDate"));
            String remark = CharacterUtil.removeNull(request.getParameter("remark"));
            String simId = CharacterUtil.removeNull(request.getParameter("simId"));

            Sim sim = new Sim();
            sim.setTeamId(assignTeam);
            sim.setChargeType(chargeType);
            sim.setCreateBy(DUMMY_USER);
            sim.setCreateDate(expireDate);
            sim.setEmailContact(email);
            sim.setEnviroment(env);
            sim.setExpireDate(expireDate);
            sim.setImsi(imsi);
            sim.setMobileNo(mobileNo);
            sim.setProjectId(project);
            sim.setRegionCode(regionCode);
            sim.setRemark(remark);
            sim.setSerialNo(serialNo);
            sim.setSerialNo(serialNo);            
            sim.setSimStatus(status);
            sim.setSite(site);
            sim.setUpdateBy(DUMMY_USER);
            sim.setUpdateDate(expireDate);
            sim.setUsageType(usageType);
            sim.setValidDate(validDate);
            SimDao simDao = new SimDao();
            
            logger.info("simId ::=="+simId);
            if(simId.equals("")){
                logger.info(" create ");
                simDao.createSim(sim);
            }else{
                logger.info(" update ");
                sim.setSimId(simId);
                simDao.updateSim(sim);
            }
            request.setAttribute("message", "save sim success");
            
            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save sim error", e);
            request.setAttribute("message", "save sim error");
        }
        response.sendRedirect(request.getContextPath() + "/SimListServlet");
    }
    
}
