// Vikram Anantha
// Nov 15 2022
// USACO 2015 December Contest, Silver
// Problem 2. High Card Wins

/*
 Strat: Figure out what cards Bessie has, then go through both their cards from greatest to smallest
        From that you can match cards that bessie has to ones that elsie has such that bessie has
        the greater card
 */

import java.util.*;
import java.io.*;
public class HighCardWins {
  static int n;
  static int[] cards;
  static int[] bessie;
  static int[] elsie;
  static long result;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "highcard";
    // String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    cards = new int[2*n];
    bessie = new int[n];
    elsie = new int[n];

    for (int i = 0; i < n; i++) cards[in.nextInt()-1] = 1;
    int e = 0;
    int b = 0;
    for (int i = cards.length-1; i >= 0; i--) {
        if (cards[i] == 0) {
            bessie[b] = i+1;
            b++;
        }
        if (cards[i] == 1) {
            elsie[e] = i+1;
            e++;
        }
    }

    // System.out.println(Arrays.toString(elsie));
    // System.out.println(Arrays.toString(bessie));    

    result = 0;

    b = 0;
    e = 0; // reduce, reuse, recycle

    while (true) {
        if (e >= elsie.length || b >= bessie.length) {
            result = b;
            break;
        }
        if (elsie[e] > bessie[b]) {
            e++;
            continue;
        }
        b++;
        e++;
    }
    // for (b = 0; b < n; b++) {
    //     e++;

    //     if (e >= elsie.length) {
    //         result = b;
    //         break;
    //     }
    //     while (elsie[e] > bessie[b]) {
    //         e++;
    //     }
    // }

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}
/*
My input:

E: 1, 2, _, _, _, 6, 7, _, _, __, 11, 12, 13, __, __, 16
B: _, _, 3, 4, 5, _, _, 8, 9, 10, __, __, __, 14, 15, __
E: 16, 13, 12, 11, 7, 6, 2, 1   
B:     15, 14,    10, 9, 8, 5, 4, 3

Wins 6 out of the 8 games
Note: Number of Bessie's cards left over is the same as number of games lost



Sample Input:
E: 1, _, _, 4, _, 6
B: _, 2, 3, _, 5, _
E: 6, 4, 1
B:    5, 3, 2

 */