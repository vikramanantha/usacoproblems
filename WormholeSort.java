// Vikram Anantha
// Aug 30 2022
// 2020 / Jan / Problem 1
// 10 / 10

import java.util.*;
import java.io.*;
public class WormholeSort {
  static int n;
  static int m;
  static long result;
  static int[] order;
  static int[][] wormholes;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "wormsort";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    m = in.nextInt();
    order = new int[n];
    wormholes = new int[m][3];
    for (int tsawn = 0; tsawn < order.length; tsawn++) order[tsawn] = in.nextInt()-1;

    for (int tsawn1 = 0; tsawn1 < wormholes.length; tsawn1++) {
        for (int tsawn2 = 0; tsawn2 < wormholes[0].length; tsawn2++) {
            wormholes[tsawn1][tsawn2] = in.nextInt()-1;
    }}

    sort(wormholes, 2, -1);
    // for (int tsawn = 0; tsawn < wormholes.length; tsawn++) System.out.println(Arrays.toString(wormholes[tsawn]));
    result = 0;

    result = findans();
    result++;
    

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }

  public static int findans() {
    DisjointSets dsu = new DisjointSets(n);
    boolean done = true;
    for (int j = 0; j < order.length; j++) {
        if (!dsu.connected(j, order[j])) {
            done = false;
            break;
        }
    }
    if (done) return -2;
    for (int i = 0; i < m; i++) {
        dsu.union(wormholes[i][0], wormholes[i][1]);
        done = true;
        for (int j = 0; j < order.length; j++) {
            if (!dsu.connected(j, order[j])) {
                done = false;
                break;
            }
        }
        if (done) return wormholes[i][2];
    }
    return 0;
  }

  public static void sort(int[][] arr, int place, int ascend) {
    // place is what it should sort by
    // ascend is ascending or descending: 1 = ascending, -1 is descending
    Arrays.sort(arr, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            return (a[place] - b[place]) * ascend;
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
  
    // finds the representative node in a's component
    public int find(int x) {
        return parents[x] == -1 ? x : (parents[x] = find(parents[x]));
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
        // System.out.println(Arrays.toString(parents));
        // System.out.println(Arrays.toString(sizes));
        return true;
    }
  
    // returns whether two nodes are in the same connected component
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
   }
}

/*
 
4 4
3 2 1 4
1 2 9
1 3 7
2 3 10
2 4 3

    3   2   1   4
|---1---2---3---4---|
    A---A -> 9
    B-------B -> 7
        C---C -> 10
        D-------D -> 3

 */