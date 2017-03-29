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
import th.co.ais.tdims.util.CharacterUtil;

public class SimDeleteServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(SimListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String simId = CharacterUtil.removeNull(request.getParameter("simId"));

            int exe = new SimDao().deleteSim(Integer.parseInt(simId));
            request.setAttribute("message", "delete sim success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete sim error", e);
            request.setAttribute("message", "delete sim error");
        }
        response.sendRedirect(request.getContextPath() + "/SimListServlet");
    }
}
