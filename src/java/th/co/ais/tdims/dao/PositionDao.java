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
import th.co.ais.tdims.model.Position;

public class PositionDao {

    final static Logger logger = Logger.getLogger(PositionDao.class);

    private Connection conn = null;

    public List<Position> getPositionAll() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Position> positionList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT p.pos_id, p.pos_name, p.pos_desc, p.dep_id, ");
            sql.append(" (SELECT dep_name FROM department d WHERE d.dep_id = p.dep_id ) as dep_name");
            sql.append(" FROM `position` p ORDER BY p.pos_name ASC");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            positionList = new ArrayList<Position>();
            while (rs.next()) {
                Position position = new Position();
                position.setPosId(rs.getString("pos_id"));
                position.setPosName(rs.getString("pos_name"));
                position.setPosDesc(rs.getString("pos_desc"));
                position.setDepId(rs.getString("dep_id"));
                position.setDepName(rs.getString("dep_name"));
                positionList.add(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getPositionAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return positionList;
    }
    
    public Position getPosition(String posId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Position position = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT p.pos_id, p.pos_name, p.pos_desc, p.dep_id, ");
            sql.append(" (SELECT dep_name FROM department d WHERE d.dep_id = p.dep_id ) as dep_name");
            sql.append(" FROM `position` p ");
            sql.append(" WHERE p.pos_id = ? ");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, posId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                position = new Position();
                position.setPosId(rs.getString("pos_id"));
                position.setPosName(rs.getString("pos_name"));
                position.setPosDesc(rs.getString("pos_desc"));
                position.setDepId(rs.getString("dep_id"));
                position.setDepName(rs.getString("dep_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getPosition error", e);
        } finally {
            this.close(pstm, rs);
        }
        return position;
    }
    
     public List<Position> getPositionByDep(String depId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Position position = null;
        List<Position> positionList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM `position` p WHERE p.dep_id = ?");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, depId);
            rs = pstm.executeQuery();
            positionList = new ArrayList<Position>();
            while (rs.next()) {
                position = new Position();
                position.setPosId(rs.getString("pos_id"));
                position.setPosName(rs.getString("pos_name"));
                position.setPosDesc(rs.getString("pos_desc"));
                positionList.add(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getPosition error", e);
        } finally {
            this.close(pstm, rs);
        }
        return positionList;
    }
    
    public int createPosition(Position position){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `position` ");
            sql.append(" ( `pos_name`, `pos_desc`, `dep_id`,create_date,create_by,update_date,update_by ) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,NOW(),?,NOW(),?)");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, position.getPosName());
            pstm.setString(2, position.getPosDesc());
            pstm.setInt(3, Integer.parseInt(position.getDepId()));
            pstm.setString(4, position.getCreateBy());
            pstm.setString(5, position.getCreateBy());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("save position error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updatePosition(Position position){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `position` SET ");
            sql.append(" `pos_name`=?, `pos_desc`=?, `dep_id`=?,update_date=NOW(),update_by=? ");
            sql.append(" WHERE `pos_id`=?");

            pstm = conn.prepareStatement(sql.toString());     
            pstm.setString(1, position.getPosName());
            pstm.setString(2, position.getPosName());
            pstm.setInt(3, Integer.parseInt(position.getDepId()));      
            pstm.setString(4, position.getUpdateBy());
            pstm.setString(5, position.getPosId());
            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("update position error", e);
        }finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int deletePosition(int posId){
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `position` WHERE pos_id=?");
            
            pstm = conn.prepareStatement(sql.toString());     
            pstm.setInt(1, posId);      
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete position error", e);
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
