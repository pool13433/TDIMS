package th.co.ais.tdims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Report;


public class ReportDao {
    final static Logger logger = Logger.getLogger(ReportDao.class);

    private Connection conn = null;
    
    public Report getSummaryReport(String env, String site, String chargeType, String usageType) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Report report = new Report();
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT "
                    + "COUNT(case s.sim_status when 'available' then 1 else null end) as available, "
                    + "COUNT(case s.sim_status when 'inUsed' then 1 else null end) as inUsed, "
                    + "COUNT(case s.sim_status when 'lost' then 1 else null end) as lost, "
                    + "COUNT(case s.sim_status when 'pending' then 1 else null end) as pending,"
                    + "COUNT(case s.sim_status when 'unavailable' then 1 else null end) as unavailable,"
                    + "COUNT(*) as total FROM sim s");
            sql.append(" WHERE s.env = ? AND s.site = ? AND s.charge_type = ? AND s.usage_type = ? ");
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
