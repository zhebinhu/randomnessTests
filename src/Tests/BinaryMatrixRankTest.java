package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;
import static Tests.Utils.rank;
import static java.lang.Math.min;

/**
 * Create by huzhebin on 2019/1/7
 */
public class BinaryMatrixRankTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("BinaryMatrixRankTest:args wrong");
            return -1;
        }
        int M = 32;
        int Q = 32;
        int n = bits.size();
        int N = n / (M * Q);
        int n_disc = n % (M * Q);
        int Fm = 0, Fm1 = 0, Fr = 0;
        int[][] matrix = new int[32][32];
        double V, P;

        int r;
        Iterator<Boolean> it = bits.iterator();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < Q; k++) {
                    if (it.next()) {
                        matrix[j][k] = 1;
                    } else {
                        matrix[j][k] = 0;
                    }
                }
            }
            r = rank(matrix, M);

            if (r == min(M, Q)) Fm++;
            else if (r == (min(M, Q) - 1)) Fm1++;
            else Fr++;
        }
        V = ((Fm - 0.2888 * N) * (Fm - 0.2888 * N)) / (0.2888 * N) +
                ((Fm1 - 0.5776 * N) * (Fm1 - 0.5776 * N)) / ((0.5776 * N) +
                (Fr - 0.1336 * N) * (Fr - 0.1336 * N)) / (0.1336 * N);

        P = igamc(1, V / 2.0);

        return P;
    }
}
