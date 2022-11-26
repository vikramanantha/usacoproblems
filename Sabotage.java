// Vikram Anantha
// Nov 11 2022
// USACO 2014 March Contest, Gold
// Problem 2. Sabotage

import java.util.*;
import java.io.*;
public class Sabotage {
  static int n;
  static long[] cows;
  static double result;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    cows = new long[n];
    for (int tsawn = 0; tsawn < cows.length; tsawn++) cows[tsawn] = in.nextLong();
    
    result = 0.0;

    result = binarySearch();

    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static double binarySearch() {
    double low = 1.0;
    double high = 10000.0;
    double mid = (low + high) / 2.0;

    while (low <= high) {
        mid = (round3(low) + round3(high)) / 2;

        boolean good = simulate((mid));

        // System.out.println(low + " < " + mid + " < " + high + " | " + good);
        if (good) {
            high = mid-1;
        } else {
            low = mid+1;
        }
    }

    return low;
  }

  public static boolean simulate(double r) {
    double sum = 0.0;
    for (long l : cows) sum += l;
    double current = cows[0] - r;
    double best = current;

    for (int i = 1; i < n-1; i++) {
        if (current < 0) current = 0;
        current += (cows[i] - r);
        best = Math.max(current, best);
    }

    return best + r * n >= sum;



  }

  public static double round3(double num) {
    return (int)((num * 1000)) / 1000.0;
  }
}

/*

5
5
1
7
8                v  v  


5---1---7---8---2
4---0---6---7---1
3- -1---5---6---0
2- -2---4---5- -1


guess = 1

total = 23
current = 0, 0+6, 0+6+7
best =            13
13 >= 23 - 1


 */