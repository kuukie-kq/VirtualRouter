package main.vjava.socket.server;

import main.vjava.bean.RoutingTable;
import java.util.ArrayList;
import java.util.List;

public interface Router {
    public static final String SEQUENCE = "\r\n";
    public List<RoutingTable> lRoutingTable = new ArrayList();
    public void listen();
}
