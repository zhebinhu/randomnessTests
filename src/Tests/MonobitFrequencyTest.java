package Tests;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.apache.commons.math3.special.Erf.erfc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class MonobitFrequencyTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("MonobitFrequencyTest:arg wrong");
            return -1;
        }
        int n = bits.size();
        int S = 0;
        double V;
        double P;
        for (Boolean bit : bits) {
            if (bit) {
                S++;
            } else {
                S--;
            }
        }
        V = abs(S) / sqrt(n);
        P = erfc(V / sqrt(2));
        return P;
    }
}

