import java.util.ArrayList;

public class Boxed {
    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Long> deltas = new ArrayList<>();

    static long sum(Point[] points) {
        long sum2 = 0;

        for (Point p : points) {
            long start = System.nanoTime();
            sum2 += p.x;
            sum2 += p.y;
            long delta = System.nanoTime() - start;
            deltas.add(delta);
        }

        return sum2;
    }

    public static void main(String[] args) {
        for (int trial = 1; trial <= 8; trial++) {
            Point[] list = new Point[100_000];
            for (int i = 0; i < 100_000; i++) {
                list[i] = new Point(i, i%100);
            }

            long sum = sum(list);
            // Print average time.
            double avgLatency = 1.0 * deltas.stream().mapToLong(x -> x).sum() / deltas.size();
            System.out.printf("%d Average Latency: %g ns\n", trial, avgLatency);
            System.out.println("\tSum = " + sum);
        }
    }

}
