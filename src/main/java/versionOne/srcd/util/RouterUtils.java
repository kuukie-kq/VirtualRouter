package versionOne.srcd.util;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import versionOne.srcd.dao.HostDao;
import versionOne.srcd.dao.RouterDao;
import versionOne.srcd.dao.RouterTableDao;

import java.nio.charset.Charset;

public class RouterUtils {
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Charset charset = Charset.forName("utf-8");
    public static final String SEQUENCE = "\r\n";
    public static HostDao hostDao = new HostDao();
    public static RouterDao routerDao = new RouterDao();
    public static RouterTableDao routerTableDao = new RouterTableDao();
}
