package test.srcd.util;

import main.srcd.bean.Host;
import main.srcd.dao.HostDao;

public class MysqlTest {
    public static void main(String[] args) {
        HostDao hostDao = new HostDao();
        for(Host host:hostDao.lookupHostGetHosts()) {
            System.out.print(" id:"+host.getHostId());
            System.out.print(" name:"+host.getHostName());
            System.out.println(" address:"+host.getHostAddress());
        }
    }
}
