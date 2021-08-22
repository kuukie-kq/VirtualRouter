package srcd.southbound;

import versionOne.srcd.southbound.router.NettyVirtualRouter;

public class NettyRTFour {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(4);
        nettyRouter.run();
    }
}
