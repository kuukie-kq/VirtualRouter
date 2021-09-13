package com.javaweb.router.server;

import version.one.bean.Router;
import version.one.northward.router.NettyVirtualRouter;
import version.one.southbound.router.NettyRouter;
import version.one.util.RouterUtils;

public class VirtualRouterSouth {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NettyRouter nettyRouter = new NettyRouter();
                nettyRouter.run();
            }
        }).start();

        for(Router router : RouterUtils.routerDao.lookupRouterGetRouters()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NettyVirtualRouter nettyVirtualRouter = new NettyVirtualRouter(router.getRouterId());
                    nettyVirtualRouter.run();
                }
            }).start();
        }

//        Thread.sleep(10000);

    }

}
