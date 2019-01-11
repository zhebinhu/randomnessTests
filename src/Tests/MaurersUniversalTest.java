package Tests;

import java.util.Iterator;
import java.util.List;

import static java.lang.Math.*;
import static org.apache.commons.math3.special.Erf.erfc;

/**
 * Create by huzhebin on 2019/1/8
 */
public class MaurersUniversalTest {
    private static double Mut_factor_C(int L, int K) {
        double v;
        v = 0.7;
        v -= 0.8 / L;
        v += (4.0 + 32.0 / L) * (pow(K, -3.0 / L) / 15.0);
        return v;
    }

    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("MaurersUniversalTest:args wrong");
            return -1;
        }
        int L = 7;
        int Q = 1280;

        int[] T = new int[1 << L];
        int mask = (1 << L) - 1;
        int n = bits.size();
        int K = n / L - Q;
        int n_disc = n % L;
        double sum = 0.0;
        double V = 0;
        double P;
        double sigma;
        final double[] expected_value = {0, 0, 0, 0, 0, 0, 5.2177052, 6.1962507, 7.1836656,
                8.1764248, 9.1723243, 10.170032, 11.168765,
                12.168070, 13.167693, 14.167488, 15.167379};
        final double[] variance = {0, 0, 0, 0, 0, 0, 2.954, 3.125, 3.238, 3.311, 3.356, 3.384,
                3.401, 3.410, 3.416, 3.419, 3.421};

        int tmp = 0;
        Iterator<Boolean> it = bits.iterator();
        for (int i = 1; i <= Q; i++) {
            for (int j = 0; j < L; j++) {
                tmp <<= 1;
                if (it.next()) tmp++;
            }
            T[tmp & mask] = i;
        }

        for (int i = Q + 1; i <= Q + K; i++) {
            for (int j = 0; j < L; j++) {
                tmp <<= 1;
                if (it.next()) tmp++;
            }
            sum += log(i - T[tmp & mask]) / log(2.0);
            T[tmp & mask] = i;
        }

        sigma = sqrt(variance[L] / K) * Mut_factor_C(L, K);
        V = (sum / K - expected_value[L]) / sigma;
        P = erfc(abs(V) / sqrt(2.0));

        return P;
    }
}