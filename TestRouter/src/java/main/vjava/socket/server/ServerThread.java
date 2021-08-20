package main.vjava.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import main.vjava.bean.RoutingTable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ServerThread extends Thread {
    private Socket socket;
    private Map<String,String> map;
    private String kuu;
    private String head;

    public ServerThread(Socket socket, Map<String, String> map, String kuu, String head) {
        this.socket = socket;
        this.map = map;
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
