package concurrency;

public class SimpleConcurrency {
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {"Adam", "Mickiewicz", "wielkim", "peotą", "był."};
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("Wątek nie skończył pracy!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        long patience = 1000 * 60 * 60;

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Wartość musi być liczbowa.");
                System.exit(1);
            }
        }

        threadMessage("Uruchomienie MessageLoop");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Oczekiwanie na zakończenie pracy MessageLoop");
        while (t.isAlive()) {
            threadMessage("Czekam...");
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Dość czekania!");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Koniec!");
    }
}
