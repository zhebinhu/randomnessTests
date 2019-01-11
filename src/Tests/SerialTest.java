package Tests;

import java.util.Iterator;
import java.util.List;

import static Tests.Utils.igamc;
import static java.lang.Math.pow;

/**
 * Create by huzhebin on 2019/1/7
 */
public class SerialTest {
    static double serial_test_P2;

    public static double run(List<Boolean> bits) {
        if (bits.size() < 5) {
            System.out.println("SerialTest:args wrong");
            return -1;
        }
        int m = 5;
        int[] patterns1 = new int[1 << m];
        int[] patterns2 = new int[1 << (m - 1)];
        int[] patterns3 = new int[1 << (m - 2)];
        int n = bits.size();
        double Phi1 = 0, Phi2 = 0, Phi3 = 0;
        double DPhi2, D2Phi2;
        double P1, P2;
        int mask1 = (1 << m) - 1;
        int mask2 = (1 << (m - 1)) - 1;
        int mask3 = (1 << (m - 2)) - 1;
        int tmp = 0;

        for (int i = 0; i < m - 1; i++) {
            bits.add(bits.get(i));
        }

        Iterator<Boolean> it = bits.iterator();

        for (int i = 0; i < m - 1; i++) {
            tmp <<= 1;
            if (it.next()) tmp++;
        }

        for (int i = 0; i < n; i++) {
            tmp <<= 1;
            if (it.next()) tmp++;
            patterns1[tmp & mask1]++;
            patterns2[tmp & mask2]++;
            patterns3[tmp & mask3]++;
        }

        for (int i = 0; i <= mask1; i++)
            Phi1 += pow(patterns1[i], 2.0);
        Phi1 *= (mask1 + 1);
        Phi1 /= n;
        Phi1 -= n;
        for (int i = 0; i <= mask2; i++)
            Phi2 += pow(patterns2[i], 2.0);
        Phi2 *= (mask2 + 1);
        Phi2 /= n;
        Phi2 -= n;
        for (int i = 0; i <= mask3; i++)
            Phi3 += pow(patterns3[i], 2.0);
        Phi3 *= (mask3 + 1);
        Phi3 /= n;
        Phi3 -= n;

        DPhi2 = Phi1 - Phi2;
        D2Phi2 = Phi1 - 2 * Phi2 + Phi3;

        P1 = igamc((1 << m) / 4.0, DPhi2 / 2.0);
        P2 = igamc((1 << m) / 8.0, D2Phi2 / 2.0);

        for (int i = 0; i < m - 1; i++) {
            bits.remove(bits.size() - 1);
        }
        serial_test_P2 = P2;

        return P1;
    }

    public static double run2(List<Boolean> bits) {
        return serial_test_P2;
    }
}
