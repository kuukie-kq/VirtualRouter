package com.javaweb.router.service;

import com.javaweb.router.bean.HostMessage;
import com.javaweb.router.bean.HostRelationship;
import com.javaweb.router.util.RouterWebUtils;
import version.one.bean.Host;
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
        List<Host> hosts = RouterUtils.hostDao.lookupHostGetHostsByLimit(page-1);
        List<HostRelationship> relationships = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShips();
        return new HostMessage(0,5,page,0,hosts,relationships);
    }
}
