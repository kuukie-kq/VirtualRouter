package main.vjava.socket.server;

import main.vjava.bean.RoutingTable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerXone implements Router {
    private ServerSocket serverSocket;
    private Map<String,String> tableMap;
    private List<RoutingTable> lRoutingTable;
    private static final String SEQUENCE = "\r\n";

    public ServerXone(ServerSocket serverSocket, List<RoutingTable> lRoutingTable) {
        this.serverSocket = serverSocket;
        this.lRoutingTable = lRoutingTable;
        this.tableMap = new HashMap();
        autoTableMap();
    }

    private void autoTableMap() {
        tableMap.clear();
        for (RoutingTable rt : lRoutingTable) {
            tableMap.put(rt.getReachableAddress(),rt.getNextAddress());
        }
    }


    @Override
    public void listen() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for(;;) {
                Socket socket = serverSocket.accept();

                //解包
                boolean flag = false;
                String kuu = null,rout = null;
                LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
                StringBuffer headBuffer = new StringBuffer();
                for(String line = lineNumberReader.readLine();line != null;line=lineNumberReader.readLine()) {
                    headBuffer.append(line).append(SEQUENCE);
                    //匹配协议
                    if(line.startsWith("KuuRouting")) {
                        kuu = line.split(":")[1].trim();
                        //匹配路由更新
                        if(kuu.startsWith("RoutingTable")) {
                            rout = kuu.split("-")[1].trim();
                            String[] routingMap = rout.split("<>");
                            //获取更新数据
                            for (String stringMap:routingMap) {
                                String[] routingTableString = stringMap.split(",");
                                RoutingTable routingTable = new RoutingTable();
                                if (routingTableString.length == 3) {
                                    routingTable.setReachableDistance(new Integer(routingTableString[0]));
                                    routingTable.setReachableAddress(routingTableString[1]);
                                    routingTable.setNextAddress(routingTableString[2]);
                                } else {
                                    System.err.println("length != 3 error");
                                    continue;
                                }
                                //与原有数据对比
                                boolean flagAddTable = true;
                                int routingId = 0, tableId = 0;
                                for (RoutingTable table : lRoutingTable) {
                                    if (table.getReachableAddress().equals(routingTable.getReachableAddress())) {
                                        flagAddTable = false;
                                        if (table.getNextAddress().equals(routingTable.getNextAddress())) {
                                            table.setReachableDistance(routingTable.getReachableDistance());
                                        } else {
                                            if (table.getReachableDistance() > routingTable.getReachableDistance()) {
                                                table.setReachableDistance(routingTable.getReachableDistance());
                                            }
                                        }
                                    }
                                    routingId = table.getRoutingId();
                                    tableId = table.getTableId();
                                }
                                if (flagAddTable) {
                                    routingTable.setRoutingId(routingId);
                                    routingTable.setTableId(tableId);
                                    routingTable.setTableName("auto");
                                    lRoutingTable.add(routingTable);
                                }
                            }
                        } else {
                            flag = true;
                        }
                    }
                }

                if(flag) {
                    autoTableMap();
                    ServerThread serverThread = new ServerThread(socket,tableMap,kuu,headBuffer.toString());
                    threadPool.submit(serverThread);
                } else {
                    if (kuu.startsWith("RoutingTable")) {
                        System.out.println("<:->The routing has been update");
                    } else {
                        System.err.println("There are not have key:KuuRouting");
                    }
                    socket.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        threadPool.shutdown();
    }
}
