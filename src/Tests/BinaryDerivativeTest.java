package Tests;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.apache.commons.math3.special.Erf.erfc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class BinaryDerivativeTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() < 7) {
            System.out.println("BinaryDerivativeTest:args wrong");
            return -1;
        }
        int k = 7;
        int n = bits.size();
        int S = 0;
        double V;
        double P;
        List<Boolean> _bits = new ArrayList<>(bits);

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                _bits.set(j, _bits.get(j) ^ _bits.get(j + 1));
            }
        }

        for (int i = 0; i < n - k; i++)
            if (_bits.get(i)) S++;
            else S--;
        V = abs(S) / sqrt(n - k);
        P = erfc(V / sqrt(2));
        return P;
    }
}
