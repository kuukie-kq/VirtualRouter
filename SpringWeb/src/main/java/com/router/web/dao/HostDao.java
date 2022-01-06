package com.router.web.dao;

import com.router.web.bean.Host;
import com.router.web.util.MysqlConnectUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HostDao {
    private MysqlConnectUtils mysqlConnectUtils = new MysqlConnectUtils();

    public List<Host> lookupHostGetHosts() {
        List<Host> hosts = new ArrayList();
        String sql = "select * from db_host";
        try {
            mysqlConnectUtils.mysqlInit();
            mysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = mysqlConnectUtils.getRs();
            for(;rs.next();) {
                Host host = new Host();
                host.setHostId(rs.getInt(1));
                host.setHostName(rs.getString(2));
                host.setHostAddress(rs.getString(3));
                hosts.add(host);
            }
            mysqlConnectUtils.mysqlClose();
            return hosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Host> lookupHostGetHostsByLimit(int page) {
        List<Host> hosts = new ArrayList();
        String sql = "select * from db_host limit " + page*5 + "," + (page+1)*5;
        try {
            mysqlConnectUtils.mysqlInit();
            mysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = mysqlConnectUtils.getRs();
            for(;rs.next();) {
                Host host = new Host();
                host.setHostId(rs.getInt(1));
                host.setHostName(rs.getString(2));
                host.setHostAddress(rs.getString(3));
                hosts.add(host);
            }
            mysqlConnectUtils.mysqlClose();
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
            mysqlConnectUtils.mysqlInit();
            mysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = mysqlConnectUtils.getRs();
            for(;rs.next();) {
                host.setHostId(rs.getInt(1));
                host.setHostName(rs.getString(2));
                host.setHostAddress(rs.getString(3));
            }
            mysqlConnectUtils.mysqlClose();
            return host;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int lookupHostGetHostsNumber() {
        List<Host> hosts = new ArrayList();
        String sql = "select * from db_host";
        int i = 0;
        try {
            mysqlConnectUtils.mysqlInit();
            mysqlConnectUtils.mysqlSelect(sql);
            ResultSet rs = mysqlConnectUtils.getRs();
            for(;rs.next();i++) {
                Host host = new Host();
                host.setHostId(rs.getInt(1));
                host.setHostName(rs.getString(2));
                host.setHostAddress(rs.getString(3));
                hosts.add(host);
            }
            mysqlConnectUtils.mysqlClose();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean lookHost(Host host) {
        String sql = "insert into db_host values(" + host.getHostId() + ",'" + host.getHostName() + "','" + host.getHostAddress() + "')";
        try {
            mysqlConnectUtils.mysqlInit();
            mysqlConnectUtils.mysqlUpdate(sql);
            mysqlConnectUtils.mysqlClose();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
