package Tests;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.util.List;

import static Tests.Utils.pow2DoubleArr;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.apache.commons.math3.special.Erf.erfc;

/**
 * Create by huzhebin on 2019/1/8
 */
public class DiscreteFourierTransformTest {
    public static double run(List<Boolean> bits) {
        if (bits.size() == 0) {
            System.out.println("DiscreteFourierTransformTest:args wrong");
            return -1;
        }
        int n = bits.size();
        double[] r = new double[n];
        double T = sqrt(2.995732274 * n);
        double N_0 = 0.95 * n / 2;
        int N_1 = 0;

        for (int i = 0; i < n; i++) {
            if (bits.get(i)) r[i] = 1.0;
            else r[i] = -1.0;
        }
        r = pow2DoubleArr(r);
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] result = fft.transform(r, TransformType.FORWARD);

        for (int i = 0; i < n / 2 - 1; i++) {
            if (result[i].abs() < T) {
                N_1++;
            }
        }

        if (abs(r[0]) < T) N_1++;

        double V = (N_1 - N_0) / sqrt(0.95 * 0.05 * n / 2);
        double P = erfc(abs(V) / sqrt(2));

        return P;
    }

}