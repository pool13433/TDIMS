/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.testcase;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.TestcaseDao;
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.model.Profile;
import th.co.ais.tdims.model.Testcase;

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
            Profile profile2 = (Profile)request.getSession().getAttribute("USER_PROFILE");
            int profileNow = Integer.parseInt(profile2.getProfileId());
            String td = "";
            String issue = "";
            String date = "";
            String testcase = "";  
            String tester = "";
            String step = "";  
            String type = ""; 
            String detail = "";                        
            String project = "";
            String automate = "";
            String testcaseId = "";
            String systems = "";
            String owner = "";
            String env = "";
            String path = "";
            
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
				}else if("manual".equals(fieldname)){
					step = item.getString("UTF-8");
				}else if("type".equals(fieldname)){
					type = item.getString("UTF-8");
				
                                }else if("automate".equals(fieldname)){
					automate = item.getString("UTF-8");
				}else if("path".equals(fieldname)){
				
					path = item.getString("UTF-8");
	
			}
                        }
		}
                  

            Testcase data = new Testcase();
            data.setCreateBy(owner);
            data.setUpdateBy(owner);
            data.setDefectNo(td);
            data.setIssueNo(issue);
            data.setPathDir(path.replace("\\", "/"));
            data.setSystems(systems);
            data.setProjectId(project);
            data.setTestcaseDetails(detail);
            data.setTestcaseTitle(testcase);
            data.setEnviroment(env);
            data.setAutomate(automate);
            data.setStep(step);
            data.setType(type);
            data.setTestcaseId(testcaseId);
            data.setUpdateById(profileNow);
            
            TestcaseDao testcaseDao = new TestcaseDao();
            int exec = 0;
            logger.info("testcaseId ::=="+testcaseId);
            if(testcaseId.equals("")){
                logger.info(" create ");
               exec = testcaseDao.createTestcase(data);
            }else{
                logger.info(" update ");
                data.setTestcaseId(testcaseId);
               exec = testcaseDao.updateTestcase(data);
            }
            MessageUI message = null;
            if (exec == 0) {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการบันทึกข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการบันทึกข้อมูล", "บันทึกข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message); 
            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save testacse error", e);
            request.setAttribute("message", "save testacse error");
        }
        response.sendRedirect(request.getContextPath() + "/TestcaseSearchServlet?menu=searching&offset=&projectSelected=&type=&system=&env=&startDate=&toDate=&title=&details=&createBy=");
    }
    
}
