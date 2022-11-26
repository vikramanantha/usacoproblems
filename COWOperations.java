// Vikram Anantha
// Nov 19 2022
// USACO 2022 US Open Contest, Silver
// Problem 3. COW Operations

import java.util.*;
import java.io.*;
public class COWOperations {
    static int qn;
    static int[][] qs;
    static String s;
    static int[][] prefix;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("__fillerFile__.in"));
        s = in.next();
        qn = in.nextInt();
        qs = new int[qn][2];
        prefix = new int[s.length()+1][3];

        for (int tsawn1 = 0; tsawn1 < qs.length; tsawn1++) {
            for (int tsawn2 = 0; tsawn2 < qs[0].length; tsawn2++) {
                 qs[tsawn1][tsawn2] = in.nextInt()-1;
        }}
        

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 3; j++) prefix[i+1][j] = prefix[i][j];
            if (s.charAt(i) == 'C') prefix[i+1][0] = 1 - prefix[i+1][0];
            if (s.charAt(i) == 'O') prefix[i+1][1] = 1 - prefix[i+1][1];
            if (s.charAt(i) == 'W') prefix[i+1][2] = 1 - prefix[i+1][2];
        }

        
        for (int[] q : qs) {
            int cs = Math.abs(prefix[q[1]+1][0] - prefix[q[0]][0]);
            int os = Math.abs(prefix[q[1]+1][1] - prefix[q[0]][1]);
            int ws = Math.abs(prefix[q[1]+1][2] - prefix[q[0]][2]);

            if (cs == 0 && os == 1 && ws == 1) System.out.print('Y');
            else if (cs == 1 && os == 0 && ws == 0) System.out.print('Y');
            else System.out.print('N');
        }
        in.close();
    }
}
