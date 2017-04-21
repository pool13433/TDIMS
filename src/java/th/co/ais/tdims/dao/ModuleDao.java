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
import static th.co.ais.tdims.dao.DepartmentDao.logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Department;
import th.co.ais.tdims.model.Module;

/**
 *
 * @author Administrator
 */
public class ModuleDao {

    final static Logger logger = Logger.getLogger(ModuleDao.class);
    private Connection conn = null;
    private final String STR_TO_DATE = " '%d-%m-%Y' ";
    private final String DATE_TO_STR = " '%d-%m-%Y' ";

    public List<Module> getModuleAll() {
        logger.debug("---- getModuleAll ----");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Module> moduleList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, (select t.team_name  from team t where t.team_id = m.team_id)  as `team_id`, ");
            sql.append(" `module_desc`, `module_name`, (select pf.username from profile pf where pf.profile_id = m.create_by)  as `create_by`,");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date ");
            sql.append(" FROM `module` m ");
            logger.info("sql ::==" + sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            moduleList = new ArrayList<Module>();
            while (rs.next()) {
                moduleList.add(getEntityModule(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getModuleAll error", e);
        } finally {
            this.close(pstm, rs);
        }
        return moduleList;
    }

    public List<Module> getModuleByTeam(String teamId) {
        logger.debug("---- getModuleByTeam ----");
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<Module> moduleList = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT `id`, (select t.team_name  from team t where t.team_id = m.team_id)  as `team_id`, ");
            sql.append(" `module_desc`, `module_name`, (select pf.username from profile pf where pf.profile_id = m.create_by)  as `create_by`,");
            sql.append(" DATE_FORMAT(create_date,").append(DATE_TO_STR).append(") create_date ");
            sql.append(" FROM `module` m ");
            sql.append(" WHERE team_id ='" + teamId + "'");
            logger.info("sql ::==" + sql);
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            moduleList = new ArrayList<Module>();
            while (rs.next()) {
                moduleList.add(getEntityModule(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getModuleByTeam error", e);
        } finally {
            this.close(pstm, rs);
        }
        return moduleList;
    }

    public Module getEntityModule(ResultSet rs) {
        Module m = new Module();
        try {
            m.setId(rs.getString("id"));
            m.setTeamId(rs.getString("team_id"));
            m.setModuleName(rs.getString("module_name"));
            m.setModuleDesc(rs.getString("module_desc"));
            m.setCreateDate(rs.getString("create_date"));
            m.setCreateBy(rs.getString("create_by"));

        } catch (Exception e) {
            logger.error("getEntityModule : " + e.getMessage());
        }
        return m;
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

    public int deleteModule(int moduleId) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM `module` WHERE id=?");

            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, moduleId);
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteModule error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }

    public Module getModule(String moduleId) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Module module = null;
        try {
            conn = new DbConnection().open();
            String sql = "SELECT * FROM `module`  WHERE id = ? ";
            //logger.info("sql ::=="+sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, moduleId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                module = this.getEntityModule(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getModule error", e);
        } finally {
            this.close(pstm, rs);
        }
        return module;
    }

    public int createModule(Module module) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO `module` ");
            sql.append(" ( `team_id`, module_name, `module_desc`,create_date,create_by) ");
            sql.append(" VALUES ");
            sql.append(" (?,?,?,NOW(),?)");

            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, module.getTeamId());
            pstm.setString(2, module.getModuleName());
            pstm.setString(3, module.getModuleDesc());
            pstm.setString(4, module.getCreateBy());
            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createModule error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }

    public int updateModule(Module module) {
        int exe = 0;
        PreparedStatement pstm = null;
        try {
            conn = new DbConnection().open();
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `module` SET ");
            sql.append(" `team_id`=?,module_name=?,`module_desc`=?");
            sql.append(" WHERE `id`=?");

            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, module.getTeamId());
            pstm.setString(2, module.getModuleName());
            pstm.setString(3, module.getModuleDesc());
            pstm.setString(4, module.getId());

            exe = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateModule error", e);
        } finally {
            this.close(pstm, null);
        }
        return exe;
    }
}
