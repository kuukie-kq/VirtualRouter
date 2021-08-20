package srcd.southbound;

import versionOne.srcd.southbound.router.NettyRouter;

public class Netty2 {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter(2);
        nettyRouter.run();
    }
}
