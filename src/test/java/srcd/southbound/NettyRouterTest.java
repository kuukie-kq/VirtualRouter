package srcd.southbound;

import versionOne.srcd.southbound.router.NettyRouter;

public class NettyRouterTest {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter(1);
        nettyRouter.run();
    }
}
