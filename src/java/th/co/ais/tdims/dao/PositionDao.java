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
import static th.co.ais.tdims.dao.ProfileDao.logger;
import th.co.ais.tdims.db.DbConnection;
import th.co.ais.tdims.model.Position;
import th.co.ais.tdims.model.Profile;

/**
 *
 * @author POOL_LAPTOP
 */
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
