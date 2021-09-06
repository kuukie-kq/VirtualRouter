package version.one.northward;

import version.one.northward.router.NettyVirtualRouter;

public class RouterN2 {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(2);
        nettyRouter.run();
    }
}
