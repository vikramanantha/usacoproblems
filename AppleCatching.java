// Vikram Anantha
// Nov 19 2022
// USACO 2022 US Open Contest, Gold
// Problem 1. Apple Catching

import java.util.*;
import java.io.*;
public class AppleCatching {
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
5
Q---T---X---N
1   2   4   5
1   4   7   6
2   5   10  100
2   6   0   3
2   8   10  7

    0---1---2---3---4---5---6---7---8---9---10--11
1
2                  5C                               
3                                                   
4                              6C
5                                        100A       
6  3A                                                   
7                                               
8                                          7A   
9                                                   






*/