package practice.deliveryfee;

/**
 * Why a price table or tier was rejected.
 *
 * LEARNING: this is the configuration-side twin of RejectionReason. One describes bad
 * customer input, the other describes a bad price table. Keeping them separate stops
 * unrelated reasons from mixing in one enum.
 */
public enum InvalidReason {

    // --- Single tier (checked in WeightTier) ---

    /** Low end is missing, zero or negative. */
    INVALID_LOWEND,

    /** High end is not above the low end. */
    INVALID_HIGHEND,

    /** Fees are missing or negative. */
    INVALID_FEES,

    /** Cess is negative. A missing cess is allowed and treated as zero. */
    INVALID_CESS,

    // --- Whole table (checked in TierTable) ---

    /** The table is null, empty or contains a null tier. */
    INVALID_EMPTY_TABLE,

    /** Some weights between two neighbouring tiers match no tier. */
    INVALID_GAP,

    /** Some weights match two neighbouring tiers. */
    INVALID_OVERLAP,

    /** A tier with no upper limit is followed by another tier. Only the last may be open-ended. */
    INVALID_HIGHEND_UNBOUNDED_NOTLAST
}
