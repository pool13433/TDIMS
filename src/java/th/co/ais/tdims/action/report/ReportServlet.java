/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.action.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import th.co.ais.tdims.dao.ConfigDao;
import th.co.ais.tdims.dao.EnvironmentDao;
import th.co.ais.tdims.dao.ReportDao;
import th.co.ais.tdims.model.Config;
import th.co.ais.tdims.model.Environment;
import th.co.ais.tdims.model.Report;
import com.google.gson.Gson;

/**
 *
 * @author satan
 */
public class ReportServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ReportServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<String> reportGsonList = new ArrayList<String>();
        ArrayList<Report> reportList = new ArrayList<Report>();
        ArrayList<String> labelList = new ArrayList<String>();
        ArrayList<Integer> availableList = new ArrayList<Integer>();
        ArrayList<Integer> inUsedList = new ArrayList<Integer>();
        ArrayList<Integer> lostList = new ArrayList<Integer>();
        ArrayList<Integer> pendingList = new ArrayList<Integer>();
        ArrayList<Integer> unavailableList = new ArrayList<Integer>();
        try {
            ReportDao reportDao = new ReportDao();
            ConfigDao configDao = new ConfigDao();
            List<Config> chargeTypeList = configDao.getConfigList("CHARGE_TYPE");
            List<Config> usageTypeList = configDao.getConfigList("USAGE_TYPE");
            
            EnvironmentDao en = new EnvironmentDao();
            List<Environment> enList = en.getAllEnvirenment();
            Gson g = new Gson();
            for (Environment enEntry : enList) {
                String[] siteList = ( (String) enEntry.getEnvSite()).split(",");
                for (String site : siteList) {
                    for (Config chargeType : chargeTypeList) {
                        for (Config usageType : usageTypeList) {
                            Report report = reportDao.getSummaryReport(enEntry.getEnvCode(), "Site"+site, chargeType.getConName(), usageType.getConName());
                            reportList.add(report);
                            reportGsonList.add(g.toJson(report));
                            labelList.add(report.getEnv() + "_" + "SITE" + report.getSite() + ":" + report.getChargeType() + ":" + report.getUsageType());
                            availableList.add(report.getAvailable());
                            inUsedList.add(report.getInUse());
                            lostList.add(report.getLost());
                            pendingList.add(report.getPending());
                            unavailableList.add(report.getUnAvailable());
                        }
                    }
                }
            }
            request.setAttribute("reportList", reportList);
            request.setAttribute("reportGsonList", reportGsonList);
            request.setAttribute("labelList", g.toJson(labelList));
            request.setAttribute("availableList", g.toJson(availableList));
            request.setAttribute("inUsedList", g.toJson(inUsedList));
            request.setAttribute("lostList", g.toJson(lostList));
            request.setAttribute("pendingList", g.toJson(pendingList));
            request.setAttribute("unavailableList", g.toJson(unavailableList));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Report Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/report/report-all.jsp");
        dispatcher.forward(request, response);
    }
}
