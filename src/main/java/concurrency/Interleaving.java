package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Interleaving {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread incrementor = new Thread(() -> {
            for (int i = 0; i < 100000; i++)
                counter.increment();
        });
        Thread decrementor = new Thread(() -> {
            for (int i = 0; i < 100000; i++)
                counter.decrement();
        });

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(incrementor);
        service.submit(decrementor);
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(counter.value());
    }

    static class Counter {
        private int c = 0;

        public void increment() {
            c++;
        }

        public void decrement() {
            c--;
        }

        public int value() {
            return c;
        }

    }
}
