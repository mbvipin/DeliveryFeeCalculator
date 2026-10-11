package practice.deliveryfee;


import java.util.List;

public final class TierTable {

    private final List<WeightTier> tiers;

    public TierTable(List<WeightTier> tiers)
    {
        if(tiers == null || tiers.isEmpty()|| tiers.contains(null))
        {
            throw new InvalidTierException(InvalidReason.INVALID_EMPTY_TABLE);
        }

        this.tiers=List.copyOf(tiers);
    }

    public List<WeightTier> getTiers()
    {
        return tiers;
    }


}
