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
import th.co.ais.tdims.model.Department;

public class DepartmentDao {
    
    final static Logger logger = Logger.getLogger(DepartmentDao.class);

    private Connection conn = null;
    
    public Department getDepertment(String depId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Department dep = null;
        try {
            conn = new DbConnection().open();
            String sql = "SELECT * FROM department WHERE dep_id = ? ";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, depId);
            rs = pstm.executeQuery();
            while (rs.next()) { 
                dep = new Department();
                dep.setDepId(rs.getString("dep_id"));
                dep.setDepName(rs.getString("dep_name"));
                dep.setDepDesc(rs.getString("dep_desc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getDepertment error", e);
        } finally {
            this.close(pstm, rs);
        }
        return dep;
    }
    
    public List<Department> getAllDepartment() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Department> configList = null;
        try {
            conn = new DbConnection().open();
            String sql = "SELECT * FROM department ORDER BY dep_id";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            configList = new ArrayList<Department>();
            while (rs.next()) {                
                Department dep = new Department();
                dep.setDepId(rs.getString("dep_id"));
                dep.setDepName(rs.getString("dep_name"));
                dep.setDepDesc(rs.getString("dep_desc"));
                
                configList.add(dep);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getAllDepartment error", e);
        } finally {
            this.close(pstm, rs);
        }
        return configList;
    }
    
     public int createDepartment(Department dep){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `department` ");
            sql.append(" ( `dep_name`, `dep_desc` ) ");
            sql.append(" VALUES ");
            sql.append(" (?,?)");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, dep.getDepName());
            pstm.setString(2, dep.getDepDesc());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createDepartment error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updateDepartment(Department dep){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `department` SET ");
            sql.append(" `dep_name`=?,`dep_desc`=?  ");
            sql.append(" WHERE `dep_id`=?");

            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, dep.getDepName());
            pstm.setString(2, dep.getDepDesc());
            pstm.setString(3, dep.getDepId());
            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateDepartment error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int deleteDepartment(int depId){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `department` WHERE dep_id=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setInt(1, depId);      
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteDepartment error", e);
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
