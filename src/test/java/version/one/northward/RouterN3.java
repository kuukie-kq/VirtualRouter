package version.one.northward;

import version.one.northward.router.NettyVirtualRouter;

public class RouterN3 {
    public static void main(String[] args) {
        NettyVirtualRouter nettyRouter = new NettyVirtualRouter(3);
        nettyRouter.run();
    }
}
