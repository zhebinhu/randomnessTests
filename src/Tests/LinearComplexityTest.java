package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;
import static Tests.Utils.linearComplexity;
import static java.lang.Math.pow;

/**
 * Create by huzhebin on 2019/1/8
 */
public class LinearComplexityTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("LinearComplexityTest:args wrong");
            return -1;
        }
        int m = 500;

        int n = bits.size();
        int N = n / m;

        double[] v = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] pi = {0.010417, 0.03125, 0.12500, 0.5000, 0.25000, 0.06250, 0.020833};
        double V = 0.0;
        double P;

        boolean[] arr;
        int complexity;
        double T, miu;
        arr = new boolean[m];
        miu = m / 2.0 + (9.0 + (m % 2 == 0 ? 1.0 : -1.0)) / 36.0 - (m / 3.0 + 2.0 / 9.0) / pow(2.0, m);
        Iterator<Boolean> it = bits.iterator();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < m; j++) {
                arr[j] = it.next();
            }
            complexity = linearComplexity(arr, m);
            T = (m % 2 == 0 ? 1.0 : -1.0) * (complexity - miu) + 2.0 / 9.0;
            if (T <= -2.5) v[0]++;
            else if (T <= -1.5) v[1]++;
            else if (T <= -0.5) v[2]++;
            else if (T <= 0.5) v[3]++;
            else if (T <= 1.5) v[4]++;
            else if (T <= 2.5) v[5]++;
            else v[6]++;
        }

        for (int i = 0; i < 7; i++) {
            V += pow(v[i] - N * pi[i], 2.0) / (N * pi[i]);
        }
        P = igamc(3.0, V / 2.0);

        return P;
    }
}
