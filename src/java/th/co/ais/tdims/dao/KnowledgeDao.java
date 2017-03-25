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
import th.co.ais.tdims.model.Knowledge;

public class KnowledgeDao {
    final static Logger logger = Logger.getLogger(KnowledgeDao.class);
    
    private Connection conn = null;
    
    public List<Knowledge> getKnowledgeAll(){
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
        try{
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, `project_id`, `server_name`, `path_folder`, `create_by`");
            sql.append("FROM `knowledge` ");
            
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                knowledgeList.add(getEntityKnowledge(rs));
            }
        }catch(Exception e){
             e.printStackTrace();
            logger.error("getKnowledgeAll error", e);
        }
        return knowledgeList;
    }
    
     public Knowledge getKnowledge(int id){
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Knowledge knowledgeList = new Knowledge();
        try{
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, `project_id`, `server_name`, `path_folder`, `create_by`");
            sql.append("FROM `knowledge` WHERE id = ?");
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                knowledgeList=getEntityKnowledge(rs);
            }
        }catch(Exception e){
             e.printStackTrace();
            logger.error("getKnowledgeAll error", e);
        }
        return knowledgeList;
    }
    public List<Knowledge> findKnowledge(boolean createBy, boolean projectID, String searchValue){
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
        try{
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, `project_id`, `server_name`, `path_folder`, `create_by`");
            sql.append("FROM `test_knowledge` ");
            sql.append(" WHERE 1=1 ");
            
            if(createBy){
               sql.append(" and create_by="+searchValue); 
            }
            if(projectID){
               sql.append(" and project_id="+searchValue); 
            }
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                knowledgeList.add(getEntityKnowledge(rs));
            }
        }catch(Exception e){
             e.printStackTrace();
            logger.error("getKnowledgeAll error", e);
        }
        return knowledgeList;
    }
    
    private Knowledge getEntityKnowledge(ResultSet rs) throws SQLException {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(rs.getInt("id"));
        knowledge.setProjectId(rs.getString("project_id"));
        knowledge.setServerName(rs.getString("server_name"));
        knowledge.setPathFolder(rs.getString("path_folder"));
        knowledge.setCreateBy(rs.getInt("create_by"));
        
        return knowledge;
    }
    
    public int deleteKnowledge(int knowledgeId) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `knowledge` WHERE id=?");

            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, knowledgeId);
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveknowledge error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
     
     public int createKnowledge(Knowledge knowledge){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO knowledge ");
            sql.append(" (`project_id`, `server_name`, `path_folder`, `create_by`) ");
            sql.append(" VALUES (?,?,?,?)");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, knowledge.getProjectId());
            pstm.setString(2, knowledge.getServerName());
            pstm.setString(3, knowledge.getPathFolder());
            pstm.setInt(4, knowledge.getCreateBy());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveknowledge error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
   
    public int updateKnowledge(Knowledge knowledge){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `knowledge` SET ");
            sql.append(" `project_id`=?,`server_name`=?,`path_folder`=?,`create_by`=? ");
            sql.append(" WHERE `id`=?");
            
            pstm = conn.prepareStatement(sql.toString());     
             pstm.setString(1, knowledge.getProjectId());
            pstm.setString(2, knowledge.getServerName());
            pstm.setString(3, knowledge.getPathFolder());
            pstm.setInt(4, knowledge.getCreateBy());
             pstm.setInt(5, knowledge.getId());
           
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveknowledge error", e);
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
