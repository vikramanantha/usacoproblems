// Vikram Anantha
// Nov 8 2022
// USACO 2020 February Contest, Silver
// Problem 2. Triangles

import java.util.*;
import java.io.*;
public class Triangles {
  static int n;
  static long result;
  static int[][] posts;
  static HashMap<Integer, ArrayList<Integer>> xs = new HashMap<>();
  static HashMap<Integer, ArrayList<Integer>> ys = new HashMap<>();
  static HashSet<Integer> listox = new HashSet<>();
  static HashSet<Integer> listoy = new HashSet<>();
  static long[][] nums;
  public static void main(String[] args) throws FileNotFoundException {
    // String file_name = "__fillerFile__";
    String file_name = "triangles";
    Scanner in = new Scanner(new File(file_name + ".in"));
    
    n = in.nextInt();
    posts = new int[n][2];
    
    for (int tsawn1 = 0; tsawn1 < posts.length; tsawn1++) {
        for (int tsawn2 = 0; tsawn2 < posts[0].length; tsawn2++) {
                posts[tsawn1][tsawn2] = in.nextInt();
    }}

    // Arrays.sort(posts, new Comparator<int[]>() {
    //     public int compare(int[] a, int[] b) {
    //         int ind = 1;
    //         if (a[ind] == b[ind]) return a[1-ind] - b[1-ind];
    //         return a[ind] - b[ind];
    //     }
    // });

    for (int i = 0; i < n; i++) {
        int x = posts[i][0];
        int y = posts[i][1];

        if (!xs.containsKey(x)) xs.put(x, new ArrayList<Integer>());
        xs.get(x).add(i);

        if (!ys.containsKey(y)) ys.put(y, new ArrayList<Integer>());
        ys.get(y).add(i);

        listox.add(x);
        listoy.add(y);
    }

    // System.out.println(xs);
    // System.out.println(ys);

    result = 0;
    nums = new long[n][2];
    Iterator<Integer> it = listoy.iterator();
    while (it.hasNext()) {
        
        ArrayList<Integer> thisy = ys.get(it.next());

        Collections.sort(thisy, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return posts[a][0] - posts[b][0];
            }
        });

        int s1 = 0;

        for (int i = 1; i < thisy.size(); i++) {
            s1 += (posts[thisy.get(i)][0] - posts[thisy.get(0)][0]);
        }
        int si = s1;
        nums[thisy.get(0)][0] = s1;
        for (int i = 1; i < thisy.size(); i++) {
            int si1 = si + (2*i - thisy.size()) * (posts[thisy.get(i)][0] - posts[thisy.get(i-1)][0]);
            si = si1;

            nums[thisy.get(i)][0] = si1;
        }
    }

    it = listox.iterator();
    while (it.hasNext()) {
        
        ArrayList<Integer> thisx = xs.get(it.next());

        Collections.sort(thisx, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return posts[a][1] - posts[b][1];
            }
        });

        int s1 = 0;

        for (int i = 1; i < thisx.size(); i++) {
            s1 += (posts[thisx.get(i)][1] - posts[thisx.get(0)][1]);
        }
        int si = s1;
        nums[thisx.get(0)][1] = s1;
        for (int i = 1; i < thisx.size(); i++) {
            int si1 = si + (2*i - thisx.size()) * (posts[thisx.get(i)][1] - posts[thisx.get(i-1)][1]);
            si = si1;

            nums[thisx.get(i)][1] = si1;
        }
    }

    // for (int tsawn = 0; tsawn < nums.length; tsawn++) System.out.println(Arrays.toString(nums[tsawn]));
    
    for (int i = 0; i < nums.length; i++) {
        result += nums[i][0] * nums[i][1];

        // long newresult = result - Long.MIN_VALUE;

        // System.out.println(newresult);
        // System.out.println(Long.MAX_VALUE);
        result = result % (1000000007);
    }

    // result = result % (1000000007);

    PrintWriter out = new PrintWriter(new File(file_name + ".out"));
    System.out.println(result);
    out.println(result);
    in.close();
    out.close();
  }
}

// 15562562500
// 9223372036854775807
// 9223372036237469124







// import java.util.*;
// import java.io.*;

// public class Triangles {
// 	static class Fence {
// 		int x;
// 		int y;
// 		// terminology: anchor point = vertex of the right angle in a right triangle
// 		// sum of heights of all triangles that use this fence as an anchor point
// 		long heightsum;
// 		// sum of the bases of all triangles that use this fence as an anchor point
// 		long basesum;
// 	}

