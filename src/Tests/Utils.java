package Tests;

import static java.lang.Math.*;
import static org.apache.commons.math3.special.Erf.erf;
import static org.apache.commons.math3.special.Gamma.logGamma;

/**
 * Create by huzhebin on 2019/1/7
 */
public class Utils {
    final static double MAXLOG = 7.09782712893383996732224E2;    // log(MAXNUM)
    final static double biginv = 2.22044604925031308085e-16;
    final static double big = 4.503599627370496e15;
    final static double MACHEP = 1.11022302462515654042E-16;

    public static double igam(double a, double x) {
        double ans, ax, c, r;

        if ((x <= 0) || (a <= 0))
            return 0.0;

        if ((x > 1.0) && (x > a))
            return 1.e0 - igamc(a, x);

        /* Compute  x**a * exp(-x) / gamma(a)  */
        ax = a * log(x) - x - logGamma(a);
        if (ax < -MAXLOG) {
            return 0.0;
        }
        ax = exp(ax);

        /* power series */
        r = a;
        c = 1.0;
        ans = 1.0;

        do {
            r += 1.0;
            c *= x / r;
            ans += c;
        } while (c / ans > MACHEP);

        return ans * ax / a;
    }

    public static double igamc(double a, double x) {
        double ans, ax, c, yc, r, t, y, z;
        double pk, pkm1, pkm2, qk, qkm1, qkm2;

        if ((x <= 0) || (a <= 0))
            return (1.0);

        if ((x < 1.0) || (x < a))
            return (1.e0 - igam(a, x));

        ax = a * log(x) - x - logGamma(a);

        if (ax < -MAXLOG) {
            return 0.0;
        }
        ax = exp(ax);

        /* continued fraction */
        y = 1.0 - a;
        z = x + y + 1.0;
        c = 0.0;
        pkm2 = 1.0;
        qkm2 = x;
        pkm1 = x + 1.0;
        qkm1 = z * x;
        ans = pkm1 / qkm1;

        do {
            c += 1.0;
            y += 1.0;
            z += 2.0;
            yc = y * c;
            pk = pkm1 * z - pkm2 * yc;
            qk = qkm1 * z - qkm2 * yc;
            if (qk != 0) {
                r = pk / qk;
                t = abs((ans - r) / r);
                ans = r;
            } else
                t = 1.0;
            pkm2 = pkm1;
            pkm1 = pk;
            qkm2 = qkm1;
            qkm1 = qk;
            if (abs(pk) > big) {
                pkm2 *= biginv;
                pkm1 *= biginv;
                qkm2 *= biginv;
                qkm1 *= biginv;
            }
        } while (t > MACHEP);

        return ans * ax;
    }

    public static double normal_CDF(double x) {
        return (1 + erf(x / sqrt(2))) / 2;
    }


    public static int rank(int[][] matrix, int m) {
        int[][] temp = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        rowEchelon(temp, m);
        int rank = 0;
        for (int i = 0; i < m; i++) {
            boolean notZero = false;
            for (int j = 0; j < m; j++) {
                if (temp[i][j] != 0) {
                    notZero = true;
                }
            }
            if (notZero) {
                rank++;
            }
        }
        return rank;
    }

    public static void rowEchelon(int[][] matrix, int m) {
        int pivotstartrow = 0;
        int pivotstartcol = 0;
        int pivotrow = 0;
        for (int i = 0; i < m; i++) {
            boolean found = false;
            for (int k = pivotstartrow; k < m; k++) {
                if (matrix[k][pivotstartcol] == 1) {
                    found = true;
                    pivotrow = k;
                    break;
                }
            }
            if (found) {
                if (pivotrow != pivotstartrow) {
                    for (int k = 0; k < m; k++) {
                        matrix[pivotrow][k] ^= matrix[pivotstartrow][k];
                        matrix[pivotstartrow][k] ^= matrix[pivotrow][k];
                        matrix[pivotrow][k] ^= matrix[pivotstartrow][k];
                    }
                }
                for (int j = pivotstartrow + 1; j < m; j++) {
                    if (matrix[j][pivotstartcol] == 1) {
                        for (int k = 0; k < m; k++) {
                            matrix[j][k] = matrix[pivotstartrow][k] ^ matrix[j][k];
                        }
                    }
                }

                pivotstartcol += 1;
                pivotstartrow += 1;
            } else {
                pivotstartcol += 1;
            }
        }
    }

    public static void printm(int[][] matrix, int m) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int linearComplexity(boolean[] a, int M) {
        int N_ = 0, L = 0, m = -1, d = 0;
        int[] B_, C, P, T;
        B_ = new int[M];
        C = new int[M];
        P = new int[M];
        T = new int[M];
        for (int i = 0; i < M; i++) {
            B_[i] = 0;
            C[i] = 0;
            T[i] = 0;
            P[i] = 0;
        }
        C[0] = 1;
        B_[0] = 1;
        while (N_ < M) {
            d = b2i(a[N_]);
            for (int i = 1; i <= L; i++)
                d += C[i] * b2i(a[N_ - i]);
            d = d % 2;
            if (d == 1) {
                for (int i = 0; i < M; i++) {
                    T[i] = C[i];
                    P[i] = 0;
                }
                for (int j = 0; j < M; j++)
                    if (B_[j] == 1)
                        P[j + N_ - m] = 1;
                for (int i = 0; i < M; i++)
                    C[i] = (C[i] + P[i]) % 2;
                if (L <= N_ / 2) {
                    L = N_ + 1 - L;
                    m = N_;
                    for (int i = 0; i < M; i++)
                        B_[i] = T[i];
                }
            }
            N_++;
        }
        return L;
    }

    private static int b2i(boolean b) {
        return b ? 1 : 0;
    }

    public static double[] pow2DoubleArr(double[] data) {

        // 创建新数组
        double[] newData = null;

        int dataLength = data.length;

        int sumNum = 2;
        while (sumNum < dataLength) {
            sumNum = sumNum * 2;
        }
        int addLength = sumNum - dataLength;

        if (addLength != 0) {
            newData = new double[sumNum];
            System.arraycopy(data, 0, newData, 0, dataLength);
            for (int i = dataLength; i < sumNum; i++) {
                newData[i] = 0d;
            }
        } else {
            newData = data;
        }

        return newData;

    }
}
