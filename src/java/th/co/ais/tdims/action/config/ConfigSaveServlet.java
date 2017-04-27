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
            
            if(config.getConId().equals("")){
                logger.info(" create ");
                configDao.createConfig(config);
            }else{
                logger.info(" update ");
                configDao.updateConfig(config);
            }
            message = "save config success";

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save config error", e);
            message = "save config error";
        }
        response.sendRedirect(request.getContextPath() + "/ConfigListServlet?message=".concat(message));
    }

}
