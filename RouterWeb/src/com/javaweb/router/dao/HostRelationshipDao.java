package com.javaweb.router.dao;

import com.javaweb.router.bean.HostRelationship;
import version.one.bean.Router;
import version.one.util.MysqlConnectUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HostRelationshipDao {
    public List<HostRelationship> lookupHostShipGetHostShips() {
        List<HostRelationship> hostRelationships = new ArrayList();
        String sql = "select * from db_host_relationship";
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();) {
                HostRelationship hostRelationship = new HostRelationship();
                hostRelationship.setHostShipId(rs.getInt(1));
                hostRelationship.setHostId(rs.getInt(2));
                hostRelationship.setRouterId(rs.getInt(3));
                hostRelationships.add(hostRelationship);
            }
            MysqlConnectUtils.mysqlClose();
            return hostRelationships;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int lookupHostShipGetHostShipsNumber() {
        List<HostRelationship> hostRelationships = new ArrayList();
        String sql = "select * from db_host_relationship";
        int i = 0;
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();i++) {
                HostRelationship hostRelationship = new HostRelationship();
                hostRelationship.setHostShipId(rs.getInt(1));
                hostRelationship.setHostId(rs.getInt(2));
                hostRelationship.setRouterId(rs.getInt(3));
                hostRelationships.add(hostRelationship);
            }
            MysqlConnectUtils.mysqlClose();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<HostRelationship> lookupHostShipGetHostShipsByLimit(int page) {
        List<HostRelationship> hostRelationships = new ArrayList();
        String sql = "select * from db_host_relationship limit " + page*5 + "," + (page+1)*5;
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();) {
                HostRelationship hostRelationship = new HostRelationship();
                hostRelationship.setHostShipId(rs.getInt(1));
                hostRelationship.setHostId(rs.getInt(2));
                hostRelationship.setRouterId(rs.getInt(3));
                hostRelationships.add(hostRelationship);
            }
            MysqlConnectUtils.mysqlClose();
            return hostRelationships;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HostRelationship lookupHostShipGetHostShipById(int id) {
        HostRelationship hostRelationship = new HostRelationship();
        String sql = "select * from db_host_relationship where hostId=" + id;
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();) {
                hostRelationship.setHostShipId(rs.getInt(1));
                hostRelationship.setHostId(rs.getInt(2));
                hostRelationship.setRouterId(rs.getInt(3));
            }
            MysqlConnectUtils.mysqlClose();
            return hostRelationship;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean lookHostShipByRouterAndHostId(int hostId,int routerId) {
        String sql = "insert into db_host_relationship values(" + (lookupHostShipGetHostShipsNumber()+1) + "," + hostId + "," + routerId + ")";
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlUpdate(sql);
            MysqlConnectUtils.mysqlClose();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
