package com.javaweb.router.server;

import version.one.bean.Router;
import version.one.northward.router.NettyVirtualRouter;
import version.one.util.RouterUtils;

public class StartNorthward {
    public void startAll() {
        for(Router router : RouterUtils.routerDao.lookupRouterGetRouters()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NettyVirtualRouter nettyVirtualRouter = new NettyVirtualRouter(router.getRouterId());
                    nettyVirtualRouter.run();
                }
            }).start();
        }
    }
}
