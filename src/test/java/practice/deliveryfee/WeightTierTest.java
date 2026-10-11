package practice.deliveryfee;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeightTierTest {

    private static final BigDecimal TEN = new BigDecimal("10");
    private static final BigDecimal TWENTY = new BigDecimal("20");
    private static final BigDecimal FIVE = new BigDecimal("5");

    private void assertInvalid(InvalidReason expected, BigDecimal low, BigDecimal high, BigDecimal fees, BigDecimal cess) {
        InvalidTierException ex = assertThrows(InvalidTierException.class, () -> new WeightTier(low, high, fees, cess));
        assertEquals(expected.name(), ex.getMessage());
    }

    @Test
    void rejectNullLowEnd() {
        assertInvalid(InvalidReason.INVALID_LOWEND, null, TWENTY, FIVE, BigDecimal.ZERO);
    }

    @Test
    void rejectNonPositiveLowEnd() {
        assertInvalid(InvalidReason.INVALID_LOWEND, BigDecimal.ZERO, TWENTY, FIVE, BigDecimal.ZERO);
    }

    @Test
    void rejectHighEndNotAboveLowEnd() {
        assertInvalid(InvalidReason.INVALID_HIGHEND, TEN, TEN, FIVE, BigDecimal.ZERO);
    }

    @Test
    void rejectNullFees() {
        assertInvalid(InvalidReason.INVALID_FEES, TEN, TWENTY, null, BigDecimal.ZERO);
    }

    @Test
    void rejectNegativeFees() {
        assertInvalid(InvalidReason.INVALID_FEES, TEN, TWENTY, new BigDecimal("-1"), BigDecimal.ZERO);
    }

    @Test
    void rejectNegativeCess() {
        assertInvalid(InvalidReason.INVALID_CESS, TEN, TWENTY, FIVE, new BigDecimal("-0.1"));
    }

    @Test
    void nullCessDefaultsToZero() {
        assertEquals(BigDecimal.ZERO, new WeightTier(TEN, TWENTY, FIVE, null).getCess());
    }

    @Test
    void nullHighEndMeansOpenEnded() {
        WeightTier tier = new WeightTier(TEN, null, FIVE, BigDecimal.ZERO);
        assertEquals(true, tier.contains(new BigDecimal("100000")));
    }
}
