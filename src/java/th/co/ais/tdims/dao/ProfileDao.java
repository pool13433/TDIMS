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
import th.co.ais.tdims.model.Profile;

public class ProfileDao {

    final static Logger logger = Logger.getLogger(ProfileDao.class);

    private Connection conn = null;

    public Profile getUser(String username, String password) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Profile user = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `profile_id`, `username`, `password`, `fname`, `lname`, `gender`,");
            sql.append(" `mobile`, `email`, `position`, DATE_FORMAT(create_date,'%d-%m-%Y') create_date,");
            sql.append(" `create_by`, DATE_FORMAT(update_date,'%d-%m-%Y') update_date, `update_by`, `status`  FROM profile p");
            sql.append(" WHERE p.username = ? AND p.password = md5(?)");            
            logger.info("sql ::=="+sql.toString());
            logger.info("username ::=="+username);
            logger.info("password ::=="+password);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                user = getEntityProfile(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser error", e);
        } finally {
            this.close(pstm, rs);
        }
        return user;
    }

    public int createProfile(Profile profile) {        
        PreparedStatement pstm = null;
        int exe = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `profile` ");
            sql.append(" ( `username`, `password`, `fname`, `lname`, `gender`, ");
            sql.append(" `mobile`, `email`, `position`, `status`,create_date,create_by,update_date,update_by) ");
            sql.append(" VALUES (?,md5(?),?,?,?, ");
            sql.append(" ?,?,?,?,NOW(),?,NOW(),? ) ");
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, profile.getUsername());
            pstm.setString(2, profile.getPassword());
            pstm.setString(3, profile.getFirstName());
            pstm.setString(4, profile.getLastName());
            pstm.setString(5, profile.getGender());
            pstm.setString(6, profile.getMobile());
            pstm.setString(7, profile.getEmail());
            pstm.setInt(8, Integer.parseInt(profile.getPosition()));
            pstm.setString(9, profile.getStatus());            
            pstm.setInt(10, profile.getCreateBy());        
            pstm.setInt(11, profile.getUpdateBy());                
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public int updateProfile(Profile profile) {        
        PreparedStatement pstm = null;
        int exe = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `profile` SET ");
            sql.append(" `fname`=?,`lname`=?,`gender`=?,");
            sql.append(" `mobile`=?,`email`=?,`position`=?,");
            sql.append(" update_date=NOW(),update_by=? ");
            sql.append(" WHERE profile_id=? ");
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, profile.getFirstName());
            pstm.setString(2, profile.getLastName());
            pstm.setString(3, profile.getGender());
            pstm.setString(4, profile.getMobile());
            pstm.setString(5, profile.getEmail());
            pstm.setInt(6, Integer.parseInt(profile.getPosition()));
            pstm.setInt(7, profile.getUpdateBy());         
            pstm.setString(8, profile.getProfileId());       
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }

    

    public int updatePassword(int id, String passwordNew) {
        PreparedStatement pstm = null;
        int exe = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE profile SET ");
            sql.append(" password = md5(?),update_date=NOW() ");
            sql.append(" WHERE profile_id = ? ");            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, passwordNew);  
            pstm.setInt(2, id);  
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updatePassword error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
    
    public List<Profile> getAllUser() {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Profile> userList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `profile_id`, `username`, `password`, `fname`, `lname`, `gender`,");
            sql.append(" `mobile`, `email`, `position`, DATE_FORMAT(create_date,'%d-%m-%Y') create_date,");
            sql.append(" `create_by`, DATE_FORMAT(update_date,'%d-%m-%Y') update_date, `update_by`, `status`  FROM profile p");
            sql.append(" ORDER BY p.username ");
            logger.info("sql ::==" + sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            userList = new ArrayList<Profile>();
            while (rs.next()) {
                userList.add(this.getEntityProfile(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser error", e);
        } finally {
            this.close(pstm, rs);
        }
        return userList;
    }
    
    private Profile getEntityProfile(ResultSet rs) throws SQLException {
        Profile user = new Profile();
        user.setProfileId(rs.getString("profile_id"));
        user.setCreateBy(rs.getInt("create_by"));
        user.setCreateByUsername("xxxx");
        user.setCreateDate(rs.getString("create_date"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("fname"));
        user.setGender(rs.getString("gender"));
        user.setLastName(rs.getString("lname"));
        user.setMobile(rs.getString("mobile"));
        user.setPassword(rs.getString("password"));
        user.setPosition(rs.getString("position"));
        user.setStatus(rs.getString("status"));
        user.setUsername(rs.getString("username"));
        user.setUpdateDate(rs.getString("update_date"));
        user.setUpdateBy(rs.getInt("update_by"));
        return user;
    }
    
    public Profile getUserBYId(int profile_id) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Profile userList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `profile_id`, `username`, `password`, `fname`, `lname`, `gender`,");
            sql.append(" `mobile`, `email`, `position`, DATE_FORMAT(create_date,'%d-%m-%Y') create_date,");
            sql.append(" `create_by`, DATE_FORMAT(update_date,'%d-%m-%Y') update_date, `update_by`, `status`  FROM profile p WHERE profile_id=?");
            sql.append(" ORDER BY p.username ");
            logger.info("sql ::==" + sql.toString());
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, profile_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                userList = (this.getEntityProfile(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser error", e);
        } finally {
            this.close(pstm, rs);
        }
        return userList;
    }
    
     public int updateProfileAll(Profile profile) {        
        PreparedStatement pstm = null;
        int exe = 0;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `profile` SET ");
            sql.append(" `fname`=?,`lname`=?,`gender`=?,");
            sql.append(" `mobile`=?,`email`=?,`position`=?,");
            sql.append(" update_date=NOW(),update_by=?, `username`=?, `password`=md5(?) ");
            sql.append(" WHERE profile_id=? ");
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, profile.getFirstName());
            pstm.setString(2, profile.getLastName());
            pstm.setString(3, profile.getGender());
            pstm.setString(4, profile.getMobile());
            pstm.setString(5, profile.getEmail());
            pstm.setInt(6, Integer.parseInt(profile.getPosition()));
            pstm.setInt(7, profile.getUpdateBy());
            pstm.setString(8, profile.getUsername());
             pstm.setString(9, profile.getPassword());
            pstm.setInt(10, Integer.parseInt(profile.getProfileId()));       
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser error", e);
        } finally {
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
    
    public int deleteProfile(int profileId) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `profile` WHERE profile_id=?");
            
            pstm = conn.prepareStatement(sql.toString());            
            pstm.setInt(1, profileId);            
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteTestcase error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
}
