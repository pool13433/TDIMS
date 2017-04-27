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
import th.co.ais.tdims.util.CharacterUtil;

public class PositionDeleteServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(PositionDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int exe = new PositionDao().deletePosition(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("posId"))));
            MessageUI message = null;
            if (exe == 0) {
                message = new MessageUI(true, "สถานะการลบข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการลบข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการลบข้อมูล", "ลบข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete position error", e);
        }
        response.sendRedirect(request.getContextPath() + "/PositionListServlet");
    }
}
