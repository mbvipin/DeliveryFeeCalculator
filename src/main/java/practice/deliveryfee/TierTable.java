package practice.deliveryfee;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The full price table: an ordered list of weight tiers.
 *
 * Why a class of its own? Rules about the whole set (no gaps, no overlaps) can't live
 * in WeightTier, which only knows about itself. This is where they will go.
 */
public final class TierTable {

    private final List<WeightTier> tiers;

    public TierTable(List<WeightTier> tiers)
    {
        // contains(null) is checked here so a bad element gives our own exception
        // instead of a raw NullPointerException from List.copyOf below.
        if(tiers == null || tiers.isEmpty()|| tiers.contains(null))
        {
            throw new InvalidTierException(InvalidReason.INVALID_EMPTY_TABLE);
        }

        Comparator<WeightTier> comparator= Comparator.comparing(WeightTier::getLowEnd);

        // LEARNING: defensive copy. If we kept the caller's list, they could add or remove
        // tiers later and silently change our table. List.copyOf makes an immutable snapshot.
        List<WeightTier> sortedTiers=tiers.stream().sorted(comparator).toList();

        for(int i=0; i< sortedTiers.size()-1; i++)
        {
            WeightTier current= sortedTiers.get(i);
            WeightTier next= sortedTiers.get(i+1);

            if( current.getHighEnd() == null)
            {
                throw new InvalidTierException(InvalidReason.INVALID_HIGHEND_UNBOUNDED_NOTLAST);
            }

            if( current.getHighEnd().compareTo(next.getLowEnd()) <0)
            {
                throw new InvalidTierException(InvalidReason.INVALID_GAP);
            }

            if( current.getHighEnd().compareTo(next.getLowEnd()) > 0)
            {
                 throw new InvalidTierException(InvalidReason.INVALID_OVERLAP);
            }




        }

        this.tiers= sortedTiers;

    }

    public List<WeightTier> getTiers()
    {
        // Safe to return directly: the list is immutable, so callers can't modify it.
        return tiers;
    }


}
