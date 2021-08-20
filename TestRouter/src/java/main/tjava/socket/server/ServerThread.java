package main.tjava.socket.server;

import main.vjava.bean.RoutingTable;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ServerThread extends Thread {
    private Socket socket;
    private Map<String,String> map;
    private String name;
    private String kuu;
    private String head;
    private static final String SEQUENCE = "\r\n";

    public ServerThread(Socket socket, Map<String, String> map, String name, String kuu, String head) {
        this.socket = socket;
        this.map = map;
        this.name = name;
        this.kuu = kuu;
        this.head = head;
    }

    @Override
    public void run() {
        try {
            String[] fat = kuu.split("->");
            if(fat.length != 2) {
                System.err.println("The first line is not *->*");
                return;
            } else {
                if(map.get(fat[1]) == null) {
                    System.err.println(fat[1]+" address not found\n");

                    //路由表自学习
                    /**
                     * 通过查表找到所有的路由器
                     * 向所有的路由器发送自己的路由表更新信息
                     *        路由表更新的时候就要反发更新后的路由表
                     */
                    String[] serverXname = {"55030","55031"};
                    for(String portName:serverXname) {
                        if(portName.equals(name)) {
                            continue;
                        } else {
                            StringBuffer headT = new StringBuffer();
                            headT.append("KuuRouting:RoutingTable-");
                            for(Map.Entry<String,String> entry:map.entrySet()) {
                                headT.append("1,"+entry.getKey()+","+name+"<>");
                            }
                            headT.append(SEQUENCE+SEQUENCE);

                            Socket tSocket = new Socket("127.0.0.1",new Integer(portName));
                            PrintWriter printWriter = new PrintWriter(tSocket.getOutputStream());
                            printWriter.write(headT.toString());
                            printWriter.flush();
                            tSocket.shutdownOutput();

                            //解包
                            boolean flag = false;
                            String kuuT = null,rout = null;
                            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(tSocket.getInputStream()));
                            StringBuffer headBuffer = new StringBuffer();
                            for(String line = lineNumberReader.readLine();line != null;line=lineNumberReader.readLine()) {
                                headBuffer.append(line).append(SEQUENCE);
                                //匹配协议
                                System.out.println("start");

                                if(line.startsWith("KuuRouting")) {
                                    kuuT = line.split(":")[1].trim();
                                    //匹配路由更新
                                    if(kuuT.startsWith("RoutingTable")) {
                                        rout = kuuT.split("-")[1].trim();
                                        String[] routingMap = rout.split("<>");
                                        //获取更新数据
                                        for (String stringMap : routingMap) {
                                            System.out.println(stringMap);
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
                                            if (routingTable.getReachableAddress().equals(fat[1])) {
                                                int port = new Integer(routingTable.getNextAddress());
                                                Socket sSocket = new Socket(socket.getInetAddress().getHostAddress(),port);
                                                printWriter = new PrintWriter(sSocket.getOutputStream());
                                                printWriter.write(head);
                                                printWriter.flush();

                                                printWriter.close();
                                                sSocket.close();
                                                System.out.println("success");
                                                return;
                                            }

                                        }
                                    }
                                }
                            }


                        }
                    }

                    System.err.println("|:<|address unreachable");
                    return;
                }
                int port = new Integer(map.get(fat[1]));
                Socket tSocket = new Socket(socket.getInetAddress().getHostAddress(),port);
                PrintWriter printWriter = new PrintWriter(tSocket.getOutputStream());
                printWriter.write(head);
                printWriter.flush();

                printWriter.close();
                tSocket.close();
            }

            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
