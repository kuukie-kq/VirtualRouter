package com.javaweb.router.service;

import com.javaweb.router.bean.Relationship;
import com.javaweb.router.bean.RouterRelationship;
import com.javaweb.router.bean.RouterShip;
import com.javaweb.router.util.RouterWebUtils;
import version.one.bean.Router;
import version.one.bean.RouterTable;
import version.one.util.RouterUtils;

import java.util.*;

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
        List<Relationship> ships = RouterWebUtils.routerRelationshipService.getRouterShip().getShips();
        for(Router router:RouterUtils.routerDao.lookupRouterGetRouters()) {
            for(RouterTable routerTable:RouterUtils.routerTableDao.lookupRouterTableById(router.getRouterId())) {
                if(routerTable.getReachableDistance() > 0) {

                    ListIterator<Relationship> iterator = ships.listIterator();

                    for(;iterator.hasNext();) {
                        Relationship ship = iterator.next();
                        if(ship.getFromNode().equals(router.getRouterName())) {
                            if(ship.getToNode().equals(routerTable.getNextAddressName())) {
                                break;
                            } else {
                                Relationship relationship = new Relationship();
                                relationship.setFromNode(router.getRouterName());
                                relationship.setToNode(routerTable.getNextAddressName());
                                iterator.add(relationship);
                            }
                        } else if (ship.getToNode().equals(router.getRouterName())) {
                            if(ship.getFromNode().equals(routerTable.getNextAddressName())) {
                                break;
                            } else {
                                Relationship relationship = new Relationship();
                                relationship.setFromNode(router.getRouterName());
                                relationship.setToNode(routerTable.getNextAddressName());
                                iterator.add(relationship);
                            }
                        }
                    }


                }
            }
        }

        return new RouterShip(RouterUtils.routerDao.lookupRouterGetRouters(),ships);
    }
}
