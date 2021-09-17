package com.javaweb.router.service;

import com.javaweb.router.bean.Relationship;
import com.javaweb.router.bean.RouterRelationship;
import com.javaweb.router.bean.RouterShip;
import com.javaweb.router.util.RouterWebUtils;
import version.one.bean.Router;
import version.one.bean.RouterTable;
import version.one.util.RouterUtils;

import java.util.ArrayList;
import java.util.List;

public class RouterRelationshipService {
    public RouterShip getRouterShip() {
        List<Router> routers = RouterUtils.routerDao.lookupRouterGetRouters();
        List<Relationship> ships = new ArrayList();
        for(RouterRelationship routerRelationship:RouterWebUtils.routerRelationshipDao.lookupRouterShipGetRouterShips()) {
            Relationship ship = new Relationship();
            ship.setFromNode(RouterUtils.routerDao.lookupRouterById(routerRelationship.getRouterIdFrom()).getRouterName());
            ship.setToNode(RouterUtils.routerDao.lookupRouterById(routerRelationship.getRouterIdTo()).getRouterName());
            ships.add(ship);
        }
        return new RouterShip(routers,ships);
    }

    public RouterShip autoFoundRouterShip() {
        for(RouterTable routerTable:RouterUtils.routerTableDao.lookupRouterTableGetRouterTables()) {

        }
    }
}
