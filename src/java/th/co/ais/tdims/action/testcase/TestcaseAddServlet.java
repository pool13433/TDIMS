/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.testcase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
       
            String td = "";
            String issue = "";
            String date = "";
            String testcase = "";  
            String tester = ""; 
            String detail = "";                        
            String project = "";
            String testcaseId = "";
            String systems = "";
            String owner = "";
            String env = "";
            String file = "";
            
           List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldname = item.getFieldName();
				if("td".equals(fieldname)){
					td = item.getString("UTF-8");
				}else if("issue".equals(fieldname)){
					issue = item.getString("UTF-8");
				}else if("date".equals(fieldname)){
					date = item.getString("UTF-8");
				}else if("env".equals(fieldname)){
					env = item.getString("UTF-8");
				}else if("tester".equals(fieldname)){
					owner = item.getString("UTF-8");
				}else if("systems".equals(fieldname)){
					systems = item.getString("UTF-8");
				}else if("testcaseId".equals(fieldname)){
					testcaseId = item.getString("UTF-8");
				}else if("project".equals(fieldname)){
					project = item.getString("UTF-8");
				}else if("detail".equals(fieldname)){
					detail = item.getString("UTF-8");
				}else if("testcase".equals(fieldname)){
					testcase = item.getString("UTF-8");
				}
			}else{
				
					file = FileUploadUtil.uploadFile(request, item, "uploads");
	
			}
		}
                  

            Testcase data = new Testcase();
            data.setUserId(owner);
            data.setDefectNo(td);
            data.setIssueNo(issue);
            data.setPathDir(file);
            data.setSystems(systems);
            data.setProjectId(project);
            data.setTestcaseDetails(detail);
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
            request.setAttribute("message", "save testacse success");
            
            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save testacse error", e);
            request.setAttribute("message", "save testacse error");
        }
        response.sendRedirect(request.getContextPath() + "/TestcaseListServlet");
    }
    
}
