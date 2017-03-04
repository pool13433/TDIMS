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
import th.co.ais.tdims.model.Position;
import th.co.ais.tdims.util.CharacterUtil;

public class PositionSaveServlet extends HttpServlet {
    
    final static Logger logger = Logger.getLogger(PositionSaveServlet.class);
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Position position = new Position();
            position.setPosId(CharacterUtil.removeNull(request.getParameter("posId")));
            position.setPosName(CharacterUtil.removeNull(request.getParameter("posName")));
            position.setPosDesc(CharacterUtil.removeNull(request.getParameter("posDesc")));
            position.setDepId(CharacterUtil.removeNull(request.getParameter("department")));

            PositionDao positionDao = new PositionDao();
            
            logger.info("posId ::=="+position.getPosId());
            
            if(position.getPosId().equals("")){
                logger.info(" create ");
                positionDao.createPosition(position);
            }else{
                logger.info(" update ");
                positionDao.updatePosition(position);
            }
            request.setAttribute("message", "save position success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save position error", e);
        }
        response.sendRedirect(request.getContextPath() + "/PositionListServlet");
        
        
    }

}
