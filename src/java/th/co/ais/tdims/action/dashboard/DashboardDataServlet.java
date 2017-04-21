/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.dashboard;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ReportDao;
import th.co.ais.tdims.model.MonthObject;
import th.co.ais.tdims.util.CharacterUtil;

public class DashboardDataServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(DashboardDataServlet.class);
    
    private final String  chartBar = "bar";
    private final String  chartDadar = "radar";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {            
            String simStatus = CharacterUtil.removeNull(req.getParameter("simStatus"));
            String chartType = CharacterUtil.removeNull(req.getParameter("chartType"));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            
            ReportDao reportDao = new ReportDao();
            
            if(chartType.equals(chartDadar)){// RADAR CHART
                Map data = reportDao.getGroupSimStatus();
                out.print(new Gson().toJson(data));
            }else{
                MonthObject simInMonths = reportDao.getCountSimTransaction(simStatus);           
                out.print(new Gson().toJson(simInMonths));
            }
            out.flush();
        } catch (Exception e) {
            logger.error("getDashboardDataServlet Error", e);
        }
    }

}
