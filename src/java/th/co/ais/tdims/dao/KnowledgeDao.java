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
import th.co.ais.tdims.util.CharacterUtil;

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
            sql.append(" SELECT `id`, `file_name`, (select t.team_name from team t  where t.team_id=k.team_id) as team_id , (select m.module_name from module m  where m.id=k.type) as type, `details`, `path`, ");
            sql.append("  `create_date`, (select pf.username from profile pf where pf.profile_id = k.create_by)  as  `create_by`, `update_date`, `update_by` ");
            sql.append(" FROM `knowledge` k ");
            
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
            sql.append(" SELECT `id`, `file_name`, (select t.team_name from team t  where t.team_id=k.team_id) as team_id , (select m.module_name from module m  where m.id=k.type) as type, `details`, `path`, ");
            sql.append("  `create_date`, (select pf.username from profile pf where pf.profile_id = k.create_by)  as  `create_by`, `update_date`, `update_by` ");
            sql.append("FROM `knowledge` k WHERE id = ?");
            
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
    public List<Knowledge> findKnowledge(Knowledge knowledge, int limit, int offset) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
        try{
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, `file_name`, (select t.team_name from team t  where t.team_id=k.team_id) as team_id, (select m.module_name from module m  where m.id=k.type) as type, `details`, `path`, ");
            sql.append("  `create_date`, (select pf.username from profile pf where pf.profile_id = k.create_by)  as  `create_by`, `update_date`, `update_by` ");
            sql.append(" FROM `knowledge` k ");
            sql.append(getConditionBuilder(knowledge));            
            sql.append(" limit ").append(limit).append(" offset ").append(offset);
            logger.info("sql ::=="+sql);
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
    
    public String getConditionBuilder(Knowledge knowledge){
        StringBuilder sql = new StringBuilder(" WHERE 1=1 ");
        try {
            
            if(!"".equals(CharacterUtil.removeNull(knowledge.getFileName()))){
               sql.append(" and file_name LIKE '%"+knowledge.getFileName()+"%'"); 
            }
            if(!"".equals(CharacterUtil.removeNull(knowledge.getTeamId()))){
               sql.append(" and team_id='"+knowledge.getTeamId()+"'"); 
            }
            if(!"".equals(CharacterUtil.removeNull(knowledge.getType()))){
               sql.append(" and type='"+knowledge.getType()+"'"); 
            }
            if(!"".equals(CharacterUtil.removeNull(knowledge.getDetails()))){
               sql.append(" and details LIKE '%"+knowledge.getDetails()+"%'"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql.toString();
    }
    private Knowledge getEntityKnowledge(ResultSet rs) throws SQLException {
        Knowledge knowledge = new Knowledge();
        
        knowledge.setId(rs.getString("id"));
        knowledge.setFileName(rs.getString("file_name"));
        knowledge.setTeamId(rs.getString("team_id"));
        knowledge.setType(rs.getString("type"));
        knowledge.setDetails(rs.getString("details"));
        knowledge.setPath(rs.getString("path"));
        knowledge.setCreateDate(rs.getString("create_date"));
        knowledge.setCreateBy(rs.getString("create_by"));
        knowledge.setUpdateDate(rs.getString("update_date"));
        knowledge.setUpdateBy(rs.getString("update_by"));
        
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
            sql.append(" (`id`, `file_name`, `team_id`, `type`, `details`, `path`, `create_date`, `create_by`, `update_date`, `update_by`) ");
            sql.append(" VALUES ('',?,?,?,?,?,?,?,?,?)");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, knowledge.getFileName());
            pstm.setString(2, knowledge.getTeamId());
            pstm.setString(3, knowledge.getType());
            pstm.setString(4, knowledge.getDetails());
            pstm.setString(5, knowledge.getPath());
            pstm.setString(6, knowledge.getCreateDate());
            pstm.setString(7, knowledge.getCreateBy());
            pstm.setString(8, knowledge.getUpdateDate());
            pstm.setString(9, knowledge.getUpdateBy());
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
            sql.append(" `file_name`=?,`team_id`=?,`type`=?, ");
            sql.append(" `details`=?,`path`=?,`update_by`=?, ");
            sql.append(" `update_date`=NOW() ");
            sql.append(" WHERE `id`=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, knowledge.getFileName());
            pstm.setString(2, knowledge.getTeamId());
            pstm.setString(3, knowledge.getType());
            pstm.setString(4, knowledge.getDetails());
            pstm.setString(5, knowledge.getPath());
            pstm.setString(6, knowledge.getUpdateBy());       
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateKnowledge error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int getCountKnowledge(String conditionBuilder) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int countKnowledge = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) as cnt FROM knowledge k");
            if (conditionBuilder != null) {
                sql.append(conditionBuilder);
            }
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            if (rs.next()) {
                countKnowledge = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCountKnowledge All error", e);
        } finally {
            this.close(pstm, rs);
        }
        return countKnowledge;
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
