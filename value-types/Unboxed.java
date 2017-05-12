import java.util.ArrayList;

public class Unboxed {
    static ArrayList<Long> deltas = new ArrayList<>();

    static long sum(long[] points) {
        long sum2 = 0;

        for (int i = 0; i < points.length; i += 2) {
            long start = System.nanoTime();
            sum2 += points[i];
            sum2 += points[i+1];
            long delta = System.nanoTime() - start;
            deltas.add(delta);
        }

        return sum2;
    }

    public static void main(String[] args) {
        for (int trial = 1; trial <= 8; trial++) {
            long [] list = new long[200_000];
            for (int i = 0; i < 200_000; i += 2) {
                list[i] = i / 2;
                list[i+1] = (i/2) % 100;
            }

            long sum = sum(list);
            // Print average time.
            double avgLatency = 1.0 * deltas.stream().mapToLong(x -> x).sum() / deltas.size();
            System.out.printf("%d Average Latency: %g ns\n", trial, avgLatency);
            System.out.println("\tSum = " + sum);
        }
    }

}
