package Tests;

import java.util.List;

import static Tests.Utils.normal_CDF;
import static java.lang.Math.*;

/**
 * Create by huzhebin on 2019/1/8
 */
public class CumulativeTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("CumulativeTest:args wrong");
            return -1;
        }
        int n = bits.size();
        int S = 0;
        int Z = 0;
        double P = 1.0;
        for (int i = 0; i < n; i++) {
            if (bits.get(i)) S++;
            else S--;
            Z = max(Z, abs(S));
        }

        for (int i = ((-n / Z) + 1) / 4; i <= ((n / Z) - 1) / 4; i++) {
            P -= normal_CDF((4 * i + 1) * Z / sqrt(n)) - normal_CDF((4 * i - 1) * Z / sqrt(n));
        }
        for (int i = ((-n / Z) - 3) / 4; i <= ((n / Z) - 1) / 4; i++) {
            P += normal_CDF((4 * i + 3) * Z / sqrt(n)) - normal_CDF((4 * i + 1) * Z / sqrt(n));
        }
        return P;
    }
}