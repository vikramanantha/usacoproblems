// Vikram Anantha
// Aug 29 2022
// 2016 / Jan / Problem 1 - GOLD
// 10 / 10

import java.util.*;
import java.io.*;
public class MoocastGOLDV2 {
  static int n;
  static long result;
  static int[][] cows;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "moocast";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    cows = new int[n][2];
    for (int i = 0; i < n; i++) {
        cows[i][0] = in.nextInt();
        cows[i][1] = in.nextInt();
    } // 1000

    int[][] distarray = makedistarray(cows);

    result = addconns(distarray);

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static int addconns(int[][] dists) {
    DisjointSets dsu = new DisjointSets(n);
    int numconns = n;
    for (int i = 0; i < dists.length; i++) {

        // System.out.println("Adding " + Arrays.toString(dists[i]));
        if (dsu.union(dists[i][0], dists[i][1])) {
            numconns--;
        }

        if (numconns == 1) return dists[i][2];
        // System.out.println();


        // if (dsu.getfromsizes(placee) == n) return dists[i][2];
    }
    return dists[dists.length-1][2];
  }

  public static int[][] makedistarray(int[][] cows) {
    int[][] dists = new int[n*(n-1)/2][3];
    int count = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i+1; j < n; j++) {

            int xs = (int)Math.pow(cows[i][0] - cows[j][0], 2);
            int ys = (int)Math.pow(cows[i][1] - cows[j][1], 2);

            dists[count][0] = i;
            dists[count][1] = j;
            dists[count][2] = xs + ys;
            count++;
        } // 500
    } // 1000

    sort(dists, 2, 1);

    // for (int thisisaweirdname = 0; thisisaweirdname < dists.length; thisisaweirdname++) System.out.println(Arrays.toString(dists[thisisaweirdname]));
    return dists;
  }

  public static void sort(int[][] arr, int place, int ascend) {
    // place is what it should sort by
    // ascend is ascending or descending: 1 = ascending, -1 is descending
    Arrays.sort(arr, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            return a[place] - b[place] * ascend;
        }
    });
  }

  private static class DisjointSets {
    int[] parents;  // 0-indexed
    int[] sizes;
    public DisjointSets(int size) {
        sizes = new int[size];
        parents = new int[size];
        Arrays.fill(sizes, 1);
        Arrays.fill(parents, -1);
    }

    // finds the "representative" node in a's component
    public int find(int x) {
        return parents[x] == -1 ? x : (parents[x] = find(parents[x]));
    }

    public int getfromsizes(int ind) {
        return sizes[ind];
    }

    // returns whether the merge changed connectivity
    public boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return false;
        }
        if (sizes[xRoot] < sizes[yRoot]) {
            parents[xRoot] = yRoot;
            sizes[yRoot] += sizes[xRoot];
        } else {
            parents[yRoot] = xRoot;
            sizes[xRoot] += sizes[yRoot];
        }
        // System.out.println("Parents: " + Arrays.toString(parents));
        // System.out.println("Sizes  : " + Arrays.toString(sizes));
        return true;
    }

    // returns whether two nodes are in the same connected component
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
}