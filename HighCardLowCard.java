// Vikram Anantha
// Nov 16 2022
// USACO 2015 December Contest, Gold
// Problem 1. High Card Low Card (Gold)

/*
Strat: Notice that the Bessies highest N/2 can possibly beat Elsie's first N/2 cards,
       and Bessie's lowest N/2 can possibly beat Elsie's last N/2 cards
 */

import java.util.*;
import java.io.*;
public class HighCardLowCard {
    static int n;
    static int[] cards;
    static int[] bessiehigh;
    static int[] bessielow;
    static int[] elsiehigh;
    static int[] elsielow;
    static long result;
    public static void main(String[] args) throws FileNotFoundException {
      String file_name = "cardgame";
      // String file_name = "__fillerFile__";
      Scanner in = new Scanner(new File(file_name + ".in"));
      
      n = in.nextInt();
      cards = new int[2*n];
      bessiehigh = new int[n/2];
      bessielow = new int[n/2];
      elsiehigh = new int[n/2];
      elsielow = new int[n/2];
      for (int i = 0; i < n/2; i++) cards[in.nextInt()-1] = 1;
      for (int i = 0; i < n/2; i++) cards[in.nextInt()-1] = 2;
      int el = 0;
      int eh = 0;
      int b = 0;
      for (int i = 0; i < cards.length; i++) {
        if (cards[i] == 0) {
            if (b > n/2-1) {
                bessiehigh[(bessiehigh.length - 1) - (b % (n/2))] = i+1;
            } else {
                bessielow[b] = i+1;
            }
            b++;
        }
        if (cards[i] == 2) {
            elsielow[el] = i+1;
            el++;
        }
        if (cards[i] == 1) {
            elsiehigh[(elsiehigh.length - 1) - eh] = i+1;
            eh++;
        }
    };

    // System.out.println(Arrays.toString(bessiehigh));
    // System.out.println(Arrays.toString(elsiehigh));
    // System.out.println(Arrays.toString(bessielow));
    // System.out.println(Arrays.toString(elsielow));

    
    result = 0;

    b = 0;
    int e = 0; // reduce, reuse, recycle

    while (true) {
        if (e >= elsiehigh.length || b >= bessiehigh.length) {
            result += b;
            break;
        }
        if (elsiehigh[e] > bessiehigh[b]) {
            e++;
            continue;
        }
        b++;
        e++;
    }

    b = 0;
    e = 0; // reduce, reuse, recycle

    while (true) {
        if (e >= elsielow.length || b >= bessielow.length) {
            result += b;
            break;
        }
        if (elsielow[e] < bessielow[b]) {
            e++;
            continue;
        }
        b++;
        e++;
    }

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}
/*

1
8

4
3

E: 1, _,  ,  , _, _, _, 8
    , _, 3, 4, _, _, _, 
B: _, 2, _, _, 5, 6, 7, _

Use your highest N/2 cards for the first half, and the lowest N/2 cards for the second half

_ _ _ X X _ X X
X X X     X
*/