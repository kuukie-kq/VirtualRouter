package main.tjava.socket.client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public interface Switch {
    public static final String SEQUENCE = "\r\n";
    public static final ServerSocket serverSocket = null;
    public Socket socket = null;
    public StringBuffer head = null;
    public PrintWriter printWriter = null;
    public BufferedReader bufferedReader = null;
    public String message = null;
    public void send();
}
