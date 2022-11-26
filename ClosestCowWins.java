// Vikram Anantha
// Nov 19 2022
// USACO 2021 December Contest, Silver
// Problem 1. Closest Cow Wins

import java.util.*;
import java.io.*;
public class ClosestCowWins {
    static int n;
    static long result;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("__fillerFile__.in"));
        n = in.nextInt();

        
        result = 0;

        

        System.out.println(result);
        in.close();
    }
}
/*


-------|     |-----|         |-----| |-----| |=====|-------|
4               6               10      8       12  14
0---|---2---|---4---|---6---|---8---|---10--|---12--|---14--|---16--|
        C   C       C       C               C
[      ] [ ] [     ] [     ] [             ] [                      ]

_  >  0  >  2
3  >  4  >  5
7  >  8  >  11
7  >  9  >  11
11 >  12 >  _
11 >  13 >  _



6 5 2
0 4
4 6
8 10
10 8
12 12
13 14
2
3
5
7
11
*/