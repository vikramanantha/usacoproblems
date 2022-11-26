
// Vikram Anantha
// Nov 6 2022
// USACO 2016 US Open Contest, Gold
// Problem 1. Splitting the Field

import java.util.*;
import java.io.*;
public class SplittingTheField {
    static long[] xs;
	static long[] ys;
	static long[][] cows;
	static int n;
	static Scanner in;
    static long result;
    static long[] minX;
    static long[] minY;
    static long[] maxY;
    static long[] maxX;
    static long[][][] cowsSorted = new long[2][][];
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "__fillerFile__";
    // String file_name = "split";
    in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    // CoordCompress();
    GetInputs();


    cowsSorted[0] = sortby(cows, 0);
    cowsSorted[1] = sortby(cows, 1);

    // for (int tsawn = 0; tsawn < cowsSorted[0].length; tsawn++) System.out.println(Arrays.toString(cowsSorted[0][tsawn]));
    // System.out.println();
    // for (int tsawn = 0; tsawn < cowsSorted[1].length; tsawn++) System.out.println(Arrays.toString(cowsSorted[1][tsawn]));

    minX = GetMin(minX, 1);
    maxX = GetMax(maxX, 1);
    minY = GetMin(minY, 0);
    maxY = GetMax(maxY, 0);

    // System.out.println(Arrays.toString(minX));
    // System.out.println(Arrays.toString(minY));
    // System.out.println(Arrays.toString(maxX));
    // System.out.println(Arrays.toString(maxY));

    

    result = 0;
    

    for (int xory = 0; xory < 2; xory++) {
    long maxxb = Long.MIN_VALUE;
    long maxyb = Long.MIN_VALUE;
    long minxb = Long.MAX_VALUE;
    long minyb = Long.MAX_VALUE;
    long maxxs = Long.MIN_VALUE;
    long maxys = Long.MIN_VALUE;
    long minxs = Long.MAX_VALUE;
    long minys = Long.MAX_VALUE;

    
    // for (int tsawn = 0; tsawn < cows.length; tsawn++) System.out.println(Arrays.toString(cows[tsawn]));
    
    Deque<long[]> big = new ArrayDeque<long[]>();
    for (int i = 0; i < n; i++) {
        big.add(cowsSorted[xory][i]);
        // maxxb = Math.max(maxxb, cows[i][0]);
        // maxyb = Math.max(maxyb, cows[i][1]);
        // minxb = Math.min(minxb, cows[i][0]);
        // minyb = Math.min(minyb, cows[i][1]);
    }
    Deque<long[]> smol = new ArrayDeque<long[]>();

    // maxxb = maxX[0];
    // int bigarea = (maxxb-minxb) * (maxyb-minyb);
    long bigarea = (maxX[0]-minX[0]) * (maxY[0]-minY[0]);
    long ogbigarea = bigarea;
    long smolarea = 0;
    // System.out.println(Arrays.toString(new int[]{bigarea, minx, miny, maxx, maxy}));
    // System.out.println(bigarea);

    // System.out.println(">>>");
    // System.out.println(
    //     checkIfOverlap(1, 2, 2, 3, 3, 1, 4, 2)
    // );

    for (int i = 1; i < n; i++) {
        long[] inquestion = big.pollFirst();
        smol.addLast(inquestion);
        maxxs = Math.max(maxxs, inquestion[0]);
        maxys = Math.max(maxys, inquestion[1]);
        minxs = Math.min(minxs, inquestion[0]);
        minys = Math.min(minys, inquestion[1]);

        minxb = minX[i];
        maxxb = maxX[i];
        minyb = minY[i];
        maxyb = maxY[i];

        if (xory == 0) {
            minxb = big.peekFirst()[0];
        }
        if (xory == 1) {
            minyb = big.peekFirst()[1];
        }

        smolarea = (maxxs-minxs) * (maxys-minys);
        bigarea = (maxxb-minxb) * (maxyb-minyb);
        long totalarea = smolarea + bigarea;

        // System.out.println(smolarea + " + " + bigarea + " = " + totalarea + " < " + ogbigarea);
        // System.out.println("  Smolarea: (" + minxs + "," + minys + ") x (" + maxxs + "," + maxys + "), ");
        // System.out.println("  Bigarea:  (" + minxb + "," + minyb + ") x (" + maxxb + "," + maxyb + "), ");
        
        boolean overlap = checkIfOverlap(minxb, minyb, maxxb, maxyb, minxs, minys, maxxs, maxys);
        if (overlap) {
            continue;
        }
        // System.out.println("ENGRAIN IN THE MEMBRAIN");

        result = Math.max(result, (ogbigarea-totalarea));
        
    }

    }
    

    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static boolean checkIfOverlap(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
    if ((x1 <= x4) && (x3 <= x2) && (y1 <= y4) && (y3 <= y2)) return true;
    return false;
  }

