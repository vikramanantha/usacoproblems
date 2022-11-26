// Vikram Anantha
// Aug 30 2022
// Learning about Union Find
// https://usaco.guide/gold/dsu?lang=java#problem-usaco-669

// REALLY useful when you have a connected graph, and you want to find
// the least weight on an edge you can have to connect all the nodes


/*
 
>> INPUT
5
5
0 1 2
0 2 3
0 0 4
1 0 1
1 1 3

disjointed set of 5 (0-4)
5 queries:

connect 1 and 2
connect 2 3
connect 0 and 4
ask if 0 and 1 are in same set (no)
ask if 1 and 3 are in same set (yes)

>> OUTPUT
0
1

 */

import java.io.*;
import java.util.*;

public class UnionFindLEARN {
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

	public static void main(String[] args) {
		Kattio io = new Kattio();
		int size = io.nextInt();
		int queryNum = io.nextInt();

		DisjointSets dsu = new DisjointSets(size);
		for (int i = 0; i < queryNum; i++) {

			int type = io.nextInt();
			int u = io.nextInt();
			int v = io.nextInt();
			if (type == 0) {
				dsu.union(u, v);
			} else {
				if (dsu.connected(u, v)) {
					io.println(1);
				} else {
					io.println(0);
				}
			}
		}
		io.close();
	}

	//BeginCodeSnip{Kattio}
	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;
		// standard input
		public Kattio() { this(System.in, System.out); }
		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}
		// USACO-style file input
		public Kattio(String problemName) throws IOException {
			super(problemName + ".out");
			r = new BufferedReader(new FileReader(problemName + ".in"));
		}
		// returns null if no more input
		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) { }
			return null;
		}
		public int nextInt() { return Integer.parseInt(next()); }
		public double nextDouble() { return Double.parseDouble(next()); }
		public long nextLong() { return Long.parseLong(next()); }
	}
	//EndCodeSnip
}