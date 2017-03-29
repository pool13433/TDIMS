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
import th.co.ais.tdims.model.Testcase;
import th.co.ais.tdims.util.CharacterUtil;

public class TestcaseSearchServlet extends HttpServlet {
final static Logger logger = Logger.getLogger(TestcaseSearchServlet.class);

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Testcase tc = new Testcase();
        logger.debug("TestcaseSearchServlet");
        String dirFile = request.getParameter("pathDir");
        ProjectDao projectCombo = new ProjectDao();
        try {
            //DropdownList
            request.setAttribute("projectCombo", projectCombo.getProjectComboList());
            String menu = CharacterUtil.removeNull(request.getParameter("menu"));
            String searching = CharacterUtil.removeNull(request.getParameter("searchBox"));
            request.setAttribute("searchBox", searching);
            String projectId = CharacterUtil.removeNull(request.getParameter("projectSelected"));
            request.setAttribute("projectSelected", projectId);
            ConfigDao configDao = new ConfigDao();
            request.setAttribute("systemList", configDao.getConfigList("SYSTEM"));
            request.setAttribute("envList", configDao.getConfigList("ENV"));
            request.setAttribute("ownerList", new ProfileDao().getAllUser());
            
            String system = CharacterUtil.removeNull(request.getParameter("system"));
            request.setAttribute("system", system);
            String createDate = CharacterUtil.removeNull(request.getParameter("createDate"));
            request.setAttribute("createDate", createDate);
            String createBy = CharacterUtil.removeNull(request.getParameter("createBy"));
            request.setAttribute("createBy", createBy);
            String issueNo = CharacterUtil.removeNull(request.getParameter("issueNo"));
            request.setAttribute("issueNo", issueNo);
            String defectNo = CharacterUtil.removeNull(request.getParameter("defectNo"));
            request.setAttribute("defectNo", defectNo);
            String env = CharacterUtil.removeNull(request.getParameter("env"));
            request.setAttribute("env", env);
            String step = CharacterUtil.removeNull(request.getParameter("step"));
            request.setAttribute("step", step);
            
            TestcastDao testcaseDao= new TestcastDao();
            
            if("searching".equals(menu)){
                System.out.println("menu ="+menu);
                tc.setProjectId(projectId);
                tc.setSystems(system);
                tc.setCreateDate(createDate);
                tc.setCreateBy(createBy);
                tc.setIssueNo(issueNo);
                tc.setDefectNo(defectNo);
                tc.setEnviroment(env);
                tc.setTestcaseDetails(searching);
                tc.setTestcaseTitle(searching);
                tc.setStep(step);
                request.setAttribute("testcaseList", testcaseDao.findTestcase(tc));
            }else{
                //request.setAttribute("testcaseList", testcaseDao.getTestcaseAll());
                request.setAttribute("testcaseList", null);
            }
            
            System.out.println(" path : "+dirFile);
            if(dirFile!= null){
                Desktop desktop = Desktop.getDesktop();
                File dirToOpen = null;
                try {
                    dirToOpen = new File(dirFile);
                    desktop.open(dirToOpen);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("KnowledgeOpenDir Error", e);
                }
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("TestcaseSearch Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/testcase/testcase-search.jsp");
        dispatcher.forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
}
