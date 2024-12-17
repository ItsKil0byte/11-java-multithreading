package org.ulearn.task01;

public class Lucky {
    static int x = 0;
    static int count = 0;
    private static final Object lock = new Object();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            int currentValue;

            while (true) {
                synchronized (lock) {
                    if (x >= 999999) {
                        break;
                    }

                    x++;
                    currentValue = x;
                }

                if ((currentValue % 10) + (currentValue / 10) % 10 + (currentValue / 100) % 10 == (currentValue / 1000)
                        % 10 + (currentValue / 10000) % 10 + (currentValue / 100000) % 10) {
                    System.out.println(currentValue);

                    synchronized (lock) {
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
