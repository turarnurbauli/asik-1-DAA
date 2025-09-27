import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // размеры входных данных
        int[] sizes = {1000, 2000, 5000, 10000, 20000};
        Random rnd = new Random();

        try (FileWriter fw = new FileWriter("results.csv")) {
            fw.write("Algorithm,n,Time_ms\n"); // заголовок CSV

            for (int n : sizes) {
                int[] base = new int[n];
                for (int i = 0; i < n; i++) base[i] = rnd.nextInt();

                // MergeSort
                int[] arr = base.clone();
                long t0 = System.nanoTime();
                MergeSort.sort(arr);
                long t1 = System.nanoTime();
                fw.write("MergeSort," + n + "," + ((t1 - t0) / 1_000_000) + "\n");

                // QuickSort
                arr = base.clone();
                t0 = System.nanoTime();
                QuickSort.sort(arr);
                t1 = System.nanoTime();
                fw.write("QuickSort," + n + "," + ((t1 - t0) / 1_000_000) + "\n");

                // Deterministic Select (ищем медиану)
                arr = base.clone();
                t0 = System.nanoTime();
                int median = DeterministicSelect.select(arr.clone(), n / 2);
                t1 = System.nanoTime();
                fw.write("DeterministicSelect," + n + "," + ((t1 - t0) / 1_000_000) + "\n");

                // Closest Pair (берем точки случайные)
                Point[] pts = new Point[Math.min(n, 2000)]; // ограничим 2000, иначе слишком долго
                for (int i = 0; i < pts.length; i++) {
                    pts[i] = new Point(rnd.nextDouble(), rnd.nextDouble());
                }
                t0 = System.nanoTime();
                double dist = ClosestPair.closest(pts);
                t1 = System.nanoTime();
                fw.write("ClosestPair," + pts.length + "," + ((t1 - t0) / 1_000_000) + "\n");
            }
        }

        System.out.println("Done! Results saved to results.csv");
    }
}
