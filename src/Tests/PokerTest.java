package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class PokerTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() < 8) {
            System.out.println("PokerTest:args wrong");
        }
        int m = 8;
        int[] patterns = new int[1 << m];
        int n = bits.size();
        int N = n / m;
        double V = 0;
        double P;
        int tmp;

        Iterator<Boolean> it = bits.iterator();

        for (int i = 0; i < N; i++) {
            tmp = 0;
            for (int j = 0; j < m; j++) {
                tmp <<= 1;
                if (it.next()) tmp++;
            }
            patterns[tmp]++;
        }

        for (int i = 0; i < (1 << m); i++) {
            V += patterns[i] * patterns[i];
        }

        V *= (1 << m);
        V /= N;
        V -= N;
        P = igamc(((1 << m) - 1) >> 1, V / 2);
        return P;
    }
}
