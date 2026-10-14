package practice.deliveryfee;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static practice.deliveryfee.Num.bd;

public class TierTableTest {

    /** A tier with fees of 5 and no cess; the tests here only care about the bounds. */
    private static WeightTier tier(int lowEnd, Integer highEnd) {
        return new WeightTier(bd(lowEnd), highEnd == null ? null : bd(highEnd), bd(5), null);
    }

    /** A mutable list, so tests can also check the table is unaffected by later changes. */
    private static List<WeightTier> tiersOf(WeightTier... tiers) {
        return new ArrayList<>(Arrays.asList(tiers));
    }

    private static void assertInvalid(InvalidReason expected, List<WeightTier> tiers) {
        InvalidTierException ex = assertThrows(InvalidTierException.class, () -> new TierTable(tiers));
        assertEquals(expected.name(), ex.getMessage());
    }

    @Test
    void testNullTiers() {
        assertInvalid(InvalidReason.INVALID_EMPTY_TABLE, null);
    }

    @Test
    void testEmptyTiers() {
        assertInvalid(InvalidReason.INVALID_EMPTY_TABLE, tiersOf());
    }

    @Test
    void testTierListWithNullValues() {
        assertInvalid(InvalidReason.INVALID_EMPTY_TABLE, tiersOf((WeightTier) null));
    }

    @Test
    void testTierTableAndEnforceImmutable() {
        List<WeightTier> tiers = tiersOf(tier(10, 20));

        TierTable tierTable = new TierTable(tiers);

        assertEquals(1, tierTable.getTiers().size());

        tiers.add(tier(20, 40));

        assertEquals(1, tierTable.getTiers().size());
    }

    @Test
    void testTierTableOverlap() {
        assertInvalid(InvalidReason.INVALID_OVERLAP, tiersOf(tier(10, 20), tier(15, 25)));
    }

    @Test
    void testTierTableGAP() {
        assertInvalid(InvalidReason.INVALID_GAP, tiersOf(tier(10, 20), tier(25, 30)));
    }

    @Test
    void testTierTableWithFourTiers() {
        List<WeightTier> tiers = tiersOf(tier(10, 20), tier(20, 50), tier(50, 100), tier(100, null));

        TierTable tierTable = new TierTable(tiers);

        assertEquals(4, tierTable.getTiers().size());
    }
}
