package com.javaweb.router.service;

import com.javaweb.router.bean.RouterMessage;
import version.one.bean.Router;
import version.one.util.RouterUtils;

import java.util.List;

public class RouterService {
    public RouterMessage getMessage() {
        List<Router> routers = RouterUtils.routerDao.lookupRouterGetRoutersByLimit(0);
        int hostCount = RouterUtils.routerDao.lookupRouterGetRoutersNumber();
        int pageCount = hostCount/5;
        return new RouterMessage(pageCount,5,1,hostCount,routers);
    }

    public RouterMessage getMessageByLimit(int page) {
        List<Router> routers = RouterUtils.routerDao.lookupRouterGetRoutersByLimit(page);
        return new RouterMessage(0,5,page+1,0,routers);
    }

    public boolean setNewRouter(int routerId,String routerName,String routerAddress) {
        Router router = new Router();
        router.setRouterId(routerId);
        router.setRouterName(routerName);
        router.setRouterAddress(routerAddress);
        return RouterUtils.routerDao.lookRouter(router);
    }
}
