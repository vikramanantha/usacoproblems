// Vikram Anantha
// Oct 5 2022
// USACO 2014 March Contest, Silver
// Problem 2. The Lazy Cow

import java.util.*;
import java.io.*;
public class TheLazyCow {
  static int n;
  static int k;
  static long result;
  static int[][] cows;
  static int[][] cowsprime;
  static int[][] prefix;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "lazy";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    k = in.nextInt();
    cows = new int[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cows[i][j] = in.nextInt();
        }
    }

    int[][] cowsprime = new int[2*n+1][2*n+1];
    int row = 0;
    int col = 5-1;
    for (int i = 0; i < n; i++) {
        row = i;
        col = n-i-1;
        for (int j = 0; j <= i; j++) {
            // System.out.println(cows[i-j][j] + "-->" + row + ", " + col);
            cowsprime[row][col] = cows[i-j][j];
            col += 2;
        }
        
    }
    row = n-1;
    col = 2*n-1;
    for (int i = 0; i < n; i++) {
        row = (2*n-1) - i - 1;
        col = n+i - 1;
        for (int j = 0; j <= i; j++) {
            // System.out.println(cows[n-(i-j)-1][n-j-1] + "-->" + row + ", " + col);
            cowsprime[row][col] = cows[n-(i-j)-1][n-j-1];
            col -= 2;
        }
    }

    
    result = 0;

    // for (int tsawn = 0; tsawn < cowsprime.length; tsawn++) System.out.println(Arrays.toString(cowsprime[tsawn]));

    prefix = prefix2d(cowsprime);

    for (int i = k*2+1-1; i < cowsprime.length; i++) {
        for (int j = k*2+1-1; j < cowsprime[0].length; j++) {
            int sum = findsum(i-(k*2), j-(k*2), i, j, prefix);
            result = Math.max(sum, result);
            // printslice(i-(k*2), j-(k*2), i, j, cowsprime);
            // System.out.println(sum);
        }
    }


    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static int findsum(int x1, int y1, int x2, int y2, int[][] prefix) {
    int sum = 0;

    sum += prefix[y2+1][x2+1];
    sum -= prefix[y1+1-1][x2+1];
    sum -= prefix[y2+1][x1+1-1];
    sum += prefix[y1+1-1][x1+1-1];
    return sum;
  }

  public static int[][] prefix2d(int[][] arr) {
    int[][] prefix = new int[arr.length+1][arr[0].length+1];

    for (int i = 1; i < prefix.length; i++) {
        for (int j = 1; j < prefix[i].length; j++) {
            prefix[i][j] += arr[i-1][j-1];
            if (i == 0 || j == 0) continue;
            prefix[i][j] += prefix[i-1][j] + prefix[i][j-1];
            prefix[i][j] -= prefix[i-1][j-1];

        }
    }

    return prefix;
  }
  public static void printslice (int x1, int y1, int x2, int y2, int[][] arr) {
    for (int i = y1; i <= y2; i++) {
        System.out.print("[");
        for (int j = x1; j <= x2; j++) {
            System.out.print(arr[i][j] + ",");
        }
        System.out.println("]");
    }
}
}



/*



50 5  25 6  17
14 3  2  7  21
99 10 1  2  80
8  7  5  23 11
10 0  78 1  9

__ __ __ __ 50 __ __ __ __
__ __ __ 14 __  5 __ __ __
__ __ 99 __  3 __ 25 __ __
__  8 __ 10 __  2 __  6 __
10 __  7 __  1 __  7 __ 17
__  0 __  5 __  2 __ 21 __
__ __ 78 __ 23 __ 80 __ __
__ __ __  1 __ 11 __ __ __
__ __ __ __  9 __ __ __ __


*/