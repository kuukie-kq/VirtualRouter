package version.one.northward;

import version.one.northward.router.NettyVirtualRouter;

public class RouterN1 {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(1);
        nettyRouter.run();
        NettyVirtualRouter nettyRouter2 = new NettyVirtualRouter(2);
        nettyRouter2.run();
    }
}
