/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.testcase;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.TestcastDao;
import th.co.ais.tdims.model.Testcase;
import th.co.ais.tdims.util.CharacterUtil;
import th.co.ais.tdims.util.FileUploadUtil;

/**
 *
 * @author Worawat
 */
public class TestcaseAddServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(TestcaseAddServlet.class);

    private String DUMMY_USER = "1";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info(" save testcase ");
        try {
            //String file = CharacterUtil.removeNull(request.getParameter("file"));
            Part file = request.getPart("file");
            String filepath = FileUploadUtil.uploadFile(request, (FileItem) file, "uploads");
            String td = CharacterUtil.removeNull(request.getParameter("td"));
            String issue = CharacterUtil.removeNull(request.getParameter("issue"));
            String date = CharacterUtil.removeNull(request.getParameter("date"));
            String testcase = CharacterUtil.removeNull(request.getParameter("testcase"));            
            String deteil = CharacterUtil.removeNull(request.getParameter("detail"));                        
            String project = CharacterUtil.removeNull(request.getParameter("project"));
            String testcaseId = CharacterUtil.removeNull(request.getParameter("testcaseId"));
            String system = CharacterUtil.removeNull(request.getParameter("systems"));
            String owner = CharacterUtil.removeNull(request.getParameter("tester"));
            String env = CharacterUtil.removeNull(request.getParameter("env"));        

            Testcase data = new Testcase();
            data.setUserId(owner);
            data.setDefectNo(td);
            data.setIssueNo(issue);
            data.setPathDir(filepath);
            data.setSystems(system);
            data.setProjectId(project);
            data.setTestcaseDetails(deteil);
            data.setTestcaseTitle(testcase);
            data.setEnviroment(env);
            data.setTestcaseId(testcaseId);
            
            TestcastDao testcaseDao = new TestcastDao();
            
            logger.info("testcaseId ::=="+testcaseId);
            if(testcaseId.equals("")){
                logger.info(" create ");
                testcaseDao.createTestcase(data);
            }else{
                logger.info(" update ");
                data.setTestcaseId(testcaseId);
                testcaseDao.updateTestcase(data);
            }
            request.setAttribute("message", "save sim success");
            
            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save sim error", e);
            request.setAttribute("message", "save sim error");
        }
        response.sendRedirect(request.getContextPath() + "/TestcaseListServlet");
    }
    
}
