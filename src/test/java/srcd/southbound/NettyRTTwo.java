package srcd.southbound;

import versionOne.srcd.southbound.router.NettyVirtualRouter;

public class NettyRTTwo {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(2);
        nettyRouter.run();
    }
}
