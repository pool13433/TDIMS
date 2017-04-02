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
    final static Logger logger = Logger.getLogger(SimDao.class);

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
    
    public Team getTeam(int teamId){        
        logger.info("getTeam==" + teamId);
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
            sql.append(" WHERE team_id = " + teamId);
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            if (rs.first()) {
                return getEntityTeam(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getTeam error");
        }
        return new Team();
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
    
}
