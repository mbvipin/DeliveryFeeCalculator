package practice.deliveryfee;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * The full price table: an ordered list of weight tiers.
 *
 * Why a class of its own? Rules about the whole set (no gaps, no overlaps, only the last
 * tier unbounded) can't live in WeightTier, which only knows about itself. They are
 * enforced here, once, when the table is constructed.
 */
public final class TierTable {

    private final List<WeightTier> tiers;

    /**
     * @throws InvalidTierException if the list is null, empty or contains a null, if a tier
     *         other than the last has no upper limit, or if neighbouring tiers leave a gap
     *         or overlap.
     */
    public TierTable(List<WeightTier> tiers) {
        // Null elements are checked here so a bad element gives our own exception
        // instead of a raw NullPointerException while sorting. Not tiers.contains(null):
        // immutable lists such as List.of(...) throw NullPointerException on it.
        if (tiers == null || tiers.isEmpty() || tiers.stream().anyMatch(Objects::isNull)) {
            throw new InvalidTierException(InvalidReason.INVALID_EMPTY_TABLE);
        }

        // Callers may pass tiers in any order, so sort by lower bound before checking
        // neighbours. Gaps and overlaps are only visible between adjacent tiers.
        Comparator<WeightTier> byLowEnd = Comparator.comparing(WeightTier::getLowEnd);

        // LEARNING: defensive copy. If we kept the caller's list, they could add or remove
        // tiers later and silently change our table. Stream.toList() returns an immutable
        // snapshot, and sorting makes a new list, so the caller's list is never touched.
        List<WeightTier> sortedTiers = tiers.stream().sorted(byLowEnd).toList();

        // Compare each tier with the one after it. The last tier has no "next", so the
        // loop stops one short; it is allowed to be unbounded.
        for (int i = 0; i < sortedTiers.size() - 1; i++) {
            WeightTier current = sortedTiers.get(i);
            WeightTier next = sortedTiers.get(i + 1);

            // An open-ended tier swallows every weight above it, so nothing may follow it.
            if (current.getHighEnd() == null) {
                throw new InvalidTierException(InvalidReason.INVALID_HIGHEND_UNBOUNDED_NOTLAST);
            }

            // Tiers are lower-inclusive and upper-exclusive, so a tier must end exactly
            // where the next one starts.
            int comparison = current.getHighEnd().compareTo(next.getLowEnd());

            if (comparison < 0) {
                // Ends before the next starts: weights in between match no tier.
                throw new InvalidTierException(InvalidReason.INVALID_GAP);
            }

            if (comparison > 0) {
                // Ends after the next starts: weights in between match two tiers.
                throw new InvalidTierException(InvalidReason.INVALID_OVERLAP);
            }
        }

        this.tiers = sortedTiers;
    }

    /** The tiers in ascending order of lower bound. */
    public List<WeightTier> getTiers() {
        // Safe to return directly: the list is immutable, so callers can't modify it.
        return tiers;
    }

    public BigDecimal getMinWeight()
    {
        return tiers.get(0).getLowEnd();
    }

    public  BigDecimal getMaxWeight()
    {
        return tiers.get(tiers.size()-1).getHighEnd();
    }
}
