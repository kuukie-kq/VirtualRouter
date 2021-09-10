package com.javaweb.router.dao;

import com.javaweb.router.bean.RouterRelationship;
import version.one.util.MysqlConnectUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouterRelationshipDao {
    public List<RouterRelationship> lookupRouterShipGetRouterShips() {
        List<RouterRelationship> routerRelationships = new ArrayList();
        String sql = "select * from db_router_relationship";
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();) {
                RouterRelationship routerRelationship = new RouterRelationship();
                routerRelationship.setRouterShipId(rs.getInt(1));
                routerRelationship.setRouterIdFrom(rs.getInt(2));
                routerRelationship.setRouterIdTo(rs.getInt(3));
                routerRelationships.add(routerRelationship);
            }
            MysqlConnectUtils.mysqlClose();
            return routerRelationships;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
