package Tests;

import java.util.List;

import static Tests.Utils.igamc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class RunsDistributionTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() < 100) {
            System.out.println("RunsDistributionTest:args wrong");
            return -1;
        }
        int n = bits.size();
        int k = 0;
        double[] e = new double[50];
        double[] b = new double[50];
        double[] g = new double[50];
        double V = 0;
        double P;
        boolean cur = bits.get(0);
        int cnt = 0;

        do {
            k++;
            e[k] = (double) (n - k + 3) / (double) (1 << (k + 2));
        } while (e[k] >= 5.0);
        k--;

        bits.add(!(bits.get(n - 1)));
        for (int i = 0; i <= n; i++) {
            if (bits.get(i) == cur) {
                cnt++;
            } else {
                if (cnt <= k) {
                    if (cur)
                        b[cnt]++;
                    else
                        g[cnt]++;
                }
                cur = bits.get(i);
                cnt = 1;
            }
        }
        bits.remove(bits.size() - 1);

        for (int i = 1; i <= k; i++) {
            V += (b[i] - e[i]) * (b[i] - e[i]) / e[i];
            V += (g[i] - e[i]) * (g[i] - e[i]) / e[i];
        }
        P = igamc(k - 1, V / 2.0);
        return P;
    }
}