  public static long[] GetMin(long[] min, int ind) {
    min = new long[n];
    long minnumero = Long.MAX_VALUE;
    for (int i = n-1; i >= 0; i--) {
        minnumero = Math.min(cowsSorted[ind][i][1-ind], minnumero);
        min[i] = minnumero;
    }
    return min;
  }

  public static long[] GetMax(long[] max, int ind) {
    max = new long[n];
    long maxnumero = Long.MIN_VALUE;
    for (int i = n-1; i >= 0; i--) {

        maxnumero = Math.max(cowsSorted[ind][i][1-ind], maxnumero);
        max[i] = maxnumero;
    }
    return max;
  }

  public static long[][] sortby(long[][] cowsPrime, int ind) {
    long[][] cows = Arrays.copyOf(cowsPrime, cowsPrime.length);
    Arrays.sort(cows, new Comparator<long[]>() {
        public int compare(long[] a, long[] b) {
            return (int)(a[ind] - b[ind]);
        }
    });
    return cows;
  }

  public static void GetInputs() {
    xs = new long[n];
    ys = new long[n];
    cows = new long[n][2];
    for (int j = 0; j < n; j++) {
        xs[j] = in.nextInt();
        ys[j] = in.nextInt();
        cows[j][0] = xs[j];
        cows[j][1] = ys[j];
    }
  }

  public static void CoordCompress() {

    xs = new long[n];
    ys = new long[n];

    Integer[] fillerarray = new Integer[n];
    for (int j = 0; j < n; j++) {
        xs[j] = in.nextInt();
        ys[j] = in.nextInt();
        fillerarray[j] = j;
    }

    Arrays.sort(fillerarray, Comparator.comparingLong(j -> xs[j]));
    for (int x = 0; x < n; x++) {
        xs[fillerarray[x]] = x;
    }
    Arrays.sort(fillerarray, Comparator.comparingLong(j -> ys[j]));
    for (int y = 0; y < n; y++) {
        ys[fillerarray[y]] = y;
    }

}
}


/*

6
A- 4  2
B- 8  10
C- 1  1
D- 9  12
E- 14 7
F- 2  3
----------
C- 1  1
F- 2  3
A- 4  2
B- 8  10
D- 9  12
E- 14 7
----------
C- 1  1
A- 4  2
F- 2  3
E- 14 7
B- 8  10
D- 9  12


13|
  |                 D
11|
  |               B
9 |
  |
7 |                            E
  |
5 |
  |
3 |   F
  |       A
1 | C
--|----------------------------------
    1   3   5   7   9   11  13  15


    Min_0: (1, 1), Max_0: (14, 12), Area: 13x11 = 143
C- 1  1
    Min_1: (1, 1), Max_1: (1, 1), Area: 0x0 = 0
    Min_2: (2, 3), Max_1: (14, 12), Area: 12x9 = 108
F- 2  3
    Min_1: (1, 1), Max_1: (2, 3), Area: 0x0 = 0
    Min_2: (2, 3), Max_1: (14, 12), Area: 12x9 = 108
A- 4  2
B- 8  10
D- 9  12
E- 14 7
----------
C- 1  1
A- 4  2
F- 2  3
E- 14 7
B- 8  10
D- 9  12



C- 1  1   minY - 1      maxY - 12
F- 2  3   minY - 2      maxY - 12
A- 4  2   minY - 2      maxY - 12
B- 8  10  minY - 7      maxY - 12
D- 9  12  minY - 7      maxY - 12 
E- 14 7   minY - 7      maxY - 7
----------
C- 1  1
A- 4  2
F- 2  3
E- 14 7
B- 8  10
D- 9  12

 */