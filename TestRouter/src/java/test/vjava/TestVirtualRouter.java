package test.vjava;

import java.io.IOException;
import main.vjava.bean.RoutingTable;
import main.vjava.socket.server.ServerXone;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class TestVirtualRouter {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(55030);
                    List<RoutingTable> lrt = new ArrayList<>();

                    RoutingTable rt = new RoutingTable();
                    rt.setTableName("main");
                    rt.setRoutingId(1);
                    rt.setTableId(2);
                    rt.setReachableDistance(0);
                    rt.setReachableAddress("55130");
                    rt.setNextAddress("55130");
                    lrt.add(rt);

                    ServerXone serverOne = new ServerXone(serverSocket,lrt);
                    serverOne.listen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(55031);
                    List<RoutingTable> lrt = new ArrayList<>();

                    RoutingTable rt = new RoutingTable();
                    rt.setTableName("main");
                    rt.setRoutingId(1);
                    rt.setTableId(2);
                    rt.setReachableDistance(0);
                    rt.setReachableAddress("55131");
                    rt.setNextAddress("55131");
                    lrt.add(rt);

                    ServerXone serverOne = new ServerXone(serverSocket,lrt);
                    serverOne.listen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100);
    }
}
