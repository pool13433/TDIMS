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
import th.co.ais.tdims.model.Module;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.util.CharacterUtil;

public class ModuleSaveServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ModuleDeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            Profile profile = (Profile)request.getSession().getAttribute("USER_PROFILE");
            Module module = new Module();
            module.setId(CharacterUtil.removeNull(request.getParameter("moduleId")));
            module.setTeamId(CharacterUtil.removeNull(request.getParameter("teamId")));
            module.setModuleName(CharacterUtil.removeNull(request.getParameter("moduleName")));
            module.setModuleDesc(CharacterUtil.removeNull(request.getParameter("moduleDesc")));
            module.setCreateBy(profile.getProfileId());
            ModuleDao moduleDao = new ModuleDao();
            
            logger.info("depId ::=="+module.getId());
            
            if(module.getId().equals("")){
                logger.info(" create ");
                moduleDao.createModule(module);
            }else{
                logger.info(" update ");
                module.setUpdateBy(profile.getProfileId());
                moduleDao.updateModule(module);
            }
            request.setAttribute("message", "save Module success");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save Module error", e);
            request.setAttribute("message", "save Module error");
        }
        response.sendRedirect(request.getContextPath() + "/ModuleListServlet");
    }
    
    
}
