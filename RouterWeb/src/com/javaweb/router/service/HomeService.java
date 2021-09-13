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
        List<HostRelationship> hostRelationshipList = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShipsByLimit(0);

        return new HeadMessage(hostsNumber,routersNumber,1,countNumber,hostRelationshipList);
    }

    public HeadMessage getMessageByLimit(int page) {
        List<HostRelationship> hostRelationships = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShipsByLimit(page);
        return  new HeadMessage(0,0,page+1,0,hostRelationships);
    }
}
