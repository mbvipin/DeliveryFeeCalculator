package practice.deliveryfee;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TierTableTest {

    private  static final BigDecimal TEN= new BigDecimal("10");
    private  static final BigDecimal TWENTY= new BigDecimal("20");
    private static final BigDecimal FIVE= new BigDecimal("5");

    @Test
    void testNullTiers()
    {

       InvalidTierException tierException= assertThrows( InvalidTierException.class, () -> new TierTable(null));

       assertEquals(InvalidReason.INVALID_EMPTY_TABLE.name(),tierException.getMessage());

    }

    @Test
    void testEmptyTiers()
    {
        List<WeightTier> tiers= new ArrayList<>();

        InvalidTierException tierException= assertThrows( InvalidTierException.class, () -> new TierTable(tiers));

        assertEquals(InvalidReason.INVALID_EMPTY_TABLE.name(),tierException.getMessage());

    }

    @Test
    void testTierListWithNullValues()
    {
        List<WeightTier> tiers= new ArrayList<>();
        tiers.add(null);

        InvalidTierException tierException= assertThrows( InvalidTierException.class, () -> new TierTable(tiers));

        assertEquals(InvalidReason.INVALID_EMPTY_TABLE.name(),tierException.getMessage());

    }

    @Test
    void testTierTableAndEnforceImmutable()
    {

        List<WeightTier> tiers= new ArrayList<>();
        tiers.add(new WeightTier(TEN,TWENTY,FIVE,null));

        TierTable tierTable= new TierTable(tiers);

        assertEquals(1,tierTable.getTiers().size());

        tiers.add(new WeightTier(TEN.add(TEN),TWENTY.add(TWENTY),FIVE.add(FIVE),null));

        assertEquals(1,tierTable.getTiers().size());



    }
}
