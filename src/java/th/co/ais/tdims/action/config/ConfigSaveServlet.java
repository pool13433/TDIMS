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
import th.co.ais.tdims.model.Config;
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.util.CharacterUtil;

public class ConfigSaveServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ConfigSaveServlet.class);
    private String message;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {

            Config config = new Config();
            config.setConId(CharacterUtil.removeNull(request.getParameter("conId")));
            config.setConCode(CharacterUtil.removeNull(request.getParameter("conCode")));
            config.setConName(CharacterUtil.removeNull(request.getParameter("conName")));
            config.setConValue(CharacterUtil.removeNull(request.getParameter("conValue")));
            ConfigDao configDao = new ConfigDao();
            
            logger.info("conId ::=="+config.getConId());
            int exe = 0;
            if(config.getConId().equals("")){
                logger.info(" create ");
                exe = configDao.createConfig(config);
            }else{
                logger.info(" update ");
                exe = configDao.updateConfig(config);
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
            logger.error("save config error", e);
        }
        response.sendRedirect(request.getContextPath() + "/ConfigListServlet");
    }

}
