package practice.learning;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * RoundingMode cheat sheet (all examples round to scale 0, i.e. to an integer).
 *
 * Quick rules:
 *  - UP / DOWN       : about the MAGNITUDE (away from / towards zero). Ignore the sign.
 *  - CEILING / FLOOR : about the DIRECTION on the number line (towards +infinity / -infinity).
 *  - HALF_*          : only differ from each other when the discarded part is EXACTLY 0.5.
 *
 *  Input    UP   DOWN  CEILING  FLOOR  HALF_UP  HALF_DOWN  HALF_EVEN
 *   5.5      6     5      6       5       6         5          6
 *   2.5      3     2      3       2       3         2          2
 *   1.6      2     1      2       1       2         2          2
 *   1.1      2     1      2       1       1         1          1
 *  -1.1     -2    -1     -1      -2      -1        -1         -1
 *  -1.6     -2    -1     -1      -2      -2        -2         -2
 *  -2.5     -3    -2     -2      -3      -3        -2         -2
 *  -5.5     -6    -5     -5      -6      -6        -5         -6
 */
public class BigDecimalLearn {

    public static void main(String[] args) {

        // UP: round AWAY from zero. Any non-zero discarded digit bumps the magnitude up.
        // 100.001 -> 101 (positive, so "away from zero" = bigger)
        // -0.0001 -> -1  (negative, so "away from zero" = more negative)
        // -1.001  -> -2
        show("UP", "100.001", RoundingMode.UP);   // 101
        show("UP", "-0.0001", RoundingMode.UP);   // -1
        show("UP", "-1.001", RoundingMode.UP);    // -2

        // DOWN: round TOWARDS zero (i.e. plain truncation). Never increases magnitude.
        // 1.99 -> 1, -1.99 -> -1
        show("DOWN", "1.99", RoundingMode.DOWN);    // 1
        show("DOWN", "-1.99", RoundingMode.DOWN);   // -1
        show("DOWN", "-0.9", RoundingMode.DOWN);    // 0

        // CEILING: round towards POSITIVE infinity (always >= the original value).
        // Positive numbers behave like UP, negative numbers behave like DOWN.
        show("CEILING", "100.001", RoundingMode.CEILING);   // 101  (like UP)
        show("CEILING", "-0.0001", RoundingMode.CEILING);   // 0    (like DOWN; note: prints 0, BigDecimal has no negative zero)
        show("CEILING", "-1.0001", RoundingMode.CEILING);   // -1   (like DOWN)

        // FLOOR: round towards NEGATIVE infinity (always <= the original value).
        // Positive numbers behave like DOWN, negative numbers behave like UP.
        show("FLOOR", "1.99", RoundingMode.FLOOR);     // 1   (like DOWN)
        show("FLOOR", "-1.01", RoundingMode.FLOOR);    // -2  (like UP)
        show("FLOOR", "-0.0001", RoundingMode.FLOOR);  // -1  (like UP)

        // HALF_UP: round to the nearest neighbour; on an exact tie round AWAY from zero.
        // This is the "school" rounding most people expect.
        show("HALF_UP", "2.5", RoundingMode.HALF_UP);    // 3
        show("HALF_UP", "2.4", RoundingMode.HALF_UP);    // 2  (not a tie, nearest is 2)
        show("HALF_UP", "-2.5", RoundingMode.HALF_UP);   // -3 (tie goes away from zero)

        // HALF_DOWN: round to the nearest neighbour; on an exact tie round TOWARDS zero.
        // Only a tie differs from HALF_UP: 2.51 is not a tie, so it still goes up.
        show("HALF_DOWN", "2.5", RoundingMode.HALF_DOWN);    // 2
        show("HALF_DOWN", "2.51", RoundingMode.HALF_DOWN);   // 3
        show("HALF_DOWN", "-2.5", RoundingMode.HALF_DOWN);   // -2

        // HALF_EVEN (banker's rounding): nearest neighbour; on a tie round to the EVEN neighbour.
        // Reduces statistical bias when summing many rounded values.
        show("HALF_EVEN", "2.5", RoundingMode.HALF_EVEN);   // 2 (2 is even)
        show("HALF_EVEN", "3.5", RoundingMode.HALF_EVEN);   // 4 (4 is even)

        // UNNECESSARY: asserts no rounding is needed; throws ArithmeticException otherwise.
        show("UNNECESSARY", "2.0", RoundingMode.UNNECESSARY);   // 2
        try {
            new BigDecimal("2.5").setScale(0, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            System.out.println("UNNECESSARY      2.5 -> " + e.getMessage());   // Rounding necessary
        }
    }

    private static void show(String label, String value, RoundingMode mode) {
        BigDecimal result = new BigDecimal(value).setScale(0, mode);
        System.out.printf("%-16s %-9s -> %s%n", label, value, result);
    }
}
