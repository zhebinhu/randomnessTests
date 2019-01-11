package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;
import static java.lang.Math.max;

/**
 * Create by huzhebin on 2019/1/7
 */
public class testForTheLongestRunOfOnesInABlock {
    static final double pi[] = {0.0882, 0.2092, 0.2483, 0.1933, 0.1208, 0.0675, 0.0727};

    public static double run(List<Boolean> bits) {
        if (bits.size() < 10000) {
            System.out.println("testForTheLongestRunOfOnesInABlock:args wrong");
            return -1;
        }
        int m = 10000;
        int n = bits.size();
        int N = n / m;
        int n_disc = n % m;
        double[] v = new double[7];
        double V = 0;
        double P;

        int lr1, mlr1;
        Iterator<Boolean> it = bits.iterator();
        for (int i = 0; i < N; i++) {
            lr1 = 0;
            mlr1 = 0;
            for (int j = 0; j < m; j++) {
                if (it.next()) {
                    lr1++;
                    mlr1 = max(mlr1, lr1);
                } else {
                    lr1 = 0;
                }
            }
            if (mlr1 <= 10) v[0]++;
            if (mlr1 >= 16) v[6]++;
            if (10 < mlr1 && mlr1 < 16) v[mlr1 - 10]++;
        }

        for (int i = 0; i < 7; i++) {
            V += (v[i] - N * pi[i]) * (v[i] - N * pi[i]) / (N * pi[i]);
        }
        P = igamc(3, V / 2.0);
        return P;
    }
}
