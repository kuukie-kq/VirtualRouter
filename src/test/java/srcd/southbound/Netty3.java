package srcd.southbound;

import versionOne.srcd.southbound.router.NettyRouter;

public class Netty3 {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter(3);
        nettyRouter.run();
    }
}
