// Vikram Anantha
// Nov 14 2022
// USACO 2016 US Open Contest, Silver
// Problem 2. Diamond Collector
/* 
Strat: Go through the sorted list with 2 pointers and 
       find the longest stretch of diamonds that are within k
       while doing this, record the second longest stretch that came strictly before it
 */

import java.util.*;
import java.io.*;
public class DiamondCollector {
  static int n;
  static long k;
  static long[] diamonds;
  static long result;
  static long[] bests;
  public static void main(String[] args) throws FileNotFoundException {
    // String file_name = "__fillerFile__";
    String file_name = "diamond";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    diamonds = new long[n];
    bests = new long[n];
    k = in.nextLong();
    for (int tsawn = 0; tsawn < diamonds.length; tsawn++) diamonds[tsawn] = in.nextLong();

    Arrays.sort(diamonds);
    result = 0;
    int l = 0;
    long bestleft = 0;

    for (int r = 0; r < n; r++) {
        while (diamonds[r] - diamonds[l] > k) {
            bestleft = Math.max(bestleft, bests[l]);
            l++;
        }
        bests[r] = r-l+1;
        result = Math.max(result, bestleft + bests[r]);
    }

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}

/*

7 3
10
5
1
12
9
5
14


1
5
5 
9
10
12 <
14 <

[
    0: 1
    1: 1
    2: 2
    3: 1
    4: 2
    5: 3
    6: 2
]

[
    [1, 0, 0]
    [1, 1, 1]
    [1, 3, 3]
    [2, 1, 2]
    [2, 3, 4]
    [2, 5, 6]
    [3, 3, 5]

]


 */