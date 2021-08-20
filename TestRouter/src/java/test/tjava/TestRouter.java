package test.tjava;

import main.tjava.socket.server.ServerXone;
import main.vjava.bean.RoutingTable;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class TestRouter {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try { List<RoutingTable> lrt = new ArrayList<>();

                RoutingTable rt = new RoutingTable();
                rt.setTableName("main");
                rt.setRoutingId(1);
                rt.setTableId(2);
                rt.setReachableDistance(0);
                rt.setReachableAddress("55130");
                rt.setNextAddress("55130");
                lrt.add(rt);

                ServerXone serverOne = new ServerXone("55030",lrt);
                serverOne.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                List<RoutingTable> lrt = new ArrayList<>();

                RoutingTable rt = new RoutingTable();
                rt.setTableName("main");
                rt.setRoutingId(1);
                rt.setTableId(2);
                rt.setReachableDistance(0);
                rt.setReachableAddress("55131");
                rt.setNextAddress("55131");
                lrt.add(rt);

                ServerXone serverOne = new ServerXone("55031",lrt);
                serverOne.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100);
    }
}
