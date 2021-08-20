package test.tjava;

import main.tjava.socket.client.ClientXone;
import main.tjava.socket.client.ClientXtwo;

public class TestVClient {
    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            ClientXone clientXone = new ClientXone();
            clientXone.send();
        }).start();

        new Thread(()-> {
            ClientXtwo clientXtwo = new ClientXtwo();
            clientXtwo.send();
        }).start();

        Thread.sleep(10);
    }
}
