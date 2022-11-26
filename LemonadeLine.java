// Vikram Anantha
// Nov 15 2022
// USACO 2018 US Open Contest, Silver
// Problem 2. Lemonade Line

/*
 * Strat: Order the cows largest to smallest
 *        This way the cows that want it the most will get it first
 *        and the cows that are meh about it will get it last (or not at all)
 */

import java.util.*;
import java.io.*;
public class LemonadeLine {
  static int n;
  static long[] cows;
  static long result;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "lemonade";
    // String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    cows = new long[n];
    for (int tsawn = 0; tsawn < cows.length; tsawn++) cows[tsawn] = in.nextLong();

    Arrays.sort(cows);

    
    result = 0;

    for (int i = cows.length-1; i >= 0; i--) {
        if ((cows.length-1) - i <= cows[i]) result++;
    }

    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}
/*

5
7 1 400 2 2

1 2 2 7 400
400 7 2 2 1



 */