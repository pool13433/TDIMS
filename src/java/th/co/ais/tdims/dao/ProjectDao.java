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
import th.co.ais.tdims.model.Project;

/**
 *
 * @author POOL_LAPTOP
 */
public class ProjectDao {

    final static Logger logger = Logger.getLogger(ProjectDao.class);

    private Connection conn = null;

    public List<Project> getProjectAll() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Project> projectList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `proj_id`, `proj_name`, `proj_desc`, `proj_status` FROM `project`  ORDER BY proj_name ASC");            
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            projectList = new ArrayList<Project>();
            while (rs.next()) {
                projectList.add(getEntityProject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getProjectAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return projectList;
    }
    
    public Project getProject(int projectId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Project project = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `proj_id`, `proj_name`, `proj_desc`, `proj_status` FROM `project`  WHERE proj_id=?");            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, projectId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                project = getEntityProject(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getProject error", e);
        } finally {
            this.close(pstm, rs);
        }
        return project;
    }

    private Project getEntityProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setProjId(rs.getString("proj_id"));
        project.setProjName(rs.getString("proj_name"));
        project.setProjDesc(rs.getString("proj_desc"));
        project.setProjStatus(rs.getString("proj_status"));
        return project;
    }
    
    
    public int createProject(Project project){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `project` ");
            sql.append(" (`proj_name`, `proj_desc`, `proj_status`) ");
            sql.append(" VALUES (?,?,?)");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, project.getProjName());
            pstm.setString(2, project.getProjDesc());
            pstm.setString(3, project.getProjStatus());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveProject error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updateProject(Project project){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `project` SET ");
            sql.append(" `proj_name`=?,`proj_desc`=?,`proj_status`=? ");
            sql.append(" WHERE `proj_id`=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, project.getProjName());
            pstm.setString(2, project.getProjDesc());
            pstm.setString(3, project.getProjStatus());
            pstm.setInt(4, Integer.parseInt(project.getProjId()));
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveProject error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    
    public int deleteProject(int projId){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `project` WHERE proj_id=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setInt(1, projId);      
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveProject error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }

    public List<Project> findProject(String projectName) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Project> projectList = new ArrayList<Project>();
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `proj_id`, `proj_name`, `proj_desc`, `proj_status` FROM `project`  WHERE proj_name=?");            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, projectName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                projectList.add(getEntityProject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getProject error", e);
        } finally {
            this.close(pstm, rs);
        }
        return projectList;
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
