package version.one.northward;

import version.one.northward.router.NettyVirtualRouter;

public class RouterN4 {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(4);
        nettyRouter.run();
    }
}
