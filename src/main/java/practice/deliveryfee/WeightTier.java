package practice.deliveryfee;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * One row of the price table: weights in [lowEnd, highEnd) cost "fees", plus "cess"
 * per started kg above lowEnd.
 *
 * LEARNING: the class is final with only final fields, so an instance never changes
 * after construction. Immutable objects are safe to share and need no locking.
 * It is package-private because only the calculator and tier table use it.
 */
final class WeightTier {
    private final BigDecimal lowEnd;
    private final BigDecimal highEnd;
    private  final BigDecimal fees;
    private  final BigDecimal cess;

    /**
     * LEARNING: validate in the constructor ("fail fast"). A tier that exists is
     * always valid, so no other code needs to re-check it.
     * highEnd == null is allowed and means "open-ended".
     */
    public WeightTier(BigDecimal lowEnd, BigDecimal highEnd, BigDecimal fees, BigDecimal cess) throws InvalidTierException {

        // The null check comes first so the signum() call can't throw a NullPointerException.
        if (lowEnd == null || lowEnd.signum() <= 0) {
            throw new InvalidTierException(InvalidReason.INVALID_LOWEND);
        }

        // <= 0 (not < 0): a tier whose high end equals its low end would be empty.
        if (highEnd != null && highEnd.compareTo(lowEnd) <= 0) {
            throw new InvalidTierException(InvalidReason.INVALID_HIGHEND);
        }

        // Fees are required, but 0 is allowed (a free tier is a valid business choice).
        // signum() is a cheap way to ask "is it negative / zero / positive?".
        if (fees == null || fees.signum() < 0) {
            throw new InvalidTierException(InvalidReason.INVALID_FEES);
        }

        if (cess != null && cess.signum() < 0) {
            throw new InvalidTierException(InvalidReason.INVALID_CESS);
        }

        this.lowEnd = lowEnd;
        this.highEnd = highEnd;
        this.fees = fees;
        // LEARNING: normalise optional input once, here. Now getCess() is never null
        // and the calculator doesn't need a null check.
        this.cess = cess == null ? BigDecimal.ZERO : cess;
    }

    /** Lower bound inclusive, upper bound exclusive, so adjacent tiers never overlap. */
    boolean contains(BigDecimal weight) {
        return weight.compareTo(lowEnd) >= 0 && (highEnd == null || weight.compareTo(highEnd) < 0);
    }

    public BigDecimal getLowEnd() {
        return lowEnd;
    }

    public BigDecimal getHighEnd() {
        return highEnd;
    }


    public BigDecimal getFees() {
        return fees;
    }

    public BigDecimal getCess() {
        return cess;
    }

}
