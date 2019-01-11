package Tests;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.apache.commons.math3.special.Erf.erfc;

/**
 * Create by huzhebin on 2019/1/7
 */
public class RunsTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("RunsTest:args wrong");
            return -1;
        }
        int n = bits.size();
        double Pi = 0;
        int V_obs = 1;
        double P;

        for (int i = 0; i < n - 1; i++) {
            if (bits.get(i) != bits.get(i + 1)) V_obs++;
            if (bits.get(i)) Pi++;
        }
        if (bits.get(n - 1)) Pi++;
        Pi /= n;
        P = erfc(abs(V_obs - 2.0 * n * Pi * (1.0 - Pi)) / (2.0 * sqrt(2.0 * n) * Pi * (1.0 - Pi)));
        return P;
    }
}
