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
import th.co.ais.tdims.dao.TestcastDao;
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
            TestcastDao testcaseDao = new TestcastDao();
            
             int exe = testcaseDao.deleteTestcase(Integer.parseInt(testcaseId));
            request.setAttribute("message", "delete testcase success");
            
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("testcase Error", e);
        }
        response.sendRedirect(request.getContextPath() + "/TestcaseListServlet");
    }

}