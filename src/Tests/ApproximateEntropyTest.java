package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;
import static java.lang.Math.log;

/**
 * Create by huzhebin on 2019/1/8
 */
public class ApproximateEntropyTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("ApproximateEntropyTest:error");
            return -1;
        }
        int m = 5;
        int n = bits.size();
        int[] pattern;
        int mask;
        int tmp = 0;
        double Cjm;
        double phim = 0, phim1 = 0;
        double ApEn;
        double V;
        double P;
        Iterator<Boolean> it;

        // round1
        for (int i = 0; i < m - 1; i++) {
            bits.add(bits.get(i));
        }

        pattern = new int[1 << m];
        mask = (1 << m) - 1;

        it = bits.iterator();
        for (int i = 0; i < m - 1; i++) {
            tmp <<= 1;
            if (it.next()) tmp++;
        }
        for (int i = 0; i < n; i++) {
            tmp <<= 1;
            if (it.next()) tmp++;
            pattern[tmp & mask]++;
        }

        for (int i = 0; i < (1 << m); i++) {
            Cjm = (double) (pattern[i]) / (double) (n);
            phim += Cjm * log(Cjm);
        }

        for (int i = 0; i < m - 1; i++) {
            bits.remove(bits.size() - 1);
        }

        // round2
        m++;
        for (int i = 0; i < m - 1; i++) {
            bits.add(bits.get(i));
        }

        pattern = new int[1 << m];
        mask = (1 << m) - 1;

        it = bits.iterator();
        for (int i = 0; i < m - 1; i++) {
            tmp <<= 1;
            if (it.next()) tmp++;
        }
        for (int i = 0; i < n; i++) {
            tmp <<= 1;
            if (it.next()) tmp++;
            pattern[tmp & mask]++;
        }

        for (int i = 0; i < (1 << m); i++) {
            Cjm = (double) (pattern[i]) / (double) (n);
            phim1 += Cjm * log(Cjm);
        }

        for (int i = 0; i < m - 1; i++) {
            bits.remove(bits.size() - 1);
        }

        //
        m--;
        ApEn = phim - phim1;
        V = 2.0 * n * (log(2) - ApEn);
        P = igamc((1 << m) / 2.0, V / 2.0);

        return P;
    }
}
