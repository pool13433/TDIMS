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
import th.co.ais.tdims.model.ExpiredSim;
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
            sql.append(" `region_code`, system,`env_id`, `usage_type`,owner, `team_id`, ");
            sql.append(" `email_contact`, `project_id`, ");
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
    
    public List<ExpiredSim> getExpiredSim() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<ExpiredSim> simList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT s.sim_id, s.mobile_no, s.serial_no, s.imsi, s.charge_type, ");
            sql.append(" s.region_code, s.system,e.env_code, e.env_site, s.usage_type, s.owner, t.team_name, ");
            sql.append(" s.email_contact, p.proj_name, ");
            sql.append(" DATE_FORMAT(s.valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(s.expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(s.create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(s.update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" s.remark, s.create_by, s.update_by, s.sim_status ");
            sql.append(" FROM sim s, team t, project p, enviroment e ");
            sql.append(" WHERE s.expire_date < CURDATE() AND s.team_id=t.team_id AND s.env_id=e.env_id AND s.project_id=p.proj_id ");
            sql.append(" GROUP BY s.team_id, s.system ");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            simList = new ArrayList<ExpiredSim>();
            while (rs.next()) {                
                simList.add(getEntityExpiredSim(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getExpiredSimAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return simList;
    }
    
    private Sim getEntitySim(ResultSet rs) throws SQLException {
        Sim sim = new Sim();
        sim.setTeamId(rs.getString("team_id"));
        sim.setChargeType(rs.getString("charge_type"));
        sim.setCreateBy(rs.getString("create_by"));
        sim.setCreateDate(rs.getString("create_date"));
        sim.setEmailContact(rs.getString("email_contact"));
        sim.setEnviroment(rs.getString("env_id"));
        sim.setExpireDate(rs.getString("expire_date"));
        sim.setImsi(rs.getString("imsi"));
        sim.setMobileNo(rs.getString("mobile_no"));
        sim.setProjectId(rs.getString("project_id"));
        sim.setRegionCode(rs.getString("region_code"));
        sim.setRemark(rs.getString("remark"));
        sim.setSerialNo(rs.getString("serial_no"));
        sim.setSimId(rs.getString("sim_id"));
        sim.setSimStatus(rs.getString("sim_status"));
        //sim.setSite(rs.getString("site_id"));
        sim.setUpdateBy(rs.getString("update_by"));
        sim.setUpdateDate(rs.getString("update_date"));
        sim.setUsageType(rs.getString("usage_type"));
        sim.setValidDate(rs.getString("valid_date"));
        sim.setSystem(rs.getString("system"));
        sim.setOwner(rs.getString("owner"));
        return sim;
    }
    
    private ExpiredSim getEntityExpiredSim(ResultSet rs) throws SQLException {
        ExpiredSim sim = new ExpiredSim();
        sim.setTeamName(rs.getString("team_name"));
        sim.setChargeType(rs.getString("charge_type"));
        sim.setCreateBy(rs.getString("create_by"));
        sim.setCreateDate(rs.getString("create_date"));
        sim.setEmailContact(rs.getString("email_contact"));
        sim.setEnviroment(rs.getString("env_code"));
        sim.setExpireDate(rs.getString("expire_date"));
        sim.setImsi(rs.getString("imsi"));
        sim.setMobileNo(rs.getString("mobile_no"));
        sim.setProjectName(rs.getString("proj_name"));
        sim.setRegionCode(rs.getString("region_code"));
        sim.setRemark(rs.getString("remark"));
        sim.setSerialNo(rs.getString("serial_no"));
        sim.setSimId(rs.getString("sim_id"));
        sim.setSimStatus(rs.getString("sim_status"));
        //sim.setSite(rs.getString("site_id"));
        sim.setUpdateBy(rs.getString("update_by"));
        sim.setUpdateDate(rs.getString("update_date"));
        sim.setUsageType(rs.getString("usage_type"));
        sim.setValidDate(rs.getString("valid_date"));
        sim.setSystem(rs.getString("system"));
        sim.setOwner(rs.getString("owner"));
        sim.setSite(rs.getString("env_site"));
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
            sql.append(" `region_code`, system, `env_id`, `usage_type`, owner, `team_id`, ");
            sql.append(" `email_contact`, `project_id`, ");
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
    
    public int createSim(Sim sim) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `sim` ");
            sql.append(" (`mobile_no`, `serial_no`, `imsi`, `charge_type`, `region_code`,");
            sql.append("  `system`, `env_id`, `usage_type`, `owner`,`create_date`, ");
            sql.append("  `create_by`, `update_date`, `update_by`, `sim_status`) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,?,?,");
            sql.append(" ?,?,?,?,NOW(),");            
            sql.append(" ?,NOW(),?,? )");

            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());            
            pstm.setString(1, sim.getMobileNo());
            pstm.setString(2, sim.getSerialNo());
            pstm.setString(3, sim.getImsi());
            pstm.setString(4, sim.getChargeType());
            pstm.setString(5, sim.getRegionCode());
            
            pstm.setString(6, sim.getSystem());            
            pstm.setString(7, sim.getEnviroment());            
            pstm.setString(8, sim.getUsageType());
            pstm.setString(9, sim.getOwner());
            pstm.setString(10, sim.getCreateBy());
            
            pstm.setString(11, sim.getUpdateBy());
            pstm.setString(12, sim.getSimStatus());            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveSim error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updateSim(Sim sim) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `sim` SET ");
            sql.append(" `mobile_no`=?,`serial_no`=?,`imsi`=?,`charge_type`=?,`region_code`=?,");
            sql.append(" system=?,`env_id`=?,`usage_type`=?,owner=?,`update_date`=NOW(),");
            sql.append(" `update_by`=?,`sim_status`=? ");
            sql.append(" WHERE `sim_id`=?");

            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());            
            pstm.setString(1, sim.getMobileNo());
            pstm.setString(2, sim.getSerialNo());
            pstm.setString(3, sim.getImsi());
            pstm.setString(4, sim.getChargeType());
            pstm.setString(5, sim.getRegionCode());
            
            pstm.setString(6, sim.getSystem());
            pstm.setString(7, sim.getEnviroment());            
            pstm.setString(8, sim.getUsageType());
            pstm.setString(9, sim.getOwner());            
            pstm.setString(10, sim.getUpdateBy());
            
            pstm.setString(11, sim.getSimStatus());            
            pstm.setString(12, sim.getSimId());            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveSim error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int deleteSim(int simId) {
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
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public List<Sim> findSim(Sim searching) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Sim> simList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, ");
            sql.append(" `region_code`, `env_id`, system, `usage_type`, owner, `team_id`, ");
            sql.append(" `email_contact`, `project_id`, ");
            sql.append(" DATE_FORMAT(valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" `remark`,`create_by`, `update_by`, `sim_status` ");
            sql.append(" FROM `sim` ");
            sql.append(" WHERE 1=1 ");

            if(!"".equals(searching.getMobileNo())){
                sql.append(" and `mobile_no` ='"+searching.getMobileNo()+"'");
            }
            if(!"".equals(searching.getEnviroment())){
                sql.append(" and `env_id` ="+searching.getEnviroment());
            }
            if(!"".equals(searching.getSystem())){
                sql.append(" and `system` ='"+searching.getSystem()+"'");
            }
            if(!"".equals(searching.getSimStatus())){
                sql.append(" and `sim_status` ='"+searching.getSimStatus()+"'");
            }
            logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            simList = new ArrayList<Sim>();
            while (rs.next()) {
                simList.add(getEntitySim(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("find Sim error", e);
        } finally {
            this.close(pstm, rs);
        }
        return simList;
    }
    
    public List<Sim> bookSim(Sim sim, String simId) {
        logger.info("Booking Sim");
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `sim` SET ");
            sql.append(" `team_id` =?, `email_contact` =?, `project_id` =?, ");
            sql.append(" `valid_date`=STR_TO_DATE(?,").append(STR_TO_DATE).append("),`expire_date`=STR_TO_DATE(?,").append(STR_TO_DATE).append("),`update_date`=NOW(),");
            sql.append(" `remark` =?, `update_by`=?,`sim_status`=? ");
            sql.append(" WHERE `sim_id` in(" + simId + ")");
            logger.info("sql ::==" + sql.toString());
            pstm = conn.prepareStatement(sql.toString());            
            pstm.setString(1, sim.getTeamId());
            pstm.setString(2, sim.getEmailContact());
            pstm.setString(3, sim.getProjectId());
            pstm.setString(4, sim.getValidDate());
            pstm.setString(5, sim.getExpireDate());
            pstm.setString(6, sim.getRemark());
            pstm.setString(7, sim.getUpdateBy());
            pstm.setString(8, sim.getSimStatus());
            //pstm.setString(9, simId);   
            logger.info(sim.getSimId() + " : pstm : " + pstm.toString());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("booking sim error", e);
        } finally {
            this.close(pstm, null);
        }
        
        return this.getSimAll();
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
