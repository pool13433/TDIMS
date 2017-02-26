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
import th.co.ais.tdims.model.Sim;

/**
 *
 * @author POOL_LAPTOP
 */
public class SimDao {

    final static Logger logger = Logger.getLogger(SimDao.class);

    private Connection conn = null;
    
    private final String STR_TO_DATE = " '%d-%m-%Y' ";
    private final String DATE_TO_STR = " '%d-%m-%Y' ";

    public List<Sim> getSimAll() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Sim> simList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, ");
            sql.append(" `region_code`, `env`, `site`, `usage_type`, `assign_team`, ");
            sql.append(" `email_contact`, `project`, ");
            sql.append(" DATE_FORMAT(valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" `remark`,`create_by`, `update_by`, `sim_status` ");
            sql.append(" FROM `sim` ");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            simList = new ArrayList<Sim>();
            while (rs.next()) {                
                simList.add(getEntitySim(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSimAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return simList;
    }

    private Sim getEntitySim(ResultSet rs) throws SQLException {
        Sim sim = new Sim();
        sim.setAssignTeam(rs.getString("assign_team"));
        sim.setChargeType(rs.getString("charge_type"));
        sim.setCreateBy(rs.getInt("create_by"));
        sim.setCreateDate(rs.getString("create_date"));
        sim.setEmailContact(rs.getString("email_contact"));
        sim.setEnv(rs.getString("env"));
        sim.setExpireDate(rs.getString("expire_date"));
        sim.setImsi(rs.getString("imsi"));
        sim.setMobileNo(rs.getString("mobile_no"));
        sim.setProject(rs.getInt("project"));
        sim.setRegionCode(rs.getString("region_code"));
        sim.setRemark(rs.getString("remark"));
        sim.setSerialNo(rs.getString("serial_no"));
        sim.setSimId(rs.getString("sim_id"));
        sim.setSimStatus(rs.getString("sim_status"));
        sim.setSite(rs.getString("site"));
        sim.setUpdateBy(rs.getInt("update_by"));
        sim.setUpdateDate(rs.getString("update_date"));
        sim.setUsageType(rs.getString("usage_type"));
        sim.setValidDate(rs.getString("valid_date"));
        return sim;
    }
    
    public Sim getSim(int simId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Sim sim = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, ");
            sql.append(" `region_code`, `env`, `site`, `usage_type`, `assign_team`, ");
            sql.append(" `email_contact`, `project`, ");
            sql.append(" DATE_FORMAT(valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" `remark`,`create_by`, `update_by`, `sim_status` ");
            sql.append(" FROM `sim` ");
            sql.append(" WHERE  sim_id = ?");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, simId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sim = getEntitySim(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSimAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return sim;
    }
    
    
    public int createSim(Sim sim){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `sim` ");
            sql.append(" (`mobile_no`, `serial_no`, `imsi`, `charge_type`,`region_code`, ");
            sql.append("  `env`, `site`, `usage_type`, `assign_team`,`email_contact`, ");
            sql.append("  `project`, `valid_date`, `expire_date`, `remark`,`create_date`, ");
            sql.append("  `create_by`, `update_date`, `update_by`, `sim_status`) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,?,?,");
            sql.append(" ?,?,?,?,?,");
            sql.append(" ?,STR_TO_DATE(?,").append(STR_TO_DATE).append("),STR_TO_DATE(?,").append(STR_TO_DATE).append("),?,NOW(),");
            sql.append(" ?,NOW(),?,? )");
            
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, sim.getMobileNo());
            pstm.setString(2, sim.getSerialNo());
            pstm.setString(3, sim.getImsi());
            pstm.setString(4, sim.getChargeType());
            pstm.setString(5, sim.getRegionCode());
            pstm.setString(6, sim.getEnv());
            pstm.setString(7, sim.getSite());
            pstm.setString(8, sim.getUsageType());
            pstm.setString(9, sim.getAssignTeam());
            pstm.setString(10, sim.getEmailContact());
            pstm.setInt(11, sim.getProject());
            pstm.setString(12, sim.getValidDate());
            pstm.setString(13, sim.getExpireDate());
            pstm.setString(14, sim.getRemark());
            pstm.setInt(15, sim.getCreateBy());
            pstm.setInt(16, sim.getUpdateBy());
            pstm.setString(17, sim.getSimStatus());            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveSim error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updateSim(Sim sim){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `sim` SET ");
            sql.append(" `mobile_no`=?,`serial_no`=?,`imsi`=?,`charge_type`=?,`region_code`=?,");
            sql.append(" `env`=?,`site`=?,`usage_type`=?,`assign_team`=?,`email_contact`=?,");
            sql.append(" `project`=?,`valid_date`=STR_TO_DATE(?,").append(STR_TO_DATE).append("),`expire_date`=STR_TO_DATE(?,").append(STR_TO_DATE).append("),`remark`=?,`update_date`=NOW(),");
            sql.append(" `update_by`=?,`sim_status`=? ");
            sql.append(" WHERE `sim_id`=?");
            
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, sim.getMobileNo());
            pstm.setString(2, sim.getSerialNo());
            pstm.setString(3, sim.getImsi());
            pstm.setString(4, sim.getChargeType());
            pstm.setString(5, sim.getRegionCode());
            
            pstm.setString(6, sim.getEnv());
            pstm.setString(7, sim.getSite());
            pstm.setString(8, sim.getUsageType());
            pstm.setString(9, sim.getAssignTeam());
            pstm.setString(10, sim.getEmailContact());
            
            pstm.setInt(11, sim.getProject());
            pstm.setString(12, sim.getValidDate());
            pstm.setString(13, sim.getExpireDate());
            pstm.setString(14, sim.getRemark());
            pstm.setInt(15, sim.getUpdateBy());
            pstm.setString(16, sim.getSimStatus());            
            pstm.setString(17, sim.getSimId());      
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveSim error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int deleteSim(int simId){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `sim` WHERE sim_id=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setInt(1, simId);      
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveSim error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
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
