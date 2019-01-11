import Tests.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Boolean> bits = new ArrayList<>(1500000);
        for (int i = 0; i < 1000000; i++) {
            if (Math.random() < 0.5) {
                bits.add(true);

            } else {
                bits.add(false);

            }
        }
        System.out.println(MonobitFrequencyTest.run(bits));
        System.out.println(FrequencyTestWithinABlock.run(bits));
        System.out.println(PokerTest.run(bits));
        System.out.println(SerialTest.run(bits));
        System.out.println(SerialTest.run2(bits));
        System.out.println(RunsTest.run(bits)); // 分布相关，概率无关
        System.out.println(RunsDistributionTest.run(bits));
        System.out.println(testForTheLongestRunOfOnesInABlock.run(bits));
        System.out.println(BinaryDerivativeTest.run(bits));// 分布相关，概率无关
        System.out.println(AutocorrelationTest.run(bits));
        System.out.println(BinaryMatrixRankTest.run(bits));// 分布强相关，概率弱相关
        System.out.println(CumulativeTest.run(bits));
        System.out.println(ApproximateEntropyTest.run(bits));
        System.out.println(LinearComplexityTest.run(bits));// 分布相关，概率无关
        System.out.println(MaurersUniversalTest.run(bits));
        System.out.println(DiscreteFourierTransformTest.run(bits));
    }
}
