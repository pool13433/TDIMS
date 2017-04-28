/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.testcase;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.TestcaseDao;
import th.co.ais.tdims.model.MessageUI;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Worawat
 */
public class TestcaseDeleteServlet extends HttpServlet {
    
  final static Logger logger = Logger.getLogger(TestcaseDeleteServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {            
            String testcaseId = CharacterUtil.removeNull(request.getParameter("testcaseId"));
            TestcaseDao testcaseDao = new TestcaseDao();
            
             int exec = testcaseDao.deleteTestcase(Integer.parseInt(testcaseId));
            MessageUI message = null;
            if (exec == 0) {
                message = new MessageUI(true, "สถานะการลบข้อมูล", "เกิดข้อผิดพลาดในขั้นตอนการลบข้อมูล", "danger");
            } else {
                message = new MessageUI(true, "สถานะการลบข้อมูล", "ลบข้อมูลสำเร็จ", "info");
            }
            request.getSession().setAttribute("MessageUI", message);
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("testcase Error", e);
        }
                response.sendRedirect(request.getContextPath() + "/TestcaseSearchServlet?menu=searching&offset=&projectSelected=&type=&system=&env=&startDate=&toDate=&title=&details=&createBy=");

    }

}