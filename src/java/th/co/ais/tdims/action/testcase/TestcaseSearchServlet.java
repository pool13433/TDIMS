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
            String searching = CharacterUtil.removeNull(request.getParameter("searchBox"));
            String projectSelected = CharacterUtil.removeNull(request.getParameter("projectSelected"));
            System.out.println("searching name = "+searching +"pId: "+ projectSelected);
            TestcastDao testcaseDao= new TestcastDao();
            
            if((!"".equals(searching) ||  !"".equals(projectSelected)) && !"ALL".equals(projectSelected)){
                if(!"".equals(projectSelected)){
                    tc.setProjectId(projectSelected.substring(0, 1));
                }                
                tc.setTestcaseDetails(searching);
                tc.setTestcaseTitle(searching);
                request.setAttribute("testcaseList", testcaseDao.findTestcase(tc));
            }else{
                request.setAttribute("testcaseList", testcaseDao.getTestcaseAll());
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
            request.setAttribute("searchBox", searching);

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
