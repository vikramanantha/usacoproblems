// Vikram Anantha
// Oct 9 2022
// USACO 2020 December Contest, Silver
// Problem 3. Stuck in a Rut

import java.util.*;
import java.io.*;
public class StuckInARut {
    static int n;
    static int[][] cows;
    static long result;
    static ArrayList<Integer> Ns = new ArrayList<>();
    static ArrayList<Integer> Es = new ArrayList<>();
    static int[][] matrix;
    static int[][] meetings;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("__fillerFile__.in"));
        n = in.nextInt();
        cows = new int[n][3];
        matrix = new int[n][n];
        for (int tsawn1 = 0; tsawn1 < cows.length; tsawn1++) {
            String s = in.next();
            if (s.equals("E")) {
                cows[tsawn1][0] = 1;
                Es.add(tsawn1);
            }
            else {
                cows[tsawn1][0] = 0;
                Ns.add(tsawn1);
            }
            for (int tsawn2 = 1; tsawn2 < cows[0].length; tsawn2++) {
                cows[tsawn1][tsawn2] = in.nextInt();
        }}
        // E is 1, N is 0;

        for (Integer i : Ns) {
            for (Integer j : Es) {

                int xd = cows[i][1] - cows[j][1];
                int yd = cows[j][2] - cows[i][2];
                if (xd < 0 || yd < 0) continue;
                if (xd > yd) {
                    // north stops east
                    matrix[i][j] = xd;
                    matrix[j][i] = xd*(-1);
                }
                if (xd < yd) {
                    // east stops north
                    matrix[i][j] = yd*(-1);
                    matrix[j][i] = yd;
                }
            }
        }


        // for (int tsawn = 0; tsawn < matrix.length; tsawn++) System.out.println(Arrays.toString(matrix[tsawn]));
        
        Collections.sort(Es, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return cows[a][2] - cows[b][2];
            }
        });
        Collections.sort(Ns, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return cows[a][1] - cows[b][1];
            }
        });


        int[] results = new int[n];
        HashSet<Integer> stopped = new HashSet<>();

        for (Integer j : Es) {
            for (Integer i : Ns) {
                if (matrix[i][j] == 0) continue;
                if (stopped.contains(i)) continue;
                if (stopped.contains(j)) continue;

                if (matrix[i][j] > 0) { // north won
                    stopped.add(j);
                    results[i] += 1 + results[j];
                } else {
                    stopped.add(i);
                    results[j] += 1 + results[i];
                }
            }
        }

        // meetings = new int[Ns.size() * Es.size()][4];
        // int count = 0;
        // for (Integer i : Ns) {
        //     for (Integer j : Es) {
        //         if (matrix[i][j] == 0) continue;

        //         meetings[count][0] = Math.abs(matrix[i][j]);
        //         meetings[count][1] = i;
        //         meetings[count][2] = j;
        //         meetings[count][3] = matrix[i][j] > 0 ? 0 : 1;
        //         count++;
        //     }
        // }

        // Arrays.sort(meetings, new Comparator<int[]>() {
        //     public int compare(int[] a, int[] b) {
        //         if (a[0] == 0) return 1;
        //         if (b[0] == 0) return -1;

        //         return a[0] - b[0];
        //     }
        // });

        // for (int tsawn = 0; tsawn < meetings.length; tsawn++) System.out.println(Arrays.toString(meetings[tsawn]));
        
        // int[] results = new int[n];
        // HashSet<Integer> stopped = new HashSet<>();

        // // for (int i = 0; i < meetings.length; i++) {
        // for (int[] meeting : meetings) {
        //     if (meeting[0] == 0) continue;
        //     if (stopped.contains(meeting[1])) continue;
        //     if (stopped.contains(meeting[2])) continue;

        //     int winner = meeting[3];
        //     results[meeting[winner]]++;
        //     stopped.add(meeting[1-winner]);

        // }

        

        for (int i : results) System.out.println(i);
        in.close();
    }
}

/*
    A       C   D       F
  [ 0,  0,  0,  0, -8,  0]
B [ 0,  0, -3,  0,  0,  0]
  [ 0,  3,  0,  0, -7,  0]
  [ 0,  0,  0,  0,  3,  0]
E [ 8,  0,  7, -3,  0,  2]
  [ 0,  0,  0,  0, -2,  0]

  East sorted in order (bottom to top):
  F, D, A, C

  North sorted in order (left to right):
  B, E

  > means stops

  E > F :: 2  <F>  >> E's at fault
  E < D :: 3  <F, E> >> D's at fault
  B < C :: 3  <F, E, B> >> C's at fault
  E > C :: 7 ----
  E > A :: 8 ---

  E > F :: 2  <F>
  E < D :: 3  <F, E>
  B < C :: 3  <F, E, B>

 EF---2
 ED---3
 EA---8
 EC---7

 BC---3

6
E 3 5
N 5 3
E 4 6
E 10 4
N 11 1
E 9 2

A. E 3 5
B. N 5 3
C. E 4 6
D. E 10 4
E. N 11 1
F. E 9 2
8 |
  |
6 |       C>
  |     A>
4 |                   D>
  |         B^
2 |                 F>      G^
  |                     E^
--+--------------------------------------
    1   3   5   7   9   11  13  15  17  19

E. N 11 1
B. N 5 3
    x
A. E 3 5
C. E 4 6
D. E 10 4
F. E 9 2

ExA ==>
    xD = 11-3 = 8
    yD = 5-1 = 4
    ys intersect first
    E stops A at time 8
ExC
ExD ==> 
ExF ==>
    xD = 2
    yD = 1
    north stops east
    E stops F at time 2
BxA
BxC
BxD
BxF

8 |
  |
6 |       >
  |     >
4 |                   >
  |         ^
2 |                 >
  |                     ^
--+--------------------------------------
    1   3   5   7   9   11  13  15  17  19

8 |
  |
6 |       > >
  |     > >
4 |         ^         > >
  |         ^
2 |                 > > ^
  |                     ^
--+--------------------------------------
    1   3   5   7   9   11  13  15  17  19


8 |
  |
6 |       > > >
  |     > > X
4 |         ^         > > >
  |         ^           ^
2 |                 > >|^|
  |                     ^
--+--------------------------------------
    1   3   5   7   9   11  13  15  17  19

8 |
  |
6 |       >|>|> >
  |     > > X >
4 |         ^         >|>|> >
  |         ^           ^
2 |                 > >|^|
  |                     ^
--+--------------------------------------
    1   3   5   7   9   11  13  15  17  19

8 |
  |
6 |       >|>|> > >
  |     > > X > >
4 |         ^         >|>|> > >
  |         ^           ^
2 |                 > >|^|
  |                     ^
--+--------------------------------------
    1   3   5   7   9   11  13  15  17  19

 */