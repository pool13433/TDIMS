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
import th.co.ais.tdims.model.Team;

public class TeamDao {
    final static Logger logger = Logger.getLogger(TeamDao.class);

    private Connection conn = null;
    
    private final String STR_TO_DATE = " '%d-%m-%Y' ";
    private final String DATE_TO_STR = " '%d-%m-%Y' ";
    
    public List<Team> getTeamAll(){        
        logger.info("getTeamAll");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Team> teamList = new ArrayList<Team>();
        try {
            //SELECT `team_id`, `team_name`, `team_email`, `create_date`, `create_by`, `update_date`, `update_by` FROM `team`
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `team_id`, `team_name`, `team_email`, ");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date, DATE_FORMAT(update_date,").append(DATE_TO_STR).append(") update_date, ");
            sql.append(" `create_by`, `update_by`");
            sql.append(" FROM `team` ");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                teamList.add(getEntityTeam(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getTeamAll error");
        }
        return teamList;
    }
    
    private Team getEntityTeam(ResultSet rs) throws SQLException {
        
        Team t = new Team();
        t.setTeamId(rs.getString("team_id"));
        t.setTeamName(rs.getString("team_name"));
        t.setTeamEmail(rs.getString("team_email"));
        t.setCreateBy(rs.getString("create_by"));
        t.setCreateDate(rs.getString("create_date"));
        t.setUpdateBy(rs.getString("update_by"));
        t.setUpdateDate(rs.getString("update_date"));
        
        return t;
    }
    
    public Team getTeam(String teamId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        
        try {
            conn = new DbConnection().open();
            String sql = "SELECT * FROM team WHERE team_id = ? ";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, teamId);
            rs = pstm.executeQuery();
            while (rs.next()) { 
               return getEntityTeam(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getTeam error", e);
        } finally {
            this.close(pstm, rs);
        }
        return null;
    }
    
    public int createTeam(Team team){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `team` ");
            sql.append(" ( `team_name`, team_email, create_date, create_by, update_date, update_by ) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,NOW(),?,NOW(),?)");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, team.getTeamName());
            pstm.setString(2, team.getTeamEmail());
            pstm.setString(3, team.getCreateBy());
            pstm.setString(4, team.getCreateBy());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createTeam error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updateTeam(Team team){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `team` SET ");
            sql.append(" `team_name`=?,`team_email`=?,update_date=NOW(),update_by =? ");
            sql.append(" WHERE `team_id`=?");

            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, team.getTeamName());
            pstm.setString(2, team.getTeamEmail());
            pstm.setString(3, team.getUpdateBy());
            pstm.setString(4, team.getTeamId());
            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateTeam error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int deleteTeam(int teamId){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `team` WHERE team_id=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setInt(1, teamId);      
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteTeam error", e);
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
