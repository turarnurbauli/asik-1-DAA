import java.util.*;

class Point {
    double x, y;
    Point(double x, double y) { this.x = x; this.y = y; }
}

public class ClosestPair {
    public static double closest(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[px.length];
        return divide(px, aux, 0, px.length - 1);
    }

    private static double divide(Point[] px, Point[] aux, int left, int right) {
        if (right - left <= 3) return brute(px, left, right);

        int mid = (left + right) / 2;
        double midX = px[mid].x;

        double d1 = divide(px, aux, left, mid);
        double d2 = divide(px, aux, mid + 1, right);
        double d = Math.min(d1, d2);

        mergeByY(px, aux, left, mid, right);

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++)
            if (Math.abs(px[i].x - midX) < d) strip.add(px[i]);

        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && j <= i + 7; j++) {
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    private static double brute(Point[] px, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, dist(px[i], px[j]));
            }
        }
        Arrays.sort(px, left, right + 1, Comparator.comparingDouble(p -> p.y));
        return min;
    }

    private static void mergeByY(Point[] px, Point[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (px[i].y <= px[j].y) aux[k++] = px[i++];
            else aux[k++] = px[j++];
        }
        while (i <= mid) aux[k++] = px[i++];
        while (j <= right) aux[k++] = px[j++];
        System.arraycopy(aux, left, px, left, right - left + 1);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }
}
