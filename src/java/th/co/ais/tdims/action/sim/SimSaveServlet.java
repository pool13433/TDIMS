/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.util.CharacterUtil;

public class SimSaveServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimSaveServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info(" save sim ");
        try {
            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            String mobileNo = CharacterUtil.removeNull(request.getParameter("mobileNo"));
            String serialNo = CharacterUtil.removeNull(request.getParameter("serialNo"));
            String imsi = CharacterUtil.removeNull(request.getParameter("imsi"));
            String chargeType = CharacterUtil.removeNull(request.getParameter("chargeType"));
            String regionCode = CharacterUtil.removeNull(request.getParameter("regionCode"));
            String env = CharacterUtil.removeNull(request.getParameter("env"));
            String usageType = CharacterUtil.removeNull(request.getParameter("usageType"));
            String status = CharacterUtil.removeNull(request.getParameter("status"));
            String simId = CharacterUtil.removeNull(request.getParameter("simId"));
            String site = CharacterUtil.removeNull(request.getParameter("site"));
            String system = CharacterUtil.removeNull(request.getParameter("system"));

            Sim sim = new Sim();
            sim.setChargeType(chargeType);
            sim.setCreateBy(profile.getProfileId());
            sim.setEnviroment(env);
            sim.setImsi(imsi);
            sim.setMobileNo(mobileNo);
            sim.setRegionCode(regionCode);
            sim.setSerialNo(serialNo);
            sim.setSerialNo(serialNo);
            sim.setSimStatus(status);
            sim.setSite(site);
            sim.setUpdateBy(profile.getProfileId());
            sim.setUsageType(usageType);
            sim.setSystem(system);
            SimDao simDao = new SimDao();

            logger.info("simId ::==" + simId);
            int exec = 0;
            if (simId.equals("")) {
                logger.info(" create ");
                exec = simDao.createSim(sim);
            } else {
                logger.info(" update ");
                sim.setSimId(simId);
                exec = simDao.updateSim(sim);
            }
            MessageUI message = null;
            if (exec == 0) {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการบันทึกข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "บันทึกข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save sim error", e);
        }
        response.sendRedirect(request.getContextPath() + "/SimSearchServlet?menu=sim_search");
    }

}
