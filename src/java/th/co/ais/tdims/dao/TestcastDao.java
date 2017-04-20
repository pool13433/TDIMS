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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import static th.co.ais.tdims.dao.SimDao.logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Sim;
import th.co.ais.tdims.model.Testcase;
import th.co.ais.tdims.util.CharacterUtil;

/**
 *
 * @author Administrator
 */
public class TestcastDao {
    final static Logger logger = Logger.getLogger(TestcastDao.class);

    private Connection conn = null;
    
    private final String STR_TO_DATE = " '%d-%m-%Y' ";
    private final String DATE_TO_STR = " '%d-%m-%Y' ";
    
    public List<Testcase> getTestcaseAll() {
        logger.debug("getTestcaseAll");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Testcase> testcaseList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `testcase_id`, `testcase_title`, `testcase_details`, ");
            sql.append(" `systems`, `enviroment`, `defect_no`, `issue_no`, (select p.proj_name from project p where p.proj_id = t.project_id)  as `project_id`,  ");
            sql.append(" `path_dir`, (select pf.username from profile pf where pf.profile_id = t.create_by)  as `create_by`,  ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, `step`,`type` ");
            sql.append(" FROM `testcase` t ");
            logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            testcaseList = new ArrayList<Testcase>();
            while (rs.next()) {                
                testcaseList.add(getEntityTestcase(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getTestcaseAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return testcaseList;
    }
    public List<Testcase> findTestcase(Testcase tc, int limit, int offset) {
        logger.debug("findTestcase");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Testcase> testcaseList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `testcase_id`, (select p.proj_name from project p where p.proj_id = t.project_id)  as `project_id`,  ");
            sql.append(" (select pf.username from profile pf where pf.profile_id = t.create_by)  as  `create_by`, ");
            sql.append(" `systems`, `enviroment`, `issue_no`, `path_dir`, ");
            sql.append(" `testcase_details`, `testcase_title`,`defect_no`, `step`, `type`, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date ");
            sql.append(" FROM `testcase` t ");            
            sql.append(getConditionBuilder(tc));            
            sql.append(" limit ").append(limit).append(" offset ").append(offset);            
            System.out.println("SQL : "+sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            testcaseList = new ArrayList<Testcase>();
            while (rs.next()) {                
                testcaseList.add(getEntityTestcase(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("findTestcase error", e);
        } finally {
            this.close(pstm, rs);
        }
        return testcaseList;
    }
    
    public String getConditionBuilder(Testcase tc){
        StringBuilder sql = new StringBuilder(" WHERE 1=1 ");
        try {
            if(!"".equals(CharacterUtil.removeNull(tc.getProjectId()))){
                sql.append(" and `project_id` = '"+tc.getProjectId()+"'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getTestcaseTitle()))){
                sql.append(" and `testcase_title` LIKE '%"+tc.getTestcaseTitle()+"%'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getTestcaseDetails()))){
                sql.append(" and `testcase_details` LIKE '%"+tc.getTestcaseDetails()+"%'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getSystems()))){
                sql.append(" and `systems` = '"+tc.getSystems()+"'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getEnviroment()))){
                sql.append(" and `enviroment` = '"+tc.getEnviroment()+"'");
            }
            if(!"|".equals(CharacterUtil.removeNull(tc.getCreateDate()))){
                String[] d = tc.getCreateDate().split("\\|");
                if(d != null && d.length > 0){
                    SimpleDateFormat sd1 = new SimpleDateFormat("dd-mm-yyyy");
                    Date sdate = sd1.parse(d[0]);
                    SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-mm-dd");
                    SimpleDateFormat td1 = new SimpleDateFormat("dd-mm-yyyy");
                    Date tdate = td1.parse(d[1]);
                    SimpleDateFormat td2 = new SimpleDateFormat("yyyy-mm-dd");
                    sql.append(" and `create_date` between '"+sd2.format(sdate)+"' and '"+td2.format(tdate)+"'");
                }
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getCreateBy()))){
                sql.append(" and `create_by` = '"+tc.getCreateBy()+"'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getType()))){
                sql.append(" and `type` = '"+tc.getType()+"'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    public int getCountTestcase(String conditionBuilder) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int countTestcase = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) as cnt FROM testcase t");
            if (conditionBuilder != null) {
                sql.append(conditionBuilder);
            }
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            if (rs.next()) {
                countTestcase = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCountTestcase All error", e);
        } finally {
            this.close(pstm, rs);
        }
        return countTestcase;
    }
    
    public Testcase getEntityTestcase(ResultSet rs) throws SQLException {
        Testcase t = new Testcase();
        t.setCreateDate(rs.getString("create_date"));
        t.setDefectNo(rs.getString("defect_no"));
        t.setEnviroment(rs.getString("enviroment"));
        t.setIssueNo(rs.getString("issue_no"));
        t.setPathDir(rs.getString("path_dir"));
        t.setProjectId(rs.getString("project_id"));
        t.setSystems(rs.getString("systems"));
        t.setTestcaseTitle(rs.getString("testcase_title"));
        t.setTestcaseDetails(rs.getString("testcase_details"));
        t.setTestcaseId(rs.getString("testcase_id"));
        t.setStep(rs.getString("step"));
        t.setType(rs.getString("type"));
        t.setCreateBy(rs.getString("create_by"));
        
        return t;
        
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
            logger.error("getTestCase error", ex);
        }
    }
    
    public int deleteTestcase(int testcaseId) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `testcase` WHERE testcase_id=?");
            
            pstm = conn.prepareStatement(sql.toString());            
            pstm.setInt(1, testcaseId);            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteTestcase error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int createTestcase(Testcase testcase) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `testcase` ");
            sql.append(" (`testcase_title`, `testcase_details`, `systems`, `enviroment`,");
            sql.append("  `defect_no`, `issue_no`, project_id,`path_dir`, `create_by`, ");
            sql.append("  `create_date`,`step`, `type`) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,?,");
            sql.append(" ?,?,?,?,?,");
            sql.append(" ?,?,? )");

            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, testcase.getTestcaseTitle());
            pstm.setString(2, testcase.getTestcaseDetails());
            pstm.setString(3, testcase.getSystems());
            pstm.setString(4, testcase.getEnviroment());

            pstm.setString(5, testcase.getDefectNo());
            pstm.setString(6, testcase.getIssueNo());
            pstm.setString(7, testcase.getProjectId());
            pstm.setString(8, testcase.getPathDir());
            pstm.setString(9, testcase.getCreateBy());
            pstm.setString(10, testcase.getCreateDate());
            pstm.setString(11, testcase.getStep());
            pstm.setString(12, testcase.getType());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveTestcase error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }

    public int updateTestcase(Testcase testcase) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `testcase` SET ");
            sql.append(" `testcase_title`=?,`testcase_details`=?,`systems`=?,`enviroment`=?,`defect_no`=?,");
            sql.append(" issue_no=?,`project_id`=?,path_dir=?,`create_by`=?,create_date=?,`step`=?,type=?");
            sql.append(" WHERE `testcase_id`=?");

            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, testcase.getTestcaseTitle());
            pstm.setString(2, testcase.getTestcaseDetails());
            pstm.setString(3, testcase.getSystems());
            pstm.setString(4, testcase.getEnviroment());

            pstm.setString(5, testcase.getDefectNo());
            pstm.setString(6, testcase.getIssueNo());
            pstm.setString(7, testcase.getProjectId());
            pstm.setString(8, testcase.getPathDir());
            pstm.setString(9, testcase.getCreateBy());
            pstm.setString(10, testcase.getCreateDate());
            pstm.setString(11, testcase.getStep());
             pstm.setString(12, testcase.getType());
            pstm.setString(13, testcase.getTestcaseId());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveTestcase error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
     public Testcase getTestcaseAllForm(int id) {
        logger.debug("getTestcaseAll");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Testcase testcaseList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `testcase_id`, `testcase_title`, `testcase_details`, ");
            sql.append(" `systems`, `enviroment`, `defect_no`, `issue_no`, `project_id`, `type`,  ");
            sql.append(" `path_dir`, (select pf.username from profile pf where pf.profile_id = t.create_by)  as `create_by`,  ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date , `step` ");
            sql.append(" FROM `testcase` t WHERE testcase_id = ?");
            logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {      
                 testcaseList = getEntityTestcase(rs);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getTestcaseAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return testcaseList;
    }
}
