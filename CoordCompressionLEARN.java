// Vikram Anantha
// Oct 02 2022
// Learning about Coord Compression Implementation
// https://usaco.guide/silver/sorting-custom?lang=java#problem-usaco-967

// REALLY useful when you have a connected graph, and you want to find
// the least weight on an edge you can have to connect all the nodes

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CoordCompressionLEARN {
	static int[] xs;
	static int[] ys;
	static Integer[] cows;
	static int n;
	static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		n = in.nextInt();

		xs = new int[n];
		ys = new int[n];

		cows = new Integer[n];
		for (int j = 0; j < n; j++) {
			xs[j] = in.nextInt();
			ys[j] = in.nextInt();
			cows[j] = j;
		}

		System.out.println(Arrays.toString(xs));
		System.out.println(Arrays.toString(ys));
		System.out.println(Arrays.toString(cows));
		System.out.println("--------");
		// [8, 5, 5, 9]
		// [4, 3, 1, 9]
		// [0, 1, 2, 3]
		// --------

		Arrays.sort(cows, Comparator.comparingInt(j -> xs[j]));
		System.out.println(Arrays.toString(cows));
		System.out.println("--------");
		// [1, 2, 0, 3]
		// --------
		// meaning the number at position cows[i] is the (i+1)th smallest
		// example:
		//		the cow at position 0 is 3rd smallest (8)
		//      the cow at position 1 is 1st smallest (5)
		//      the cow at position 2 is 2nd smallest (5)
		//      the cow at position 3 is 4th smallest (9)

		for (int x = 1; x <= n; x++) {
			xs[cows[x - 1]] = x;
		}
		System.out.println(Arrays.toString(xs));
		System.out.println(Arrays.toString(ys));
		System.out.println(Arrays.toString(cows));
		System.out.println("--------");
		// [3, 1, 2, 4]
		// [4, 3, 1, 9]
		// [1, 2, 0, 3]
		// --------

		Arrays.sort(cows, Comparator.comparingInt(j -> ys[j]));
		System.out.println(Arrays.toString(cows));
		System.out.println("--------");
		// [2, 1, 0, 3]
		// --------

		for (int y = 1; y <= n; y++) {
			ys[cows[y - 1]] = y;
		}
		System.out.println(Arrays.toString(xs));
		System.out.println(Arrays.toString(ys));
		System.out.println(Arrays.toString(cows));
		System.out.println("--------");
		// [3, 1, 2, 4]
		// [3, 2, 1, 4]
		// [2, 1, 0, 3]
		// --------
	}

	public static void CoordCompress() {

		xs = new int[n];
		ys = new int[n];

		Integer[] fillerarray = new Integer[n];
		for (int j = 0; j < n; j++) {
			xs[j] = in.nextInt();
			ys[j] = in.nextInt();
			fillerarray[j] = j;
		}

		Arrays.sort(fillerarray, Comparator.comparingInt(j -> xs[j]));
		for (int x = 0; x < n; x++) {
			xs[fillerarray[x]] = x;
		}
		Arrays.sort(fillerarray, Comparator.comparingInt(j -> ys[j]));
		for (int y = 0; y < n; y++) {
			ys[fillerarray[y]] = y;
		}

	}

}

	
