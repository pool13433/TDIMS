/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package th.co.ais.tdims.action.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.util.CharacterUtil;

public class ConfigDeleteServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ConfigDeleteServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            int exe = new ConfigDao().deleteConfig(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("conId"))));
            MessageUI message = null;
            if (exe == 0) {
                message = new MessageUI(true, "สถานะการลบข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการลบข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการลบข้อมูล", "ลบข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete sim error", e);
        }
        response.sendRedirect(request.getContextPath() + "/ConfigListServlet");
    }
}
