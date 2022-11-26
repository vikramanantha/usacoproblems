// Vikram Anantha
// Nov 8 2022
// USACO 2018 January Contest, Silver
// Problem 2. Rental Service
/*
Strat: Sort the cows, buyers by price, and renters
       Then assign the cow with the most milk to the buyer with the highest asking price to get a preliminary price
       Then one by one, assign a cow with the lowest milk payout to the renter with the highest asking price (gotta scam them)
       Find the max price with that

       TL;DR: Scam the renters and buyers
 */

import java.util.*;
import java.io.*;
public class RentalService {
  static int n;
  static int m;
  static int r;
  static long result;
  static Integer[] cows;
  static int[][] buyers;
  static Integer[] renters;

  static long[] money;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "rental";
    // String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    m = in.nextInt();
    r = in.nextInt();

    cows = new Integer[n];
    money = new long[n];
    renters = new Integer[r];
    buyers = new int[m][2];
    for (int tsawn = 0; tsawn < cows.length; tsawn++) cows[tsawn] = in.nextInt();
    for (int tsawn1 = 0; tsawn1 < buyers.length; tsawn1++) {
        for (int tsawn2 = 0; tsawn2 < buyers[0].length; tsawn2++) {
            buyers[tsawn1][tsawn2] = in.nextInt();
    }}
    for (int tsawn = 0; tsawn < renters.length; tsawn++) renters[tsawn] = in.nextInt();
    
    Arrays.sort(cows);
    Arrays.sort(buyers, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            return b[1]-a[1];
        }
    });
    Arrays.sort(renters, Collections.reverseOrder());
    
    

    int buyerind = 0;
    int buyerleft = buyers[buyerind][0];
    
    int i = n-1;
    int cowleft = cows[i];
    while (i >= 0) {
        // System.out.println(buyerind + " " + buyerleft + " " + i + " " + cowleft + " " + money[i]);
        if (cowleft < buyerleft) {
            buyerleft -= cowleft;
            money[i] += buyers[buyerind][1] * cowleft;
            i--;
            if (i >= 0) cowleft = cows[i];
        }
        else {
            cowleft -= buyerleft;
            money[i] += buyers[buyerind][1] * buyerleft;
            buyerind+=1;
            if (buyerind < buyers.length)
                buyerleft = buyers[buyerind][0];
            else    
                break;
        }
    }

    long sum = 0;
    for (long a : money) sum += a;

    result = sum;

    // System.out.println(Arrays.toString(money));

    for (i = 0; i < Math.min(cows.length, renters.length); i++) {
        sum -= money[i];

        money[i] = renters[i];

        sum += money[i];

        result = Math.max(result, sum);
    }

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}