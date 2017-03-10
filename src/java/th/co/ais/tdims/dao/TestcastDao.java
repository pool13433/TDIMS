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
            sql.append(" SELECT `testcase_id`, `project_id`, `user_id`, ");
            sql.append(" `systems`, `enviroment`, `issue_no`, `path_dir`, ");
            sql.append(" `testcase_details`, `testcase_title`,`defect_no`,  ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date ");
            sql.append(" FROM `testcase` ");
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
    public List<Testcase> findTestcase(Testcase tc) {
        logger.debug("findTestcase");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Testcase> testcaseList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `testcase_id`, `project_id`, `user_id`, ");
            sql.append(" `systems`, `enviroment`, `issue_no`, `path_dir`, ");
            sql.append(" `testcase_details`, `testcase_title`,`defect_no`,  ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date ");
            sql.append(" FROM `testcase` ");            
            sql.append(" WHERE 1=1 ");
            
            if(!"".equals(CharacterUtil.removeNull(tc.getProjectId()))){
                sql.append(" and `project_id` = '"+tc.getProjectId()+"'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getTestcaseTitle()))){
                sql.append(" and `testcase_title` LIKE '%"+tc.getTestcaseTitle()+"%'");
            }
            if(!"".equals(CharacterUtil.removeNull(tc.getTestcaseDetails()))){
                sql.append(" and `testcase_details` LIKE '%"+tc.getTestcaseDetails()+"%'");
            }
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
        t.setUserId(rs.getString("user_id"));
        
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
    
    
}
