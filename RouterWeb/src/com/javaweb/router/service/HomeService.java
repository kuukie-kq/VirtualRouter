package com.javaweb.router.service;

import com.javaweb.router.bean.HeadMessage;
import com.javaweb.router.bean.HostRelationship;
import com.javaweb.router.util.RouterWebUtils;
import version.one.util.RouterUtils;

import java.util.List;

public class HomeService {
    public HeadMessage getMessage() {
        int hostsNumber = RouterUtils.hostDao.lookupHostGetHostsNumber();
        int routersNumber = RouterUtils.routerDao.lookupRouterGetRoutersNumber();
        int countNumber = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShipsNumber();
        List<HostRelationship> hostRelationshipList = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShips();

        return new HeadMessage(hostsNumber,routersNumber,1,countNumber,hostRelationshipList);
    }
}
