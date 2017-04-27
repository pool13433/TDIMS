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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.ExpiredSim;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.model.SimHistory;
import th.co.ais.tdims.util.CharacterUtil;

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
            StringBuilder sql = getQueryBuilder();
            logger.info("sql ::==" + sql);
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

    private StringBuilder getQueryBuilder() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT `sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, ");
        sql.append(" `region_code`, system,`env`, site, `usage_type`, (select t.team_name from team t  where t.team_id=s.team_id) as team_id, ");
        sql.append(" `email_contact`, (select p.proj_name from project p where p.proj_id = s.project_id)  as project_id, ");
        sql.append(" DATE_FORMAT(valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(expire_date,").append(DATE_TO_STR).append(") expire_date, ");
        sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
        sql.append(" `remark`,`create_by`, `update_by`, `sim_status` ");
        sql.append(" ,(SELECT CONCAT(fname,' ',lname) FROM profile p WHERE p.profile_id = s.create_by) as create_by");
        sql.append(" FROM `sim` s ");
        return sql;
    }

    public List<ExpiredSim> getExpiredSim(int teamId, String system, String mobile, int limit, int offset) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<ExpiredSim> simList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT s.sim_id, s.mobile_no, s.serial_no, s.imsi, s.charge_type, ");
            sql.append(" s.region_code, s.system, s.env, s.site, s.usage_type, t.team_name, ");
            sql.append(" s.email_contact, p.proj_name, ");
            sql.append(" DATE_FORMAT(s.valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(s.expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(s.create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(s.update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" s.remark, s.create_by, s.update_by, s.sim_status ");
            sql.append(" FROM sim s ");
            sql.append(" LEFT JOIN team t ON s.team_id=t.team_id ");
            sql.append(" LEFT JOIN project p ON s.project_id=p.proj_id");
            sql.append(" WHERE s.expire_date < CURDATE() AND t.team_id = ? AND s.system = ? AND s.mobile_no = ?");
            sql.append(" limit ").append(limit).append(" offset ").append(offset);
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, teamId);
            pstm.setString(2, system);
            pstm.setString(3, mobile);
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

    public int getCountExpiredSim(int teamId, String system, String mobile) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int countSim = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT COUNT(*) as cnt");
            sql.append(" FROM sim s ");
            sql.append(" LEFT JOIN team t ON s.team_id=t.team_id ");
            sql.append(" LEFT JOIN project p ON s.project_id=p.proj_id");
            sql.append(" WHERE s.expire_date < CURDATE() AND t.team_id = ? AND s.system = ? AND s.mobile_no = ?");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, teamId);
            pstm.setString(2, system);
            pstm.setString(3, mobile);
            rs = pstm.executeQuery();
            if (rs.next()) {
                countSim = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getExpiredSimAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return countSim;
    }
    
    private Sim getEntitySim(ResultSet rs) throws SQLException {
        Sim sim = new Sim();
        sim.setTeamId(rs.getString("team_id"));
        sim.setChargeType(rs.getString("charge_type"));
        sim.setCreateBy(rs.getString("create_by"));
        sim.setCreateDate(rs.getString("create_date"));
        sim.setEmailContact(rs.getString("email_contact"));
        sim.setEnviroment(rs.getString("env"));
        sim.setExpireDate(rs.getString("expire_date"));
        sim.setImsi(rs.getString("imsi"));
        sim.setSystem(rs.getString("system"));
        sim.setMobileNo(rs.getString("mobile_no"));
        sim.setProjectId(rs.getString("project_id"));
        sim.setRegionCode(rs.getString("region_code"));
        sim.setRemark(rs.getString("remark"));
        sim.setSerialNo(rs.getString("serial_no"));
        sim.setSimId(rs.getString("sim_id"));
        sim.setSimStatus(rs.getString("sim_status"));
        sim.setSite(rs.getString("site"));
        sim.setUpdateBy(rs.getString("update_by"));
        sim.setUpdateDate(rs.getString("update_date"));
        sim.setUsageType(rs.getString("usage_type"));
        sim.setValidDate(rs.getString("valid_date"));
        return sim;
    }

    private SimHistory getEntityHistorySim(ResultSet rs) throws SQLException {
        SimHistory simHistory = new SimHistory();
        simHistory.setLogId(rs.getString("log_id"));
        simHistory.setTeamId(rs.getString("team_id"));
        simHistory.setCreateBy(rs.getString("create_by"));
        simHistory.setCreateDate(rs.getString("create_date"));
        simHistory.setEnviroment(rs.getString("env"));
        simHistory.setSystem(rs.getString("system"));
        simHistory.setMobileNo(rs.getString("mobile_no"));
        simHistory.setProjectId(rs.getString("project_id"));
        simHistory.setRemark(rs.getString("remark"));
        simHistory.setStatus(rs.getString("status"));
        simHistory.setSite(rs.getString("site"));
        return simHistory;
    }

    private ExpiredSim getEntityExpiredSim(ResultSet rs) throws SQLException {
        ExpiredSim sim = new ExpiredSim();
        sim.setTeamName(rs.getString("team_name"));
        sim.setChargeType(rs.getString("charge_type"));
        sim.setCreateBy(rs.getString("create_by"));
        sim.setCreateDate(rs.getString("create_date"));
        sim.setEmailContact(rs.getString("email_contact"));
        sim.setEnviroment(rs.getString("env"));
        sim.setExpireDate(rs.getString("expire_date"));
        sim.setImsi(rs.getString("imsi"));
        sim.setMobileNo(rs.getString("mobile_no"));
        sim.setProjectName(rs.getString("proj_name"));
        sim.setRegionCode(rs.getString("region_code"));
        sim.setRemark(rs.getString("remark"));
        sim.setSerialNo(rs.getString("serial_no"));
        sim.setSimId(rs.getString("sim_id"));
        sim.setSimStatus(rs.getString("sim_status"));
        sim.setUpdateBy(rs.getString("update_by"));
        sim.setUpdateDate(rs.getString("update_date"));
        sim.setUsageType(rs.getString("usage_type"));
        sim.setValidDate(rs.getString("valid_date"));
        sim.setSystem(rs.getString("system"));
        sim.setSite(rs.getString("site"));
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
            sql.append(" `region_code`, system, `env`, site, `usage_type`, `team_id`, ");
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
            sql.append("  `system`, `env`, site,`usage_type`, `create_date`,");
            sql.append("  `create_by`,`sim_status`) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,?,?,");
            sql.append(" ?,?,?,?,NOW(),");
            sql.append(" ?,? )");

            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, sim.getMobileNo());
            pstm.setString(2, sim.getSerialNo());
            pstm.setString(3, sim.getImsi());
            pstm.setString(4, sim.getChargeType());
            pstm.setString(5, sim.getRegionCode());

            pstm.setString(6, sim.getSystem());
            pstm.setString(7, sim.getEnviroment());
            pstm.setString(8, sim.getSite());
            pstm.setString(9, sim.getUsageType());
            pstm.setString(10, sim.getCreateBy());

            pstm.setString(11, sim.getSimStatus());

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
            sql.append(" system=?,`env`=?,site=?,`usage_type`=?,");
            sql.append(" `update_date`=NOW(),`update_by`=?,`sim_status`=? ");
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
            pstm.setString(8, sim.getSite());
            pstm.setString(9, sim.getUsageType());

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

    public List<Sim> findSim(Sim searching, int limit, int offset) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Sim> simList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, ");
            sql.append(" `region_code`, `env`, site, system, `usage_type`, (select t.team_name from team t  where t.team_id=s.team_id) as team_id, ");
            sql.append(" `email_contact`, (select p.proj_name from project p where p.proj_id = s.project_id)  as project_id, ");
            sql.append(" DATE_FORMAT(valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" `remark`,`create_by`, `update_by`, `sim_status` ");
            sql.append(" FROM `sim` s ");
            sql.append(getConditionBuilder(searching));
            sql.append(" limit ").append(limit).append(" offset ").append(offset);
            logger.info("sql ::==" + sql);
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

    public String getConditionBuilder(Sim searching) {
        StringBuilder sql = new StringBuilder(" WHERE 1=1 ");
        if (!"".equals(CharacterUtil.removeNull(searching.getMobileNo()))) {
            sql.append(" and `mobile_no` LIKE '%" + searching.getMobileNo() + "%'");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getSerialNo()))) {
            sql.append(" and `serial_no` LIKE '%" + searching.getSerialNo() + "%'");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getImsi()))) {
            sql.append(" and `imsi` LIKE '%" + searching.getImsi() + "%'");
        }
         if (!"".equals(CharacterUtil.removeNull(searching.getUsageType()))) {
            sql.append(" and `usage_type` = '" + searching.getUsageType() + "' ");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getChargeType()))) {
            sql.append(" and `charge_type` = '" + searching.getChargeType() + "' ");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getRegionCode()))) {
            sql.append(" and `region_code` LIKE '%" + searching.getRegionCode() + "%' ");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getSystem()))) {
            sql.append(" and `system` ='" + searching.getSystem() + "'");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getEnviroment()))) {
            sql.append(" and `env` ='" + searching.getEnviroment() + "'");
        }        
        if (!"".equals(CharacterUtil.removeNull(searching.getSite()))) {
            sql.append(" and `site` ='" + searching.getSite()+ "'");
        }  
        if (!"".equals(CharacterUtil.removeNull(searching.getSimStatus()))) {
            sql.append(" and `sim_status` ='" + searching.getSimStatus() + "'");
        }
        if (!"".equals(CharacterUtil.removeNull(searching.getSimId()))) {
            sql.append(" and `sim_id` in(" + searching.getSimId() + ")");
        }

        return sql.toString();
    }

    public List<Sim> findSimCancel(Sim searching) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Sim> simList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, ");
            sql.append(" `region_code`, `env`, site, system, `usage_type`, team_id, ");
            sql.append(" `email_contact`, project_id, ");
            sql.append(" DATE_FORMAT(valid_date,").append(DATE_TO_STR).append(") valid_date, DATE_FORMAT(expire_date,").append(DATE_TO_STR).append(") expire_date, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" `remark`,`create_by`, `update_by`, `sim_status` ");
            sql.append(" FROM `sim` s ");
            sql.append(" WHERE 1=1 ");

            if (!"".equals(CharacterUtil.removeNull(searching.getSimId()))) {
                sql.append(" and `sim_id` in(" + searching.getSimId() + ")");
            }
            logger.info("sql ::==" + sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            simList = new ArrayList<Sim>();
            while (rs.next()) {
                simList.add(getEntitySim(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("findSimCancel error", e);
        } finally {
            this.close(pstm, rs);
        }
        return simList;
    }

    public List<SimHistory> findSimHistory(String mobile, String dateFrom, String dateTo, int limit, int offset){
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<SimHistory> simHistoryList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `log_id`, `mobile_no`, `system`, `env`, `site`, `status`, ");
            sql.append(" (select t.team_name from team t  where t.team_id=s.team_id) as `team_id`, `remark`, ");
            sql.append(" (select pf.username from profile pf where pf.profile_id = s.create_by)  as create_by, ");
            sql.append(" (select p.proj_name from project p where p.proj_id = s.project_id)  as project_id, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date ");
            sql.append(" FROM `sim_history` s ");
            sql.append(getConditionBuilderSimHis(mobile,dateFrom,dateTo));
            sql.append(" order by create_date DESC ");            
            sql.append(" limit ").append(limit).append(" offset ").append(offset);      
            

            logger.info("sql ::==" + sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            simHistoryList = new ArrayList<SimHistory>();
            while (rs.next()) {
                simHistoryList.add(getEntityHistorySim(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("find SimHistory error", e);
        } finally {
            this.close(pstm, rs);
        }
        return simHistoryList;
    }

    public String getConditionBuilderSimHis(String mobile, String dateFrom, String dateTo){
        StringBuilder sql = new StringBuilder(" WHERE 1=1 ");
        try {

            if (!"".equals(CharacterUtil.removeNull(mobile))) {
                sql.append(" and `mobile_no` LIKE '%" + mobile + "%'");
            }
            if (!"".equals(CharacterUtil.removeNull(dateFrom)) && !"".equals(CharacterUtil.removeNull(dateTo))) {
                SimpleDateFormat d1 = new SimpleDateFormat("dd-mm-yyyy");
                Date dateF = d1.parse(dateFrom);
                Date dateT = d1.parse(dateTo);
                SimpleDateFormat d2 = new SimpleDateFormat("yyyy-mm-dd");
                sql.append(" and `create_date` between '" + d2.format(dateF) + "' and '" + d2.format(dateT) + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql.toString();
    }
    public int bookSim(Sim sim, String simId) {
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

        return exe;
    }

    public int simSaveLog(Sim sim) {
        logger.info("simSaveLog");
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `sim_history` ");
            sql.append("  (`mobile_no`, `system`, `env`, `site`, ");
            sql.append(" `create_by`,`create_date`, ");
            sql.append(" `status`, `team_id`, `project_id`, `remark`) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,?,");
            sql.append(" ?,NOW(),?,?,?,?)");

            logger.info("sql ::==" + sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, sim.getMobileNo());
            pstm.setString(2, sim.getSystem());
            pstm.setString(3, sim.getEnviroment());
            pstm.setString(4, sim.getSite());
            pstm.setString(5, sim.getCreateBy());
            pstm.setString(6, sim.getSimStatus());
            pstm.setString(7, sim.getTeamId());
            pstm.setString(8, sim.getProjectId());
            pstm.setString(9, sim.getRemark());

            logger.info(" : pstm : " + pstm.toString());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("simSaveLog error", e);
        } finally {
            this.close(pstm, null);
        }

        return exe;
    }

    public int resetBookingSim(Sim sim, String simId) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `sim` SET ");
            sql.append(" `team_id` = 0, `email_contact` = '', `project_id` = 0, `valid_date` = null, `expire_date` = null,");
            sql.append(" `update_by` = ?, `update_date`=NOW(), ");
            sql.append(" `sim_status` = 'Available', remark='' ");
            sql.append(" WHERE `sim_id` in(" + simId + ")");

            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, sim.getUpdateBy());
            logger.info("pstm ::==" + pstm.toString());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("resetBookingSim error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }

    public List<Sim> getSimRecordLimit(int limit, int offset) {
        List<Sim> simList = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = getQueryBuilder();
            sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
            pstm = conn.prepareStatement(sql.toString());
            logger.info("pstm ::==" + pstm.toString());
            rs = pstm.executeQuery();
            simList = new ArrayList<Sim>();
            while (rs.next()) {
                simList.add(getEntitySim(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSimRecordLimit error", e);
        } finally {
            this.close(pstm, rs);
        }
        return simList;
    }

    public int getCountSim(String conditionBuilder) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int countSim = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) as cnt FROM sim s");
            if (conditionBuilder != null) {
                sql.append(conditionBuilder);
            }
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            if (rs.next()) {
                countSim = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCountSimAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return countSim;
    }

    public int getCountSimHis(String conditionBuilder) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int countSimHis = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) as cnt FROM sim_history sh");
            if (conditionBuilder != null) {
                sql.append(conditionBuilder);
            }
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            if (rs.next()) {
                countSimHis = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCountSimHistoryAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return countSimHis;
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
