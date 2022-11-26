// Vikram Anantha
// Nov 9 2022
// USACO 2018 US Open Contest, Silver
// Problem 1. Out of Sorts
/*
Strat: Notice that every time one does Bubble Sort, it's only as long as the time it takes to get the furthest out-of-sort number to its correct positition
Example:
1, 5, 3, 8, 2
1 is 0 spots away
5 is 2 spots away
3 is 1 spot away
8 is one spot away
2 is 3 spots away

hence, bubble sort should only take about 3 cycles for this example, and "moo" should print 4 times

Note: this only works for 9/10 test cases but that's still pretty good
 */

import java.util.*;
import java.io.*;
public class OutOfSorts {
  static int n;
  static long result;
  static long[] xs;
  static Scanner in;
  public static void main(String[] args) throws FileNotFoundException {
    // String file_name = "__fillerFile__";
    String file_name = "sort";
    in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();

    CoordCompress();

    
    result = 0;

    for (int i = 0; i < n; i++) {
        result = Math.max(result, Math.abs(xs[i] - i));
    }
    // long result2 = 0;
    // for (int i = 0; i < n; i++) {
    //     result2 = Math.max(result2, Math.abs(xs[n-i-1] - i));
    // }

    // result = Math.min(result, result2 + n-2);
    
    result++;
    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static void CoordCompress() {

    xs = new long[n];

    Integer[] fillerarray = new Integer[n];
    for (int j = 0; j < n; j++) {
        xs[j] = in.nextLong();
        fillerarray[j] = j;
    }

    Arrays.sort(fillerarray, Comparator.comparingLong(j -> xs[j]));
    for (int x = 0; x < n; x++) {
        xs[fillerarray[x]] = x;
    }

}
}

/*

1, 0 --> 0
2, 4 --> 1
3, 2 --> 2
5, 1 --> 3
8, 3 --> 4


1  1  1  1
5  3  3  2
3  5  2  3
8  2  5  5
2  8  8  8

1, 5, 3, 8, 2
0, 3, 2, 4, 1
0, 1, 2, 3, 4

0  2  2  1  3

 */
