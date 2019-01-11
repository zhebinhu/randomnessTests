package Tests;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.apache.commons.math3.special.Erf.erfc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class AutocorrelationTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() < 16) {
            System.out.println("AutocorrelationTest:args wrong");
            return -1;
        }
        int d = 16;
        int n = bits.size();
        int Ad = 0;
        double V;
        double P;

        for (int i = 0; i < n - d; i++) {
            if (bits.get(i) ^ bits.get(i + d)) Ad++;
        }

        V = 2.0 * (Ad - ((n - d) / 2.0)) / sqrt(n - d);
        P = erfc(abs(V) / sqrt(2));
        return P;
    }
}
