import java.util.Random;

public class QuickSort {
    private static final Random RAND = new Random();

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            int pivot = arr[left + RAND.nextInt(right - left + 1)];
            int i = left, j = right;

            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }
            }

            // рекурсивно вызываем только меньшую часть
            if (j - left < right - i) {
                if (left < j) quickSort(arr, left, j);
                left = i; // хвост рекурсивно не вызываем → экономим стек
            } else {
                if (i < right) quickSort(arr, i, right);
                right = j;
            }
        }
    }
}
