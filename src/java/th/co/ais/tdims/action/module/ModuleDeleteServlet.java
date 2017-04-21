/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ModuleDao;
import th.co.ais.tdims.util.CharacterUtil;

public class ModuleDeleteServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ModuleDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int exe = new ModuleDao().deleteModule(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("moduleId"))));
            request.setAttribute("message", "delete department success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete module error", e);
            request.setAttribute("message", "delete module error");
        }
        response.sendRedirect(request.getContextPath() + "/ModuleListServlet");
    }

}
