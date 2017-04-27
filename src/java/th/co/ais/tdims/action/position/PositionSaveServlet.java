/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.position;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.PositionDao;
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.model.Position;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

public class PositionSaveServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(PositionSaveServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Profile profile = (Profile) request.getSession().getAttribute("USER_PROFILE");
            Position position = new Position();
            position.setPosId(CharacterUtil.removeNull(request.getParameter("posId")));
            position.setPosName(CharacterUtil.removeNull(request.getParameter("posName")));
            position.setPosDesc(CharacterUtil.removeNull(request.getParameter("posDesc")));
            position.setDepId(CharacterUtil.removeNull(request.getParameter("department")));
            position.setCreateBy(profile.getProfileId());
            PositionDao positionDao = new PositionDao();

            logger.info("posId ::==" + position.getPosId());
            int exe = 0;
            if (position.getPosId().equals("")) {
                logger.info(" create ");
                exe = positionDao.createPosition(position);
            } else {
                logger.info(" update ");
                position.setUpdateBy(profile.getProfileId());
                exe = positionDao.updatePosition(position);
            }
            
            MessageUI message = null;
            if (exe == 0) {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการบันทึกข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "บันทึกข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save position error", e);
        }
        response.sendRedirect(request.getContextPath() + "/PositionListServlet");

    }

}
