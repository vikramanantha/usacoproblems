// Vikram Anantha
// Nov 17 2022
// USACO 2020 January Contest, Silver
// Problem 1. Berry Picking

import java.util.*;
import java.io.*;
public class BerryPicking {
  static int n;
  static long result;
  public static void main(String[] args) throws FileNotFoundException {
    // String file_name = "problemfilename";
    String file_name = "__fillerFile__";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();

    
    result = 0;

    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}

/*
5 4
3 6 8 4 2

8, 6, 4, 3, 2


6, 4, 4, 4

*/