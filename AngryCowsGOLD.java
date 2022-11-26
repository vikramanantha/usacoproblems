// Vikram Anantha
// Nov 12 2022
// USACO 2016 January Contest, Gold
// Problem 1. Angry Cows

/*
Strat & Psuedocode: Binary Search to find the optimal Radius
       Then binary search to find the optimal blast point (with respect to if it can blast cows on the left)
       if that point can also blast cows on the right, then it's a good blast radius


Psuedo Code:

main() {
    input
    BinarySearch()
    output
}

BinarySeachRadius () {
    for each mid:
        BinarySeachStartPoint(mid)
}

BinarySeachStartPoint(r) {
    for each midpoint m:
        BlastBackward(m, r) 
        if that returns true
            move more right
        else move more left
    
        once optimal midpoint is found, BlastForward(m, r)
        if that returns true, our radius is more than enough
}

BlastBackward(m, r) {
    if r = 0:
        if very left point has been blasted, return true
        else return false
    mark e as blasted
    find the left endpoint of the window made by the blast, n
    BlastBackward(f, r-1)
}

BlastForward(m, r) {
    if r = 0:
        if very right point has been blasted, return true
        else return false
    mark e as blasted
    find the right endpoint of the window made by the blast, n
    BlastForward(n, r-1)
}
 */

import java.util.*;
import java.io.*;
public class AngryCowsGOLD {
  static int n;
  static TreeSet<Long> bales = new TreeSet<Long>();
  static boolean startblasted = false;
  static boolean endblasted = false;
  static long result;
  public static void main(String[] args) throws FileNotFoundException {
    // String file_name = "__fillerFile__";
    String file_name = "angry";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    for (int tsawn = 0; tsawn < n; tsawn++) bales.add(in.nextLong() * 2);
    
    // System.out.println(bales);

    result = BinarySearchRadius();

    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    // System.out.printf("%.1f\n", result / 2.0);
    out.printf("%.1f\n", result / 2.0);
    in.close();
    out.close();
  }

  public static long BinarySearchRadius() {
    long high = bales.last() - bales.first() + 2;
    long low = 1;
    while (low < high) {
        long mid = (high + low) / 2;

        // System.out.println(low + " > " + mid + " > " + high);
        boolean startpoint = BinarySearchStartPoint(mid);
        if (startpoint) high = mid;
        else low = mid+1;
    }

    return low;
  }

  public static boolean BinarySearchStartPoint(long rad) {
    long l = bales.first();
    long r = bales.last();

    while (l < r) {
        long m = (l + r + 1) / 2; // why + 1?
        // System.out.println("  " + l + " > " + m + " > " + r);

        startblasted = false;
        boolean back = BlastBackward(m, rad);

        if (back) l = m;
        else r = m-1;
    }
    // System.out.println("  " + BlastBackward(l, rad));
    endblasted = false;
    // System.out.println("  ----");
    boolean forw = BlastForward(l, rad);

    // System.out.println(forw);
    return forw;
  }

  public static boolean BlastBackward(long m, long r) {
    if (r < 0) {
        if (startblasted) return true;
        else return false;
    }
    long e = bales.ceiling(m - r);

    // System.out.println("    M, E: " + m + " " + e + " R:" + r);

    if (e == bales.first()) startblasted = true;
    if (startblasted == true) return true;
    if (e == m) return false;
    return BlastBackward(e, r-2);

  }

  public static boolean BlastForward(long m, long r) {
    if (r < 0) {
        if (endblasted) return true;
        else return false;
    }
    long e = bales.floor(m + r);
    // System.out.println("    M, E: " + m + " " + e + " R:" + r);
    if (e == bales.last()) endblasted = true;
    if (endblasted == true) return true;
    if (e == m) return false;

    return BlastForward(e, r-2);
  }

}
/*

    X       X                   X       X   X
|---|---|---|---|---|---|---|---|---|---|---|---|
0       2       4       6       8       10



 */