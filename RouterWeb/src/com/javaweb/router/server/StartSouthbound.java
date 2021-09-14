package com.javaweb.router.server;

import version.one.southbound.router.NettyRouter;

public class StartSouthbound {
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NettyRouter nettyRouter = new NettyRouter();
                nettyRouter.run();
            }
        }).start();
    }
}
