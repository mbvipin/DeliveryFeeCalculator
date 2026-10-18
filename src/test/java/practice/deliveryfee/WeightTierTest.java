package practice.deliveryfee;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static practice.deliveryfee.Num.bd;

/** Rules for a single tier: each invalid field has its own reason. */
public class WeightTierTest {

    /** Builds a tier that must fail, and checks it fails for the expected reason. */
    private static void assertInvalid(InvalidReason expected, BigDecimal low, BigDecimal high,
                                      BigDecimal fees, BigDecimal cess) {
        InvalidTierException ex = assertThrows(InvalidTierException.class,
                () -> new WeightTier(low, high, fees, cess));
        assertEquals(expected.name(), ex.getMessage());
    }

    // ---------------------------------------------------------------- low end

    @Test
    void rejectNullLowEnd() {
        assertInvalid(InvalidReason.INVALID_LOWEND, null, bd(20), bd(5), BigDecimal.ZERO);
    }

    @Test
    void rejectNonPositiveLowEnd() {
        assertInvalid(InvalidReason.INVALID_LOWEND, BigDecimal.ZERO, bd(20), bd(5), BigDecimal.ZERO);
    }

    // ---------------------------------------------------------------- high end

    @Test
    void rejectHighEndNotAboveLowEnd() {
        assertInvalid(InvalidReason.INVALID_HIGHEND, bd(10), bd(10), bd(5), BigDecimal.ZERO);
    }

    @Test
    void nullHighEndMeansOpenEnded() {
        WeightTier tier = new WeightTier(bd(10), null, bd(5), BigDecimal.ZERO);

        assertTrue(tier.contains(bd(100000)));
    }

    // ---------------------------------------------------------------- fees

    @Test
    void rejectNullFees() {
        assertInvalid(InvalidReason.INVALID_FEES, bd(10), bd(20), null, BigDecimal.ZERO);
    }

    @Test
    void rejectNegativeFees() {
        assertInvalid(InvalidReason.INVALID_FEES, bd(10), bd(20), bd(-1), BigDecimal.ZERO);
    }

    // ---------------------------------------------------------------- cess

    @Test
    void rejectNegativeCess() {
        assertInvalid(InvalidReason.INVALID_CESS, bd(10), bd(20), bd(5), bd("-0.1"));
    }

    @Test
    void nullCessDefaultsToZero() {
        assertEquals(BigDecimal.ZERO, new WeightTier(bd(10), bd(20), bd(5), null).getCess());
    }
}
