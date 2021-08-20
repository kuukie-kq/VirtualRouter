package versionOne.srcd.dao;

import versionOne.srcd.bean.Host;
import versionOne.srcd.util.MysqlConnectUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HostDao {
    public List<Host> lookupHostGetHosts() {
        List<Host> hosts = new ArrayList();
        String sql = "select * from db_host";
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();) {
                Host host = new Host();
                host.setHostId(rs.getInt(1));
                host.setHostName(rs.getString(2));
                host.setHostAddress(rs.getString(3));
                hosts.add(host);
            }
            MysqlConnectUtils.mysqlClose();
            return hosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Host lookupHostByName(String name) {
        Host host = new Host();
        String sql = "select * from db_host where hostName='" + name + "'";
        try {
            MysqlConnectUtils.mysqlInit();
            MysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = MysqlConnectUtils.getRs();
            for(;rs.next();) {
                host.setHostId(rs.getInt(1));
                host.setHostName(rs.getString(2));
                host.setHostAddress(rs.getString(3));
            }
            MysqlConnectUtils.mysqlClose();
            return host;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void lookHost() {

    }
}
