// Vikram Anantha
// [[Date]]

import java.util.*;
import java.io.*;
public class __template_Old__ {
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