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
import th.co.ais.tdims.model.ReportSim;
import com.google.gson.Gson;
import java.util.Calendar;
import th.co.ais.tdims.model.ReportTestCase;

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
        ArrayList<ReportSim> reportList = new ArrayList<ReportSim>();
        ArrayList<String> labelList = new ArrayList<String>();
        ArrayList<Integer> availableList = new ArrayList<Integer>();
        ArrayList<Integer> inUsedList = new ArrayList<Integer>();
        ArrayList<Integer> lostList = new ArrayList<Integer>();
        ArrayList<Integer> pendingList = new ArrayList<Integer>();
        ArrayList<Integer> unavailableList = new ArrayList<Integer>();
        
        String env = request.getParameter("env");
        String siteString = request.getParameter("site");
        String type = request.getParameter("type");
        String cases = request.getParameter("case");
        try {
            ReportDao reportDao = new ReportDao();
            ConfigDao configDao = new ConfigDao();
            List<Config> chargeTypeList = configDao.getConfigList("CHARGE_TYPE");
            List<Config> usageTypeList = configDao.getConfigList("USAGE_TYPE");
            
            EnvironmentDao en = new EnvironmentDao();
            if ("sim".equals(type)) {
                List<Environment> enList = new ArrayList<Environment>();
                if (env.isEmpty() || env == "") {
                    enList = en.getAllEnvirenment();
                } else {
                    enList.add(en.getEnvirenment(env));
                }
                Gson g = new Gson();
                for (Environment enEntry : enList) {
                    String[] siteList = ( (String) enEntry.getEnvSite()).split(",");
                    for (String site : siteList) {
                        if (siteString.isEmpty() || siteString == "") {
                            for (Config chargeType : chargeTypeList) {
                                for (Config usageType : usageTypeList) {
                                    ReportSim report = reportDao.getSummaryReport(enEntry.getEnvCode(), "Site"+site, chargeType.getConName(), usageType.getConName());
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
                        } else if (site.equals(siteString)) {
                            for (Config chargeType : chargeTypeList) {
                                for (Config usageType : usageTypeList) {
                                    ReportSim report = reportDao.getSummaryReport(enEntry.getEnvCode(), "Site"+site, chargeType.getConName(), usageType.getConName());
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
                }
                request.setAttribute("enList", enList);
                request.setAttribute("reportList", reportList);
                request.setAttribute("reportGsonList", reportGsonList);
                request.setAttribute("labelList", g.toJson(labelList));
                request.setAttribute("availableList", g.toJson(availableList));
                request.setAttribute("inUsedList", g.toJson(inUsedList));
                request.setAttribute("lostList", g.toJson(lostList));
                request.setAttribute("pendingList", g.toJson(pendingList));
                request.setAttribute("unavailableList", g.toJson(unavailableList));
            } else if ("testcase".equals(type)) {
                Gson g = new Gson();
                List<Integer> previousYearList = new ArrayList<Integer>();
                List<Integer> thisYearList = new ArrayList<Integer>();
                List<Integer> nextYearList = new ArrayList<Integer>();
                List<Integer> manualStepList = new ArrayList<Integer>();
                List<Integer> autoStepList = new ArrayList<Integer>();
                List<ReportTestCase> testcaseDetList = new ArrayList<ReportTestCase>();
                ArrayList<String> yearList = new ArrayList<String>() {{
                                                int year = Calendar.getInstance().get(Calendar.YEAR);
                                                add(Integer.toString(year - 1));
                                                add(Integer.toString(year));
                                                add(Integer.toString(year + 1));
                                            }};
                
                List<Config> typeList = configDao.getConfigList("TC_TYPE");
                if ("isTestcase".equals(cases)) {
                    for (Config typeConfig : typeList) {
                        labelList.add(typeConfig.getConValue());
                        for (String year : yearList) {
                            ReportTestCase reportTestcase = reportDao.getTestCaseReport(year, typeConfig.getConValue());
                            testcaseDetList.add(reportTestcase);
                            if (yearList.indexOf(year) == 0) {
                                previousYearList.add(reportTestcase.getTestcase());
                            } else if (yearList.indexOf(year) == 1) {
                                thisYearList.add(reportTestcase.getTestcase());
                            } else if (yearList.indexOf(year) == 2) {
                                nextYearList.add(reportTestcase.getTestcase());
                            }
                        }
                    }
                    request.setAttribute("previousYearList", g.toJson(previousYearList));
                    request.setAttribute("thisYearList", g.toJson(thisYearList));
                    request.setAttribute("nextYearList", g.toJson(nextYearList));
                    request.setAttribute("cases", "isTestcase");
                } else if ("isStep".equals(cases)) {
                    for (String year : yearList) {
                        labelList.add(year);
                        for (Config typeConfig : typeList) {
                            ReportTestCase reportTestcase = reportDao.getTestCaseReport(year, typeConfig.getConValue());
                            testcaseDetList.add(reportTestcase);
                            manualStepList.add(reportTestcase.getManualStep());
                            autoStepList.add(reportTestcase.getAutoStep());
                        }
                    }
                    request.setAttribute("manualStepList", g.toJson(manualStepList));
                    request.setAttribute("autoStepList", g.toJson(autoStepList));
                    request.setAttribute("cases", "isStep");
                }
                request.setAttribute("testcaseDetList", testcaseDetList);
                request.setAttribute("labelList", g.toJson(labelList));
            }
            request.setAttribute("envSelectList", en.getAllEnvirenment());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Report Error", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/report/report-all.jsp");
        dispatcher.forward(request, response);
    }
}
