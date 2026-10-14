package practice.deliveryfee;

import java.math.BigDecimal;

/** Short, exact BigDecimal factories for tests. Import with: import static practice.deliveryfee.Num.bd; */
final class Num {

    private Num() {
    }

    /** Exact from text: bd("12.50") keeps scale 2, bd("20") keeps scale 0. */
    static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }

    /** Whole numbers: bd(10). There is deliberately no double overload; new BigDecimal(0.1) is inexact. */
    static BigDecimal bd(int value) {
        return BigDecimal.valueOf(value);
    }
}
