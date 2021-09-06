package version.one.southbound;

import version.one.util.RouterUtils;
import version.one.util.TestUtilGP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Test {
    public static void main(String[] args) throws Exception {
//        // 创建数据包对象，封装要发送的数据，接收端IP，端口
//        byte[] date = "你好UDP".getBytes();
//        //创建InetAdress对象，封装自己的IP地址
//        InetAddress inet = InetAddress.getByName("127.0.0.1");
//        DatagramPacket dp = new DatagramPacket(date, date.length, inet,6000);
//        //创建DatagramSocket对象，数据包的发送和接收对象
//        DatagramSocket ds = new DatagramSocket();
//        //调用ds对象的方法send，发送数据包
//        ds.send(dp);
//        ds.close();
        //域名查询

//IP对应域名
//        InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com"); // ip or DNS name
//        for (int i = 0; i < addresses.length; i++) {
//            String hostname = addresses[i].getHostAddress();
//            System.out.println(hostname);
//        }

        String request = "GET / HTTP/1.1" + RouterUtils.SEQUENCE +
                "accept:*/*" + RouterUtils.SEQUENCE + "connection:Keep-Alive" + RouterUtils.SEQUENCE +"user-agent:Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"
                + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE;
        TestUtilGP.send("www.baidu.com","00",request);
    }
}
