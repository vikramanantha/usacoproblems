// Vikram Anantha
// Nov 19 2022
// USACO 2022 US Open Contest, Silver
// Problem 1. Visits

import java.util.*;
import java.io.*;
public class Visits {
    static long n;
    static long result;
    static long[][] cows;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("__fillerFile__.in"));
        n = in.nextLong();
        cows = new long[(int)n][2];
        for (int tsawn1 = 0; tsawn1 < cows.length; tsawn1++) {
            for (int tsawn2 = 0; tsawn2 < cows[0].length; tsawn2++) {
                 cows[tsawn1][tsawn2] = in.nextInt();
        }}

        Arrays.sort(cows, new Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
                if (b[1] > a[1]) return 1;
                if (b[1] < a[1]) return -1;
                return 0;
            }
        });

        
        result = 0;

        for (int i = 0; i < n-1; i++) {
            result += cows[i][1];
        }

        System.out.println(result);
        in.close();
    }
}
