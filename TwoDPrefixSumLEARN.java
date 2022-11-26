// Vikram Anantha
// Oct 5 2022
// Learning about 2D Prefix Sums
// https://usaco.guide/gold/dsu?lang=java#problem-usaco-669

// REALLY useful when you have a connected graph, and you want to find
// the least weight on an edge you can have to connect all the nodes


import java.util.*;
import java.io.*;
public class TwoDPrefixSumLEARN {
    public static void main(String[] args) throws IOException {
        
        int[][] arrold = new int[][]{
            {0,0,0,0,0,0},
            {0,1,5,6,11,8},
            {0,1,7,11,9,4},
            {0,4,6,1,3,2},
            {0,7,5,4,2,3}
        };

        int[][] arr = new int[][]{
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        print2d(arr);
        
        int[][] prefix = prefix2d(arr);
        System.out.println("----");

        print2d(prefix);

        // int result = findsum(1, 2, 3, 4, prefix);

        // int checkresult = checksum(1, 2, 3, 4, arr);

        // printslice(1, 2, 3, 4, arr);
        // System.out.println("----");
        // printslice(0, 0, 3, 4, prefix);

        // System.out.println(result + ", " + checkresult);

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

    public static int checksum(int x1, int y1, int x2, int y2, int[][] arr) {
        int sum = 0;
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                sum+= arr[i][j];
            }
        }
        return sum;
    }

    public static void print2d(int[][] arr) {
        for (int tsawn = 0; tsawn < arr.length; tsawn++) System.out.println(Arrays.toString(arr[tsawn]));
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


000000
0156118
0171194
046132
075423

 */