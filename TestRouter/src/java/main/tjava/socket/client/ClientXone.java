package main.tjava.socket.client;

import main.tjava.socket.client.Switch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientXone implements Switch {
    private static ServerSocket serverSocket;
    private static final String SEQUENCE = "\r\n";

    @Override
    public void send() {
        try {
            serverSocket = new ServerSocket(55130);
            Socket socket;



            //先向自己的路由器发送路由表更新信息
//            StringBuffer head = new StringBuffer();
//            head.append("GET / HTTP/1.1" + SEQUENCE);
//            head.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+SEQUENCE);
//            head.append("Accept-Language:zh-CN,zh;q=0.8"+SEQUENCE);
//            head.append("KuuRouting:RoutingTable-1,55131,55031<>"+SEQUENCE+SEQUENCE);
//
//            socket = new Socket("127.0.0.1",55030);
//            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
//            printWriter.write(head.toString());
//            printWriter.flush();
//            socket.shutdownOutput();
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String message = null;
//            for(;(message = bufferedReader.readLine()) != null;) {
//                System.out.println(message);
//            }




            StringBuffer head;
            PrintWriter printWriter;

            head = new StringBuffer();
            head.append("GET / HTTP/1.1" + SEQUENCE);
            head.append("Host:" + "127.0.0.1" + SEQUENCE);
            head.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+SEQUENCE);
            head.append("Accept-Language:zh-CN,zh;q=0.8"+SEQUENCE);
            head.append("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36"+SEQUENCE);
            head.append("KuuRouting:55130->55131"+SEQUENCE+SEQUENCE);

            socket = new Socket("127.0.0.1",55030);
            printWriter = new PrintWriter(socket.getOutputStream());
            System.out.println(head.toString());
            printWriter.write(head.toString());
            printWriter.flush();
            socket.shutdownOutput();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = null;
            for(;(message = bufferedReader.readLine()) != null;) {
                System.out.println(message);
            }

            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = null;
            for(;(message = bufferedReader.readLine()) != null;) {
                System.out.println(message);
            }

            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = null;
            for(;(message = bufferedReader.readLine()) != null;) {
                System.out.println(message);
            }

            printWriter.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
