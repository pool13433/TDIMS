/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Environment;

public class EnvironmentDao {

    final static Logger logger = Logger.getLogger(EnvironmentDao.class);

    private Connection conn = null;

    public List<Environment> getAllEnvirenment() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Environment> envList = null;
        try {
            conn = new DbConnection().open();
            String sql = "SELECT * FROM enviroment ORDER BY env_code";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            envList = new ArrayList<Environment>();
            while (rs.next()) {
                Environment env = new Environment();
                env.setCreateBy(rs.getString("create_by"));
                env.setCreateDate(rs.getString("create_date"));
                env.setEnvCode(rs.getString("env_code"));
                env.setEnvDesc(rs.getString("env_desc"));
                env.setEnvId(rs.getString("env_id"));
                env.setEnvSite(rs.getString("env_site"));
                env.setUpdateBy(rs.getString("update_by"));
                env.setUpdateDate(rs.getString("update_date"));
                envList.add(env);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getAllEnvirenment error", e);
        } finally {
            this.close(pstm, rs);
        }
        return envList;
    }

    public Environment getEnvirenment(String envId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Environment env = null;
        try {
            conn = new DbConnection().open();
            String sql = "SELECT * FROM enviroment where env_id = ? ORDER BY env_code";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, envId);
            rs = pstm.executeQuery();
            env = new Environment();
            while (rs.next()) {
                env.setCreateBy(rs.getString("create_by"));
                env.setCreateDate(rs.getString("create_date"));
                env.setEnvCode(rs.getString("env_code"));
                env.setEnvDesc(rs.getString("env_desc"));
                env.setEnvId(rs.getString("env_id"));
                env.setEnvSite(rs.getString("env_site"));
                env.setUpdateBy(rs.getString("update_by"));
                env.setUpdateDate(rs.getString("update_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getAllEnvirenment error", e);
        } finally {
            this.close(pstm, rs);
        }
        return env;
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
