// Vikram Anantha
// Sept 1 2022
// 2018 / Jan / Problem 1 - GOLD

import java.util.*;
import java.io.*;
public class MootubeGOLD {
  static int n;
  static int q;
  static long result;
  static int[][] edges;
  static int[][] qs;
  public static void main(String[] args) throws FileNotFoundException {
    String file_name = "mootube";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    q = in.nextInt();
    edges = new int[n-1][3];
    qs = new int[q][4];

    for (int i = 0; i < n-1; i++) {
        edges[i][0] = in.nextInt()-1;
        edges[i][1] = in.nextInt()-1;
        edges[i][2] = in.nextInt();
    }

    for (int i = 0; i < q; i++) {
        qs[i][0] = in.nextInt();
        qs[i][1] = in.nextInt()-1;
        qs[i][2] = i;
    }

    sort(edges, 2, -1);
    sort(qs, 0, -1);

    // DisjointSets dsu = new DisjointSets(n);
    // for (int i = 0; i < edges.length; i++) {
    //     dsu.union(edges[i][0], edges[i][1]);
    // }

    DisjointSets dsu = new DisjointSets(n);
    
    
    result = 0;
    
    int e = 0;
    for (int i = 0; i < q; i++) {
        int k = qs[i][0];
        int v = qs[i][1];
        // int kprime = edges[e][2];
        while (e < edges.length && edges[e][2] >= k) {
            dsu.union(edges[e][0], edges[e][1]);
            e++;
        }
        // for (int d = 0; d < n; d++) System.out.println(">>" + dsu.find(d));
        int vids = dsu.getsizes()[dsu.find(v)]-1;
        
        qs[i][3] = vids;
    }

    sort(qs, 2, 1);

    int[] ansarr = new int[q];
    for (int i = 0; i < q; i++) {
        ansarr[qs[i][2]] = qs[i][3];
    }

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    for (int i = 0; i < qs.length; i++) {
        // System.out.println(ansarr[i]);
        out.println(ansarr[i]);
    }
    in.close();
    out.close();
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

    public DisjointSets(int[] p, int[] s) {
        sizes = s;
        parents = p;
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
        return true;
    }
  
    // returns whether two nodes are in the same connected component
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    public int[] getsizes() {
        return sizes;
    }
    public int[] getparents() {
        return parents;
    }
   }
}
/*
 
1---2----4
    |
    |
    3

 */