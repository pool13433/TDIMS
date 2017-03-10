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
import th.co.ais.tdims.util.CharacterUtil;

public class PositionDeleteServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(PositionDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int exe = new PositionDao().deletePosition(Integer.parseInt(CharacterUtil.removeNull(request.getParameter("posId"))));
            request.setAttribute("message", "delete position success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete position error", e);
            request.setAttribute("message", "delete position error");
        }
        response.sendRedirect(request.getContextPath() + "/PositionListServlet");
    }
}
