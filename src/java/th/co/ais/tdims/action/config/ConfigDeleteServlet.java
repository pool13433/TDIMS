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
import th.co.ais.tdims.util.CharacterUtil;

public class ConfigDeleteServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ConfigDeleteServlet.class);
    private String message;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            int exe = new ConfigDao().deleteConfig(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("conId"))));
            message = "delete config success";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete sim error", e);
            message = "delete config error";
        }
        response.sendRedirect(request.getContextPath() + "/ConfigListServlet?message=".concat(message));
    }
}
