package test.srcd.southbound;

import main.srcd.southbound.router.NettyRouter;

public class NettyTest {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter(55031);
        nettyRouter.run();
    }
}
