# Assignment 1 — Algorithmic Analysis

## 📌 Introduction
This project was made for **Assignment 1** of the *Design and Analysis of Algorithms* course.  
The goal was to implement four algorithms in Java, analyze their complexity, run experiments, and compare the results with theory.

Algorithms included:
- **MergeSort**
- **QuickSort**
- **Deterministic Select (Median of Medians)**
- **Closest Pair of Points**

---

## 📂 Architecture Notes
- **MergeSort** → recursive, single buffer, cutoff to InsertionSort for small subarrays.
- **QuickSort** → randomized pivot, tail recursion elimination (saves stack).
- **Deterministic Select** → Median of Medians guarantees linear time.
- **Closest Pair** → divide-and-conquer with strip optimization.

### Depth & Memory
- **MergeSort**: depth `O(log n)`, buffer allocated once.
- **QuickSort**: expected depth `O(log n)`, randomized pivot avoids worst-case.
- **Deterministic Select**: recursion shrinks ≥30% each step → depth `O(log n)`.
- **Closest Pair**: depth `O(log n)`, auxiliary arrays reused.

---

## 📊 Recurrence Analysis

- **MergeSort**  
  `T(n) = 2T(n/2) + Θ(n)`  
  Solution (Master Theorem): `Θ(n log n)`

- **QuickSort** (average)  
  `T(n) = T(αn) + T((1-α)n) + Θ(n)`  
  Expected: `Θ(n log n)`  
  Worst-case: `O(n²)` (rare due to random pivot).

- **Deterministic Select (MoM5)**  
  `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)`  
  Solution (Akra–Bazzi): `Θ(n)`

- **Closest Pair of Points**  
  `T(n) = 2T(n/2) + Θ(n)`  
  Solution (Master Theorem): `Θ(n log n)`

---

## 📈 Experimental Results

We benchmarked algorithms up to `n = 20,000` (see `results.csv`).

### Runtime
![Time Plot](time_plot.png)

- MergeSort and QuickSort follow `n log n`.
- Deterministic Select grows linearly but with larger constants.
- Closest Pair is slower on small arrays due to overhead.

### Recursion Depth
![Depth Plot](depth_plot.png)

- Depth grows around `log₂(n)`.
- For `n = 20,000`, depth ≈ 15.
- Matches the theory for all algorithms.

---

## 📋 Comparison Table

| Algorithm              | Average Time | Worst Case | Memory   | Depth    |
|------------------------|--------------|------------|----------|----------|
| MergeSort              | Θ(n log n)   | Θ(n log n) | O(n)     | O(log n) |
| QuickSort              | Θ(n log n)   | O(n²)      | O(log n) | O(log n) |
| Deterministic Select   | Θ(n)         | Θ(n)       | O(1)     | O(log n) |
| Closest Pair of Points | Θ(n log n)   | Θ(n log n) | O(n)     | O(log n) |

---

## ✅ Testing

Unit tests were written with **JUnit 5**.

- Sorting algorithms tested on random, sorted, and empty arrays.
- QuickSort recursion depth ≤ about `2*log₂(n)`.
- Deterministic Select compared with `Arrays.sort()[k]`.
- Closest Pair validated against brute-force for small `n (≤ 2000)`.

Example:
```java
@Test
void testMergeSortBasic() {
    int[] arr = {5, 3, 8, 1, 2};
    int[] expected = {1, 2, 3, 5, 8};
    MergeSort.sort(arr);
    assertArrayEquals(expected, arr);
}
```
## 🎯 Summary
- **MergeSort** is stable and predictable (`Θ(n log n)`).
- **QuickSort** is usually fastest, thanks to cache, but has a rare `O(n²)` case.
- **Deterministic Select** is linear in theory, but constants make it slower in practice.
- **Closest Pair** works well for large `n`, but overhead is high for small inputs.

✅ Theoretical analysis matches experimental data, with only small constant-factor differences.
