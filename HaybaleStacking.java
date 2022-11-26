// Vikram Anantha
// Oct 25 2022
// USACO 2012 January Contest, Bronze Division
// Problem 2. Haybale Stacking

import java.util.*;
import java.io.*;
public class HaybaleStacking {
  static int n;
  static int k;
  static long result;
  static int[] ks;
  static int[] stacks;
  static int[] prefix;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    k = in.nextInt();
    ks = new int[k];
    stacks = new int[n+1];
    prefix = new int[n];

    for (int i = 0; i < k; i++) {
        int a = in.nextInt();
        int b = in.nextInt();
        stacks[a-1]++;
        stacks[b]--;
    }
    
    result = 0;

    prefix[0] = stacks[0];
    for (int i = 1; i < n; i++) {
        prefix[i] += prefix[i-1] + stacks[i];
    }

    Arrays.sort(prefix);

    result = prefix[prefix.length/2];

    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}
