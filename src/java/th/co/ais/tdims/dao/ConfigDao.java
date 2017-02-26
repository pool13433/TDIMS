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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Config;

/**
 *
 * @author POOL_LAPTOP
 */
public class ConfigDao {

    final static Logger logger = Logger.getLogger(ConfigDao.class);

    private Connection conn = null;

    public Map<String, String> getConfigMap(String configCode) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Map<String, String> configMap = new HashMap<String, String>();
        try {
            conn = new DbConnection().open();
            String sql = "SELECT c.con_name,c.con_value FROM config c WHERE c.con_code = ?";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, configCode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                configMap.put(rs.getString("con_name"), rs.getString("con_value"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getConfigMap error", e);
        } finally {
            this.close(pstm, rs);
        }
        return configMap;
    }
    
    public List<Config> getConfigList(String configCode) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Config> configList = null;
        try {
            conn = new DbConnection().open();
            String sql = "SELECT c.con_name,c.con_value FROM config c WHERE c.con_code = ? ORDER BY c.con_value ASC";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, configCode);
            rs = pstm.executeQuery();
            configList = new ArrayList<Config>();
            while (rs.next()) {                
                Config config = new Config();
                config.setConName(rs.getString("con_name"));
                config.setConValue(rs.getString("con_value"));
                configList.add(config);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getConfigList error", e);
        } finally {
            this.close(pstm, rs);
        }
        return configList;
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
