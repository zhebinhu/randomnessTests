package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class FrequencyTestWithinABlock {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("FrequencyTestWithinABlock:args wrong");
            return -1;
        }
        int m = Math.min(bits.size(), 100);
        int n = bits.size();
        int N = n / m;
        double Pi;
        double V = 0;
        double P;

        Iterator<Boolean> it = bits.iterator();

        for (int i = 0; i < N; i++) {
            Pi = 0;
            for (int j = 0; j < m; j++) {
                if (it.next()) {
                    Pi++;
                }
            }
            Pi /= m;
            V += (Pi - 0.5) * (Pi - 0.5);
        }
        V *= 4.0 * m;
        P = igamc(N / 2.0, V / 2.0);
        return P;
    }
}
