package com.javaweb.router.service;

import com.javaweb.router.bean.HostMessage;
import com.javaweb.router.bean.HostRelationship;
import com.javaweb.router.util.RouterWebUtils;
import version.one.bean.Host;
import version.one.bean.Router;
import version.one.bean.RouterTable;
import version.one.util.RouterUtils;

import java.util.List;

public class HostService {
    public HostMessage getMessage() {
        List<Host> hosts = RouterUtils.hostDao.lookupHostGetHostsByLimit(0);
        int hostCount = RouterUtils.hostDao.lookupHostGetHostsNumber();
        int pageCount = hostCount/5;
        List<HostRelationship> relationships = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShips();
        return new HostMessage(pageCount,5,1,hostCount,hosts,relationships);
    }

    public HostMessage getMessageByLimit(int page) {
        List<Host> hosts = RouterUtils.hostDao.lookupHostGetHostsByLimit(page);
        List<HostRelationship> relationships = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShips();
        return new HostMessage(0,5,page+1,0,hosts,relationships);
    }

    public boolean setNewHostAndShip(int hostId,String hostName,String hostAddress,int routerId) {
        Host host = new Host();
        host.setHostId(hostId);
        host.setHostName(hostName);
        host.setHostAddress(hostAddress);
        if(RouterUtils.hostDao.lookHost(host)) {
            RouterTable routerTable = new RouterTable();
            routerTable.setTableId(-1);
            routerTable.setTableName(RouterUtils.routerDao.lookupRouterById(routerId).getRouterName() + "To" + hostName);
            routerTable.setRouterId(routerId);
            routerTable.setReachableAddressName(hostName);
            routerTable.setReachableDistance(Integer.parseInt("0"));
            routerTable.setNextAddressName(hostAddress);
            RouterUtils.routerTableDao.lookRouterTable(routerTable);
            return RouterWebUtils.hostRelationshipDao.lookHostShipByRouterAndHostId(hostId,routerId);
        } else {
            return false;
        }
    }
}
