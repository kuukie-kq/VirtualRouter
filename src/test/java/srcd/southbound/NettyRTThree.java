package srcd.southbound;

import versionOne.srcd.southbound.router.NettyVirtualRouter;

public class NettyRTThree {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(3);
        nettyRouter.run();
    }
}
