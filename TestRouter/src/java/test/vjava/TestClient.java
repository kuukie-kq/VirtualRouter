package test.vjava;

import main.vjava.socket.client.ClientXone;
import main.vjava.socket.client.ClientXtwo;

public class TestClient {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            ClientXone clientXone = new ClientXone();
            clientXone.send();
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ClientXtwo clientXtwo = new ClientXtwo();
                clientXtwo.send();
            }
        }).start();

        Thread.sleep(10);
    }
}
