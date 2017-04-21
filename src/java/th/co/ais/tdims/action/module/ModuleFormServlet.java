/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.module;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ModuleDao;
import th.co.ais.tdims.dao.TeamDao;
import th.co.ais.tdims.model.Module;
import th.co.ais.tdims.util.CharacterUtil;

public class ModuleFormServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(ModuleFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            
            String moduleId = CharacterUtil.removeNull(request.getParameter("moduleId"));
            Module module = new Module();
            
            if(!moduleId.equals("")){ // Update
                module = new ModuleDao().getModule(moduleId);
            }
            request.setAttribute("module", module);
            request.setAttribute("teamList", new TeamDao().getTeamAll());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("module form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/module/module-form.jsp");
        dispatcher.forward(request, response);
    }
    

}
