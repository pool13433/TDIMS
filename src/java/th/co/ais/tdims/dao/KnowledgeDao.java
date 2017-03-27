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
    
    public List<Knowledge> findKnowledge(boolean createBy, boolean projectID, String searchValue){
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
        try{
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, `project_id`, `server_name`, `path_folder`, `create_by`");
            sql.append("FROM `knowledge` ");
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
}
