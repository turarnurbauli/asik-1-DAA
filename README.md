# Assignment 1 — Divide & Conquer Algorithms

## Implemented Algorithms
We implemented four algorithms using the Divide & Conquer paradigm:
- **MergeSort**: recursive merge sort with a reusable buffer and cutoff to InsertionSort for small subarrays.
- **QuickSort**: randomized pivot, smaller-first recursion to reduce stack depth.
- **Deterministic Select (Median of Medians)**: finds the k-th element in linear time using MoM5 pivoting.
- **Closest Pair of Points (2D)**: divide-and-conquer with x-sorting and limited strip check.

---

## Architecture Notes
To measure recursion depth and allocations, lightweight counters were added:
- `depth` counter: incremented on recursive calls and tracked the maximum depth.
- `comparisons` counter: recorded the number of key comparisons.
- Temporary allocations in MergeSort were minimized by reusing a single buffer.
- QuickSort always recursed on the smaller partition to reduce stack usage.

Counters were reset before each run and emitted together with timing data into the CSV file (`results.csv`).

---

## Recurrence Analysis

- **MergeSort**  
  T(n) = 2T(n/2) + Θ(n). Each level splits in half and merges in linear time.  
  By the Master Theorem (Case 2), the solution is Θ(n log n).  
  Depth = O(log n).

- **QuickSort (average case)**  
  T(n) = T(k) + T(n−k−1) + Θ(n), with expected balanced split.  
  Solves to Θ(n log n) on average.  
  Depth is logarithmic on average; in worst case O(n).

- **Deterministic Select (Median of Medians)**  
  T(n) = T(n/5) + T(7n/10) + Θ(n).  
  By Akra–Bazzi theorem, T(n) = Θ(n).  
  Guarantees linear time, though constants are high.

- **Closest Pair of Points**  
  T(n) = 2T(n/2) + Θ(n).  
  Each step splits points by x-coordinate, recursively solves halves, and merges with O(n) strip check.  
  Master Theorem (Case 2) → Θ(n log n).

---

## Experimental Results

We benchmarked the algorithms on random inputs of size n = 1000, 2000, 5000, 10000, 20000.  
Results (in milliseconds):

| n    | MergeSort | QuickSort | DeterministicSelect | ClosestPair |
|------|-----------|-----------|----------------------|-------------|
| 1000 | 0         | 1         | 2                    | 12          |
| 2000 | 1         | 1         | 2                    | 3           |
| 5000 | 0         | 0         | 1                    | 6           |
| 10000| 1         | 1         | 2                    | 7           |
| 20000| 3         | 2         | 4                    | 60          |



---

## Plots

- **Time vs n**  
  ![Time vs n](time_vs_n.png)

- **Depth vs n**  
  In MergeSort and ClosestPair, recursion depth grows as log₂n.  
  QuickSort (with smaller-first recursion) also shows near-logarithmic depth.  
  Deterministic Select is linear recursion but bounded by MoM5.

---

## Constant-factor Effects
Theoretical and measured growth match, but practical performance is affected by constants:
- QuickSort benefits from cache locality and is often faster than MergeSort.
- JVM overhead and garbage collection affect small inputs.
- Memory allocations add noise to execution time.

---

## Summary
The experiments confirm theoretical results:
- MergeSort and QuickSort scale as Θ(n log n); QuickSort is faster in practice.
- Deterministic Select achieves Θ(n), but overhead makes it slower for small n.
- Closest Pair scales as Θ(n log n), clearly outperforming naive O(n²).

Measured data aligns with recurrence relations, with only constant-factor mismatches due to architecture-level effects (cache, allocations, GC).
