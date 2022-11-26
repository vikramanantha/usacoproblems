// // Vikram Anantha
// // Oct 2 2022
// // USACO 2020 December Contest, Silver
// // Problem 2. Rectangular Pasture

// import java.util.*;
// import java.io.*;
// public class RectangularPasture {
//     static int n;
//     static long result;
//     public static void main(String[] args) throws IOException {
//       Scanner in = new Scanner(System.in);
//       // Scanner in = new Scanner(new File("__fillerFile__.in"));
//       n = in.nextInt();
//       int[] xs = new int[n];
//       int[] ys = new int[n];

//       Integer[] fillerarray = new Integer[n];
//       for (int j = 0; j < n; j++) {
//         xs[j] = in.nextInt()-1;
//         ys[j] = in.nextInt()-1;
//         fillerarray[j] = j;
//       }

//       Arrays.sort(fillerarray, Comparator.comparingInt(j -> xs[j]));
//       for (int x = 0; x < n; x++) {
//         xs[fillerarray[x]] = x;
//       }
//       Arrays.sort(fillerarray, Comparator.comparingInt(j -> ys[j]));
//       for (int y = 0; y < n; y++) {
//         ys[fillerarray[y]] = y;
//       }


//       long[][] cows = new long[n][n];
//       for (int i = 0; i < n; i++) {
//         cows[xs[i]][ys[i]]++;
//       }

//       // for (int tsawn = 0; tsawn < cows.length; tsawn++) System.out.println(Arrays.toString(cows[tsawn]));
//       // System.out.println(Arrays.toString(xs));
//       // System.out.println(Arrays.toString(ys));

//       long[][] prefix = prefix2d(cows);

          
//       result = 0;

//       for (int i = 0; i < n; i++) {
//         for (int j = i+1; j < n; j++) {

//           int minx = Math.min(xs[i], xs[j]);
//           int maxx = Math.max(xs[i], xs[j]);
//           int miny = Math.min(ys[i], ys[j]);
//           int maxy = Math.max(ys[i], ys[j]);

//           long lowercount = findsum(minx, 0, maxx, miny, prefix);
//           long uppercount = findsum(minx, maxy, maxx, n-1, prefix);

//           // System.out.println("(" + xs[i] + "," + ys[i] + "), " + "(" + xs[j] + "," + ys[j] + "), ");
//           // System.out.println(lowercount + " " + uppercount);
//           // printslice(minx, 0, maxx, miny, cows);
//           // System.out.println("--");
//           // printslice(minx, maxy, maxx, n-1, cows);
//           // System.out.println("\n--------\n");


//           result += lowercount * uppercount;
//           System.out.println(result);

//         }
//       }

//       result += n + 1;
      

//       System.out.println(result);
//       in.close();
//     }

//     public static long findsum(int x1, int y1, int x2, int y2, long[][] prefix) {
//       int sum = 0;

//       sum += prefix[y2+1][x2+1];
//       sum -= prefix[y1+1-1][x2+1];
//       sum -= prefix[y2+1][x1+1-1];
//       sum += prefix[y1+1-1][x1+1-1];
//       return sum;
//     }

//     public static long[][] prefix2d(long[][] arr) {
//       long[][] prefix = new long[arr.length+1][arr[0].length+1];

//       for (int i = 1; i < prefix.length; i++) {
//           for (int j = 1; j < prefix[i].length; j++) {
//               prefix[i][j] += arr[i-1][j-1];
//               if (i == 0 || j == 0) continue;
//               prefix[i][j] += prefix[i-1][j] + prefix[i][j-1];
//               prefix[i][j] -= prefix[i-1][j-1];

//           }
//       }

//       return prefix;
//     }

//     public static void printslice (int x1, int y1, int x2, int y2, int[][] arr) {
//       System.out.print("[");
//       for (int i = y1; i <= y2; i++) {
//           for (int j = x1; j <= x2; j++) {
//               System.out.print(arr[i][j] + ",");
//           }
//           System.out.println();
//           System.out.print(" ");
//       }
//       System.out.println("]");
//     }
// }

// /*
//     0   1   2   3   4
//   +-------------------
// 0 |     B
//   |
// 1 |
//   |
// 2 | A
//   |
// 3 |         C
//   |
// 4 |
//   |
// 5 |             D

// A ==> (0, 2) ==> (0, 1)
// B ==> (1, 0) ==> (1, 0)
// C ==> (2, 3) ==> (2, 2)
// D ==> (3, 5) ==> (3, 3)


//     0   1   2   3
//   +--------------
// 0 |     B
//   |
// 1 | A
//   |
// 2 |         C
//   |
// 3 |             D




//     0   1   2   3
//   +--------------
// 0 |-+---B---+---+
//   | |   |   |   |
// 1 |-A---+---+---+
//   | |   |   |   |
// 2 |-+---+---C---+
//   | |   |   |   |
// 3 |-+---+---+---D

// answer += getSum(Math.min(xs[j], xs[k]), Math.max(xs[j], xs[k]), 1, Math.min(ys[j], ys[k]))
// 					* getSum(Math.min(xs[j], xs[k]), Math.max(xs[j], xs[k]), Math.max(ys[j], ys[k]), n);


// answer += getSum(2, 3, 0, 2) * getSum(2, 3, 3, 3)

//  */
// BeginCodeSnip{Solution code}
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class RectangularPasture {
	static int[][] sums;

	static int getSum(int fromX, int toX, int fromY, int toY) {
		return sums[toX][toY] - sums[fromX - 1][toY] - sums[toX][fromY - 1] + sums[fromX - 1][fromY - 1];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		//EndCodeSnip

		int[] xs = new int[n];
		int[] ys = new int[n];

		Integer[] cows = new Integer[n];
		for (int j = 0; j < n; j++) {
			xs[j] = in.nextInt();
			ys[j] = in.nextInt();
			cows[j] = j;
		}

		Arrays.sort(cows, Comparator.comparingInt(j -> xs[j]));
		for (int x = 1; x <= n; x++) {
			xs[cows[x - 1]] = x;
		}
		Arrays.sort(cows, Comparator.comparingInt(j -> ys[j]));
		for (int y = 1; y <= n; y++) {
			ys[cows[y - 1]] = y;
		}

//BeginCodeSnip{Solution code}
		sums = new int[n + 1][n + 1];
		for (int j = 0; j < n; j++) {
			sums[xs[j]][ys[j]]++;
		}
		for (int x = 0; x <= n; x++) {
			for (int y = 0; y <= n; y++) {
				if (x > 0) {
					sums[x][y] += sums[x - 1][y];
				}
				if (y > 0) {
					sums[x][y] += sums[x][y - 1];
				}
				if (x > 0 && y > 0) {
					sums[x][y] -= sums[x - 1][y - 1];
				}
			}
		}
		long answer = n + 1;
		for (int j = 0; j < n; j++) {
			for (int k = j + 1; k < n; k++) {
				answer += getSum(Math.min(xs[j], xs[k]), Math.max(xs[j], xs[k]), 1, Math.min(ys[j], ys[k]))
					* getSum(Math.min(xs[j], xs[k]), Math.max(xs[j], xs[k]), Math.max(ys[j], ys[k]), n);

        // System.out.println(answer);
			}
		}
		System.out.println(answer);
	}
}
//EndCodeSnip