// 	static class Pair implements Comparable<Pair> {
// 		int first, second;

// 		public Pair(int x, int y) {
// 			first = x;
// 			second = y;
// 		}

// 		public int compareTo(Pair x) {
// 			if (this.first == x.first) return this.second - x.second;
// 			return this.first - x.first;
// 		}
// 	}
	
// 	static final int MOD = (int) 1e9 + 7;
// 	static final int MAX_C = (int) 1e4;

// 	public static void main(String[] args) throws IOException {
// 		BufferedReader in = new BufferedReader(new FileReader("__fillerFile__.in"));
// 		PrintWriter pw = new PrintWriter("__fillerFile__.out");
// 		StringTokenizer st = new StringTokenizer(in.readLine());
		
// 		int n = Integer.parseInt(st.nextToken());
// 		Fence[] fences = new Fence[n];
// 		// all possible x coordinates of the fences (+1 to account for 0)
// 		ArrayList<Pair>[] xcoords = new ArrayList[2 * MAX_C + 1];
// 		// all possible y coordinates of the fences
// 		ArrayList<Pair>[] ycoords = new ArrayList[2 * MAX_C + 1];
// 		for (int i = 0; i < n; i++) {
// 			st = new StringTokenizer(in.readLine());
// 			fences[i] = new Fence();
// 			fences[i].x = Integer.parseInt(st.nextToken());
// 			fences[i].y = Integer.parseInt(st.nextToken());
// 			// we add MAX_C to make all of our coordinates positive
// 			// so that we won't have negative indices
// 			if (xcoords[fences[i].x + MAX_C] == null)
// 				xcoords[fences[i].x + MAX_C] = new ArrayList<>();
// 			if (ycoords[fences[i].y + MAX_C] == null)
// 				ycoords[fences[i].y + MAX_C] = new ArrayList<>();
			
// 			xcoords[fences[i].x + MAX_C].add(new Pair(fences[i].y, i));
// 			ycoords[fences[i].y + MAX_C].add(new Pair(fences[i].x, i));
// 		}

// 		for (int i = 0; i <= 2 * MAX_C; i++) {
// 			if (xcoords[i] != null) {
// 				// cur is the value of the current s_i
// 				long cur = 0;
// 				// sort all y positions of all points w/ the same x pos
// 				Collections.sort(xcoords[i]);
// 				/*
// 				 * then we compute the value s_1 of this set:
// 				 * the sum of the heights of all the triangles that
// 				 * have their anchor point at (i, xcoords[i][0].first)
// 				 */
// 				for (int j = 1; j < xcoords[i].size(); j++) {
// 					cur += xcoords[i].get(j).first - xcoords[i].get(0).first;
// 				}
// 				fences[xcoords[i].get(0).second].heightsum = cur;
// 				// then we compute the rest of the s_i for this set
// 				for (int j = 1; j < xcoords[i].size(); j++) {
// 					cur += (2 * j - xcoords[i].size())
// 							* (xcoords[i].get(j).first - xcoords[i].get(j - 1).first);
// 					fences[xcoords[i].get(j).second].heightsum = cur;
// 				}
// 			}
// 		}

// 		// we do the sums of bases in exactly the same way
// 		for (int i = 0; i <= 2 * MAX_C; i++) {
// 			if (ycoords[i] != null) {
// 				long cur = 0;
// 				Collections.sort(ycoords[i]);
// 				for (int j = 1; j < ycoords[i].size(); j++) {
// 					cur += ycoords[i].get(j).first - ycoords[i].get(0).first;
// 				}
// 				fences[ycoords[i].get(0).second].basesum = cur;
// 				for (int j = 1; j < ycoords[i].size(); j++) {
// 					cur += (2 * j - ycoords[i].size())
// 							* (ycoords[i].get(j).first - ycoords[i].get(j - 1).first);
// 					fences[ycoords[i].get(j).second].basesum = cur;
// 				}
// 			}
// 		}

// 		// finally we compute the total area
// 		int totalArea = 0;
// 		for (int i = 0; i < n; i++) {
//             if (fences[i].heightsum * fences[i].basesum != 0) {
//             System.out.println(fences[i].heightsum + " " + fences[i].basesum);
//             }
// 			totalArea += fences[i].heightsum * fences[i].basesum % MOD;
// 			totalArea %= MOD;
// 		}
// 		pw.println(totalArea);
//         System.out.println(totalArea);
// 		pw.close();
// 	}
// }