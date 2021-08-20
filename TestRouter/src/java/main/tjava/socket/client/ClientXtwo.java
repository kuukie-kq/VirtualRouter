package main.tjava.socket.client;

import main.tjava.socket.client.Switch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientXtwo implements Switch {
    private static ServerSocket serverSocket;
    private static final String SEQUENCE = "\r\n";

    @Override
    public void send() {
        try {
            serverSocket = new ServerSocket(55131);
            Socket socket;



            //先向自己的路由器发送路由表更新信息
//            StringBuffer head = new StringBuffer();
//            head.append("GET / HTTP/1.1" + SEQUENCE);
//            head.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+SEQUENCE);
//            head.append("Accept-Language:zh-CN,zh;q=0.8"+SEQUENCE);
//            head.append("KuuRouting:RoutingTable-1,55130,55030<>"+SEQUENCE+SEQUENCE);
//
//            socket = new Socket("127.0.0.1",55031);
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





            socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = null;
            for(;(message = bufferedReader.readLine()) != null;) {
                System.out.println(message);
            }

            StringBuffer head = new StringBuffer();
            head.append("HTTP/1.1 200 OK" + SEQUENCE);
            head.append("Content-Type:text/html"+SEQUENCE);
            head.append("Date:Fri, 5 Aug 2021 12:50:12 GMT"+SEQUENCE);
            head.append("KuuRouting:55131->55130"+SEQUENCE+SEQUENCE);
            head.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"UTF-8\">\n" +
                    "\t\t<title>kknw</title>\n" +
                    "\t\t<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/kokoui.css\" />\n" +
                    "\t\t<link type=\"text/css\" rel=\"stylesheet\" href=\"./live/assets/waifu.min.css?v=1.4.2\"/>\n" +
                    "\t</head>\n" +
                    "\t<body>\n" +
                    "\t\t<div class=\"koko-div\">\n" +
                    "\t\t\t<div class=\"koko-div-header\">\n" +
                    "\t\t\t\t<div class=\"koko-header-logo\">logo</div>\n" +
                    "\t\t\t\t<div class=\"koko-header-user\">\n" +
                    "\t\t\t\t\t<ul class=\"koko-ul\">\n" +
                    "\t\t\t\t\t\t<p><img src=\"./img/20210201.png\" />管理员</p>\n" +
                    "\t\t\t\t\t\t<li class=\"koko-ul-li\"><p>个人空间</p></li>\n" +
                    "\t\t\t\t\t\t<li class=\"koko-ul-li koko-ul-li-hr\"><p>退出登录</p></li>\n" +
                    "\t\t\t\t\t</ul>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t\t<div class=\"koko-header-user\">\n" +
                    "\t\t\t\t\t<iframe src=\"./html/timetable.html\" style=\"width: 200px; height: 50px;\"></iframe>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t\t<div class=\"koko-clear-float\"></div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"koko-div-aside\">\n" +
                    "\t\t\t\t<div class=\"koko-aside-ul\">\n" +
                    "\t\t\t\t\t<ul class=\"koko-ul\">\n" +
                    "\t\t\t\t\t\tLive\n" +
                    "\t\t\t\t\t\t<li class=\"koko-ul-li\"><a href=\"\">Live2d</a></li>\n" +
                    "\t\t\t\t\t\t<li class=\"koko-ul-li\">Live3D</li>\n" +
                    "\t\t\t\t\t</ul>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"koko-div-article\">\n" +
                    "\t\t\t\t<iframe src=\"\" name=\"mainIframe\" style=\"width: 100%; height: 100%;\"></iframe>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"koko-clear-float\"></div>\n" +
                    "\t\t\t<div class=\"koko-div-footer\"></div>\n" +
                    "\t\t</div>\n" +
                    "\t\t\n" +
                    "\t\t<!-- waifu-tips.js 依赖 JQuery 库 -->\n" +
                    "\t\t<script src=\"./live/assets/jquery.min.js?v=3.3.1\"></script>\n" +
                    "\t\t\n" +
                    "\t\t<!-- 实现拖动效果，需引入 JQuery UI -->\n" +
                    "\t\t<script src=\"./live/assets/jquery-ui.min.js?v=1.12.1\"></script>\n" +
                    "\t\t\n" +
                    "\t\t<div class=\"waifu\">\n" +
                    "\t\t\t<div class=\"waifu-tips\"></div>\n" +
                    "\t\t\t<canvas id=\"live2d\" width=\"280\" height=\"250\" class=\"live2d\"></canvas>\n" +
                    "\t\t\t<div class=\"waifu-tool\">\n" +
                    "\t\t\t\t<span class=\"fui-home\"></span>\n" +
                    "\t\t\t\t<span class=\"fui-chat\"></span>\n" +
                    "\t\t\t\t<span class=\"fui-eye\"></span>\n" +
                    "\t\t\t\t<span class=\"fui-user\"></span>\n" +
                    "\t\t\t\t<span class=\"fui-photo\"></span>\n" +
                    "\t\t\t\t<span class=\"fui-info-circle\"></span>\n" +
                    "\t\t\t\t<span class=\"fui-cross\"></span>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t\t\n" +
                    "\t\t<script src=\"./live/assets/waifu-tips.min.js?v=1.4.2\"></script>\n" +
                    "\t\t<script src=\"./live/assets/live2d.min.js?v=1.0.5\"></script>\n" +
                    "\t\t<script type=\"text/javascript\">\n" +
                    "\t\t\t/* 可直接修改部分参数 */\n" +
                    "\t\t\tlive2d_settings['modelId'] = 1;                  // 默认模型 ID\n" +
                    "\t\t\tlive2d_settings['modelTexturesId'] = 72;         // 默认材质 ID 36 37 72\n" +
                    "\t\t\tlive2d_settings['modelStorage'] = false;         // 不储存模型 ID\n" +
                    "\t\t\tlive2d_settings['canCloseLive2d'] = true;        // 隐藏 关闭看板娘 按钮\n" +
                    "\t\t\tlive2d_settings['canTurnToHomePage'] = false;    // 隐藏 返回首页 按钮\n" +
                    "\t\t\tlive2d_settings['waifuSize'] = '400x360';        // 看板娘大小\n" +
                    "\t\t\tlive2d_settings['waifuTipsSize'] = '240x100';    // 提示框大小\n" +
                    "\t\t\tlive2d_settings['hitokotoAPI'] = 'hitokoto.cn';  // 提示框内容\n" +
                    "\t\t\tlive2d_settings['waifuFontSize'] = '18px';       // 提示框字体\n" +
                    "\t\t\tlive2d_settings['waifuToolFont'] = '20px';       // 工具栏字体\n" +
                    "\t\t\tlive2d_settings['waifuToolLine'] = '30px';       // 工具栏行高\n" +
                    "\t\t\tlive2d_settings['waifuToolTop'] = '-60px';       // 工具栏顶部边距\n" +
                    "\t\t\tlive2d_settings['waifuDraggable'] = 'unlimited'; // 拖拽样式\n" +
                    "\t\t\tlive2d_settings['waifuDraggableRevert'] = false; // 拖拽后是否复位\n" +
                    "\t\t\t/* 在 initModel 前添加 */\n" +
                    "\t\t\tinitModel(\"./live/assets/waifu-tips.json?v=1.4.2\")\n" +
                    "\t\t</script>\n" +
                    "\t</body>\n" +
                    "</html>"+SEQUENCE+SEQUENCE);

            socket = new Socket("127.0.0.1",55031);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(head.toString());
            printWriter.flush();
            socket.shutdownOutput();


            head = new StringBuffer();
            head.append("HTTP/1.1 200 OK" + SEQUENCE);
            head.append("Content-Type:text/css"+SEQUENCE);
            head.append("Date:Fri, 5 Aug 2021 12:50:12 GMT"+SEQUENCE);
            head.append("KuuRouting:55131->55130"+SEQUENCE+SEQUENCE);
            head.append("/*kokoui.css*/\n" +
                    "/*-全局- 外边距+内填充+边框 */\n" +
                    "* {\n" +
                    "\tmargin: 0px auto;\n" +
                    "\tpadding: 0px;\n" +
                    "\tborder: 0px;\n" +
                    "}\n" +
                    "/*-- 解决高度自适应 The start*/\n" +
                    "html {\n" +
                    "\twidth: 100%;\n" +
                    "\theight: 100%;\n" +
                    "}\n" +
                    "body {\n" +
                    "\twidth: 100%;\n" +
                    "\theight: 100%;\n" +
                    "}\n" +
                    "/*-- The end*/\n" +
                    "/*-class- 清除浮动 */\n" +
                    ".koko-clear-float {\n" +
                    "\tclear: both;\n" +
                    "}\n" +
                    "/*-class- 定义画布 */\n" +
                    ".koko-div {\n" +
                    "\tmin-width: 1000px;\n" +
                    "\tmin-height: 600px;\n" +
                    "\twidth: 100%;\n" +
                    "\theight: 100%;\n" +
                    "}\n" +
                    "/*-class- 画布的头部 */\n" +
                    ".koko-div-header {\n" +
                    "\tmin-width: 1000px;\n" +
                    "\tmin-height: 60px;\n" +
                    "\twidth: 100%;\n" +
                    "\theight: 10%;\n" +
                    "\tbackground-color: coral;\n" +
                    "}\n" +
                    "/*-class 画布的导航栏 */\n" +
                    ".koko-div-aside {\n" +
                    "\tmin-width: 200px;\n" +
                    "\tmin-height: 480px;\n" +
                    "\twidth: 20%;\n" +
                    "\theight: 80%;\n" +
                    "\tfloat: left;\n" +
                    "\tbackground-color: aqua;\n" +
                    "\toverflow: auto;\n" +
                    "}\n" +
                    "/*-class- 画布的主体内容 */\n" +
                    ".koko-div-article {\n" +
                    "\tmin-width: 800px;\n" +
                    "\tmin-height: 480px;\n" +
                    "\twidth: 80%;\n" +
                    "\theight: 80%;\n" +
                    "\tfloat: right;\n" +
                    "\tbackground-color: aquamarine;\n" +
                    "}\n" +
                    "/*-class- 画布的尾部 */\n" +
                    ".koko-div-footer {\n" +
                    "\tmin-width: 1000px;\n" +
                    "\tmin-height: 60px;\n" +
                    "\twidth: 100%;\n" +
                    "\theight: 10%;\n" +
                    "\tbackground-color: chartreuse;\n" +
                    "}\n" +
                    "/*-class- 头部的图标 */\n" +
                    ".koko-header-logo {\n" +
                    "\tmin-width: 200px;\n" +
                    "\tmin-height: 60px;\n" +
                    "\twidth: 20%;\n" +
                    "\theight: 100%;\n" +
                    "\tfloat: left;\n" +
                    "}\n" +
                    "/*-class- 头部的用户信息 */\n" +
                    ".koko-header-user {\n" +
                    "\tmargin: auto;\n" +
                    "\tmin-width: 200px;\n" +
                    "\tmin-height: 60px;\n" +
                    "\twidth: 20%;\n" +
                    "\theight: 100%;\n" +
                    "\tfloat: right;\n" +
                    "\toverflow: auto;\n" +
                    "}\n" +
                    "/*-class- 头部的图片 */\n" +
                    ".koko-header-user img {\n" +
                    "\twidth: 60px;\n" +
                    "\theight: 60px;\n" +
                    "\tborder-radius: 50%;\n" +
                    "\tfloat: left;\n" +
                    "}\n" +
                    "/*-class- 头部的文字 */\n" +
                    ".koko-header-user p {\n" +
                    "\ttext-align: center;\n" +
                    "\tline-height: 60px;\n" +
                    "}\n" +
                    "\n" +
                    ".koko-ul-li-hr {\n" +
                    "\tborder-top: solid #34495E;\n" +
                    "}\n" +
                    "/*-class- 导航的列表 */\n" +
                    ".koko-aside-ul {\n" +
                    "\tmin-width: 160px;\n" +
                    "\twidth: 80%;\n" +
                    "\tfont-size: x-large;\n" +
                    "\tbackground: #D76C9C;\n" +
                    "}\n" +
                    "/*-class- 列表 */\n" +
                    ".koko-ul {\n" +
                    "\ttext-align: center;\n" +
                    "}\n" +
                    "/*-class- 列表项 */\n" +
                    ".koko-ul-li {\n" +
                    "\ttext-align: center;\n" +
                    "\tdisplay: none;\n" +
                    "}\n" +
                    "/*-class- 列表获取焦点列表项显示 */\n" +
                    ".koko-ul:hover .koko-ul-li {\n" +
                    "\tdisplay: block;\n" +
                    "}"+SEQUENCE);


            socket = new Socket("127.0.0.1",55031);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(head.toString());
            printWriter.flush();
            socket.shutdownOutput();

            printWriter.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
