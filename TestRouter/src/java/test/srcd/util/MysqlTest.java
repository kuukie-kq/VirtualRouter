package test.srcd.util;

public class MysqlTest implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new MysqlTest());
        thread.start();
        for(int i=0;i<20;i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        int number = 0;
        for (; number < 100; number++) {

                System.out.println(number);
                virtualInterrupt(number);

        }
    }

    private void virtualInterrupt(int number) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
