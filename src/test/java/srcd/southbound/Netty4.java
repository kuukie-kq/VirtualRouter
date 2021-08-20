package srcd.southbound;

import versionOne.srcd.southbound.router.NettyRouter;

public class Netty4 {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter(4);
        nettyRouter.run();
    }
}
