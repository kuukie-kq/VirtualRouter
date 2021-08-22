package srcd.southbound;

import versionOne.srcd.southbound.router.NettyVirtualRouter;

public class NettyRTOne {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(1);
        nettyRouter.run();
    }
}
