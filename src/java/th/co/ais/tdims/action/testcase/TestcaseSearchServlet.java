/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.testcase;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.EnvironmentDao;
import th.co.ais.tdims.dao.ProfileDao;
import th.co.ais.tdims.dao.ProjectDao;
import th.co.ais.tdims.dao.TestcastDao;
import th.co.ais.tdims.model.Pagination;
import th.co.ais.tdims.model.Testcase;
import th.co.ais.tdims.util.CharacterUtil;

public class TestcaseSearchServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(TestcaseSearchServlet.class);

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Testcase tc = new Testcase();
        logger.debug("doGet TestcaseSearchServlet");
        ProjectDao projectCombo = new ProjectDao();
        try {
            //DropdownList
            request.setAttribute("projectCombo", projectCombo.getProjectComboList());
            String menu = CharacterUtil.removeNull(request.getParameter("menu"));
            String details = CharacterUtil.removeNull(request.getParameter("details"));
            request.setAttribute("details", details);
            String title = CharacterUtil.removeNull(request.getParameter("title"));
            request.setAttribute("title", title);
            String projectId = CharacterUtil.removeNull(request.getParameter("projectSelected"));
            request.setAttribute("projectSelected", projectId);
            ConfigDao configDao = new ConfigDao();
            request.setAttribute("systemList", configDao.getConfigList("SYSTEM"));
            request.setAttribute("envList", configDao.getConfigList("ENV"));
            request.setAttribute("ownerList", new ProfileDao().getAllUser());            
            String system = CharacterUtil.removeNull(request.getParameter("system"));
            request.setAttribute("system", system);
            request.setAttribute("typeList", configDao.getConfigList("TC_TYPE"));
            String startDate = CharacterUtil.removeNull(request.getParameter("startDate"));
            request.setAttribute("startDate", startDate);
            String toDate = CharacterUtil.removeNull(request.getParameter("toDate"));
            request.setAttribute("toDate", toDate);
            String createBy = CharacterUtil.removeNull(request.getParameter("createBy"));
            request.setAttribute("createBy", createBy);
            String env = CharacterUtil.removeNull(request.getParameter("env"));
            request.setAttribute("env", env);
            String type = CharacterUtil.removeNull(request.getParameter("type"));
            request.setAttribute("type", type);
            int limit = CharacterUtil.removeNullTo(request.getParameter("limit"), 5);
            int offset = CharacterUtil.removeNullTo(request.getParameter("offset"), 0);
            TestcastDao testcaseDao= new TestcastDao();
            
            if("searching".equals(menu)){
                tc.setProjectId(projectId);
                tc.setSystems(system);
                tc.setCreateDate(startDate+"|"+toDate);
                tc.setCreateBy(createBy);
                tc.setEnviroment(env);
                tc.setTestcaseDetails(details);
                tc.setTestcaseTitle(title);
                tc.setType(type);
                String pageUrl = request.getContextPath() + "/TestcaseSearchServlet?" + request.getQueryString();
                String sqlConditionBuilder = testcaseDao.getConditionBuilder(tc);
                int countRecordAll = testcaseDao.getCountTestcase(sqlConditionBuilder);
                request.setAttribute("testcaseList", testcaseDao.findTestcase(tc, limit, offset));
                Pagination pagination = new Pagination(pageUrl, countRecordAll, limit, offset);
                request.setAttribute("pagination", pagination);
            }else{
                //request.setAttribute("testcaseList", testcaseDao.getTestcaseAll());
                request.setAttribute("testcaseList", null);
            }
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/testcase/testcase-search.jsp");
            dispatcher.forward(request, response);
            
            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("TestcaseSearch Error", e);
        }
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        logger.info("doPost TestcaseSearchServlet");
        try {
            String dirFile = request.getParameter("pathDir");
            //System.out.println("open path :"+dirFile);
            if(dirFile!= null){
                Desktop desktop = Desktop.getDesktop();
                File dirToOpen = null;
                try {
                    dirToOpen = new File(dirFile);
                    desktop.open(dirToOpen);
                } catch (Exception e) {                   
                    logger.error("TestcaseSearchServlet Error"+ e.getMessage());
                }
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
