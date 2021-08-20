package test.srcd.southbound;

import main.srcd.southbound.router.NettyRouter;

public class NettyRouterTest {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter(55030);
        nettyRouter.run();
    }
}
