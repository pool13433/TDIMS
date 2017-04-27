package th.co.ais.tdims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Combo;
import th.co.ais.tdims.model.MonthObject;
import th.co.ais.tdims.model.ReportSim;
import th.co.ais.tdims.model.ReportTestCase;

public class ReportDao {

    final static Logger logger = Logger.getLogger(ReportDao.class);

    private Connection conn = null;

    public ReportSim getSummaryReport(String env, String site, String chargeType, String usageType) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ReportSim report = new ReportSim();
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT "
                    + "COUNT(case s.sim_status when 'Available' then 1 else null end) as available, "
                    + "COUNT(case s.sim_status when 'InUsed' then 1 else null end) as inUsed, "
                    + "COUNT(case s.sim_status when 'Lost' then 1 else null end) as lost, "
                    + "COUNT(case s.sim_status when 'Pending' then 1 else null end) as pending,"
                    + "COUNT(case s.sim_status when 'Unavailable' then 1 else null end) as unavailable,"
                    + "COUNT(*) as total FROM sim s");
            sql.append(" WHERE s.env = ? AND UPPER(s.site) = ? AND s.charge_type = ? AND s.usage_type = ? ");
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, env);
            pstm.setString(2, site);
            pstm.setString(3, chargeType);
            pstm.setString(4, usageType);
            rs = pstm.executeQuery();
            if (rs.next()) {
                report.setSite(site);
                report.setEnv(env);
                report.setUsageType(usageType);
                report.setChargeType(chargeType);
                report.setAvailable(rs.getInt("available"));
                report.setInUse(rs.getInt("inUsed"));
                report.setLost(rs.getInt("lost"));
                report.setPending(rs.getInt("pending"));
                report.setUnAvailable(rs.getInt("unavailable"));
                report.setTotal(rs.getInt("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSummaryReport error", e);
        } finally {
            this.close(pstm, rs);
        }
        return report;
    }

    public ReportTestCase getTestCaseReport(String year, String type) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ReportTestCase report = new ReportTestCase();
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT "
                    + "COUNT(*) as alltest, "
                    + "SUM(t.defect_no) as defect,"
                    + "SUM(t.issue_no) as issue,"
                    + "SUM(t.step) as step,"
                    + "SUM(t.automate) as automate FROM testcase t");
            sql.append(" WHERE t.create_date >= '" + year + "-01-01 00:00:00' AND t.create_date <= '" + year + "-12-31 23:59:59' "
                    + "AND t.type = ?");
            System.out.println("===sql " + sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, type);
            rs = pstm.executeQuery();
            if (rs.next()) {
                report.setType(type);
                report.setYear(year);
                report.setTestcase(rs.getInt("alltest"));
                report.setDefect(rs.getInt("defect"));
                report.setIssue(rs.getInt("issue"));
                report.setManualStep(rs.getInt("step"));
                report.setAutoStep(rs.getInt("automate"));
                report.setAllStep(rs.getInt("step") + rs.getInt("automate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSummaryReport error", e);
        } finally {
            this.close(pstm, rs);
        }
        return report;
    }

    public MonthObject getCountSimTransaction(String simStatus) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        MonthObject object = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT YEAR(create_date) as year, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 1 THEN 0 END) AS Jan, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 2 THEN 0 END) AS Feb, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 3 THEN 0 END) AS Mar, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 4 THEN 0 END) AS Apr, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 5 THEN 0 END) AS May, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 6 THEN 0 END) AS Jun, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 7 THEN 0 END) AS Jul, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 8 THEN 0 END) AS Aug, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 9 THEN 0 END) AS Sep, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 10 THEN 0 END) AS Oct, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 11 THEN 0 END) AS Nov, ");
            sql.append(" COUNT(CASE WHEN MONTH(create_date) = 12 THEN 0 END) AS December ");
            sql.append(" FROM sim_history WHERE status = ? AND YEAR(create_date) = YEAR(CURDATE()) ");
            sql.append(" GROUP BY 1 ");
            logger.info(" sql ::==" + sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, simStatus);
            rs = pstm.executeQuery();
            if (rs.next()) {
                object = new MonthObject();
                object.setJanuary(rs.getString("Jan"));
                object.setFebruary(rs.getString("Feb"));
                object.setMarch(rs.getString("Mar"));
                object.setApril(rs.getString("Apr"));
                object.setMay(rs.getString("May"));
                object.setJanuary(rs.getString("Jun"));
                object.setJuly(rs.getString("Jul"));
                object.setAugust(rs.getString("Aug"));
                object.setSeptember(rs.getString("Sep"));
                object.setOctober(rs.getString("Oct"));
                object.setNovember(rs.getString("Nov"));
                object.setDecember(rs.getString("December"));
                object.setYear(rs.getString("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCountSimTransaction error", e);
        } finally {
            this.close(pstm, rs);
        }
        return object;
    }

    public Map<String, Integer> getGroupSimStatus() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Map<String, Integer> data = new HashMap<String, Integer>();
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT s.sim_STATUS as status, COUNT(s.mobile_no) AS cnt_sim ");
            sql.append(" FROM sim s ");
            sql.append(" LEFT JOIN sim_history h ON s.mobile_no = h.mobile_no ");
            sql.append(" WHERE YEAR (s.create_date) = YEAR(CURDATE()) ");
            sql.append(" GROUP BY s.sim_STATUS ");

            logger.info(" sql ::==" + sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                data.put(rs.getString("status"), rs.getInt("cnt_sim"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCountSimTransaction error", e);
        } finally {
            this.close(pstm, rs);
        }
        return data;
    }

    private void close(PreparedStatement pstm, ResultSet rs) {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            logger.error("getUser error", ex);
        }
    }

}
