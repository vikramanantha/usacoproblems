// Vikram Anantha
// Nov 11 2022
// USACO 2016 January Contest, Silver
// Problem 1. Angry Cows
/*
Strat: Use binary search to find the optimal blast radius
       For each potential blast radius, start shooting cows in the optimal way
       (The optimal way is to shoot the cow to the leftmost haybale, then shoot the next one at the haybale that's at least [Radius] units away, and so on)
 */

import java.util.*;
import java.io.*;
public class AngryCows {
  static int n;
  static int k;
  static long result;
  static long[] bales;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "angry";
    // String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    k = in.nextInt();

    bales = new long[n];
    for (int tsawn = 0; tsawn < bales.length; tsawn++) bales[tsawn] = in.nextLong();

    Arrays.sort(bales);
    result = binarySearch();
    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static long binarySearch() {
    long low = 0;
    long high = (int)(bales[bales.length-1] - bales[0] + 1);
    long mid = (low + high) / 2;

    while (low <= high) {
        mid = (low + high) / 2;

        boolean good = simulate(mid);

        // System.out.println(low + " < " + mid + " < " + high + " | " + good);
        if (good) {
            high = mid-1;
        } else {
            low = mid+1;
        }
    }

    return low;
  }

  public static boolean simulate(long r) {
    long start = bales[0];
    int count = 1;
    for (int i = 0; i < bales.length; i++) {
        if (bales[i] > start + 2*r) {
            count++;
            start = bales[i];
        }
    }
    if (count > k) return false;
    return true;
  }
}

/*

Example Data Visualized

X       X                   X       X                                   X       X               X
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25

*/