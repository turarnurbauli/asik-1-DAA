import java.util.Arrays;

public class DeterministicSelect {

    // k — индекс (0-based), например, для медианы k = n/2
    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length)
            throw new IllegalArgumentException("Invalid k");
        return selectHelper(arr, 0, arr.length - 1, k);
    }

    private static int selectHelper(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return selectHelper(arr, left, pivotIndex - 1, k);
        else return selectHelper(arr, pivotIndex + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right, int pivotValue) {
        while (left <= right) {
            while (arr[left] < pivotValue) left++;
            while (arr[right] > pivotValue) right--;
            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return left - 1;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int median = arr[i + (subRight - i) / 2];
            arr[left + numMedians] = median;
            numMedians++;
        }
        return medianOfMedians(arr, left, left + numMedians - 1);
    }
}
