
package th.co.ais.tdims.action.sim;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.SimDao;
import th.co.ais.tdims.util.CharacterUtil;

public class SimSearchServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(SimSearchServlet.class);
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        logger.debug("ProjectSearchServlet");
        try {
            String searching = CharacterUtil.removeNull(request.getParameter("searchBox"));
            System.out.println("searching name = "+searching);
            SimDao simDao = new SimDao();
            
            if(!"".equals(searching)){
                request.setAttribute("simList", simDao.findSim(searching));
            }else{
                request.setAttribute("simList", simDao.getSimAll());
            }
            
            request.setAttribute("searchBox", searching);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ProjectSearch Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sim/sim-list.jsp");
        dispatcher.forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}