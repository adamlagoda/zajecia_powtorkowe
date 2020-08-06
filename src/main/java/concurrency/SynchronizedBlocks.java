package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizedBlocks {
    static class Chart {
        private int[] productsInCart = new int[3];

        public void addToChart(int productIndex) {
            if (productIndex > productsInCart.length) {
                System.err.println("Product cannot be found");
            }
            productsInCart[productIndex]++;
        }

        public void showProductsInChart() {
            for (int i = 0; i < productsInCart.length; i++) {
                System.out.println("Product " + i + ": " + productsInCart[i] + " items");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Chart chart = new Chart();
        ExecutorService service = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000000)
                .forEach(number -> service.submit(() -> chart.addToChart(0)));
        service.awaitTermination(20, TimeUnit.SECONDS);
        service.shutdown();
        chart.showProductsInChart();
    }
}
