import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class AlgorithmsTest {


    @Test
    void testMergeSortBasic() {
        int[] arr = {5, 3, 8, 1, 2};
        int[] expected = {1, 2, 3, 5, 8};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeSortEmpty() {
        int[] arr = {};
        int[] expected = {};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }


    @Test
    void testQuickSortBasic() {
        int[] arr = {9, 4, 6, 2, 7};
        int[] expected = {2, 4, 6, 7, 9};
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testQuickSortAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }


    @Test
    void testSelectMedian() {
        int[] arr = {7, 2, 9, 1, 5};
        int median = DeterministicSelect.select(arr.clone(), arr.length / 2);
        Arrays.sort(arr);
        assertEquals(arr[arr.length / 2], median);
    }

    @Test
    void testSelectMin() {
        int[] arr = {10, 3, 5, 1};
        int min = DeterministicSelect.select(arr.clone(), 0);
        assertEquals(1, min);
    }


    @Test
    void testClosestPairSmall() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 1),
                new Point(2, 2)
        };
        double dist = ClosestPair.closest(points);


        double brute = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                double d = Math.sqrt(dx * dx + dy * dy);
                brute = Math.min(brute, d);
            }
        }
        assertEquals(brute, dist, 1e-6);
    }

    @Test
    void testClosestPairIdentical() {
        Point[] points = {
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2)
        };
        assertEquals(0.0, ClosestPair.closest(points), 1e-6);
    }
}
