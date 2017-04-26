/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.testcase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 *
 * @author Worawat
 */
public class TestcaseFormServlet extends HttpServlet {

 
    final static Logger logger = Logger.getLogger(TestcaseFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ConfigDao configDao = new ConfigDao();
            ProjectDao projecDao = new ProjectDao();
            TestcastDao testcaseDao = new TestcastDao();
            
           String testcaseId = CharacterUtil.removeNull(request.getParameter("testcaseId"));
            Testcase testcase = null;
            if(testcaseId.equals("")){ // NEW
                testcase = new Testcase();
            }else{
               testcase = testcaseDao.getTestcaseAllForm(Integer.parseInt(testcaseId));
            }
            request.setAttribute("chargeTypeList", configDao.getConfigList("CHARGE_TYPE"));
            request.setAttribute("usageTypeList", configDao.getConfigList("USAGE_TYPE"));
            request.setAttribute("simStatusList", configDao.getConfigList("SIM_STATUS"));
            request.setAttribute("systemList", configDao.getConfigList("SYSTEM"));
            request.setAttribute("ownerList", new ProfileDao().getAllUser());
             request.setAttribute("typeList", configDao.getConfigList("TC_TYPE"));
            request.setAttribute("envList", configDao.getConfigList("ENV"));
            request.setAttribute("projectList", projecDao.getProjectAll());
            request.setAttribute("totalStep", (Integer.parseInt(testcase.getStep())+Integer.parseInt(testcase.getAutomate())));
            System.out.println("++++++++++++++++++++++++++++++"+testcase.getCreateDate());
            Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(testcase.getCreateDate());
            SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
            String date2 = format.format(date1);
            testcase.setCreateDate(date2);
            request.setAttribute("testcase", testcase);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("testcase form error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/testcase/testcase-form.jsp");
        dispatcher.forward(request, response);
    }

}
