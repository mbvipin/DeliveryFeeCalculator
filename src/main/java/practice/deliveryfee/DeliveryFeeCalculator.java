package practice.deliveryfee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * Prices a parcel by its weight in kg.
 *
 * LEARNING: the constructor is private and callers use a named factory method
 * (withStandardPricing). A name like "standard pricing" says more than
 * "new DeliveryFeeCalculator(list)", and later we can add other factories
 * (e.g. withPricing(TierTable)) without changing how callers read.
 */
public class DeliveryFeeCalculator {

    // ---------------------------------------------------------------- business limits

    // LEARNING: BigDecimal built from a String is exact. new BigDecimal(0.1) would
    // carry the binary error of the double 0.1, which is exactly what we avoid for money.
    private static final BigDecimal MIN_WEIGHT = new BigDecimal("10");
    private static final BigDecimal MAX_WEIGHT = new BigDecimal("500");

    // Scale (digits after the decimal point) we accept. Measuring devices are not
    // precise beyond grams, and rejecting extra digits stops ambiguous inputs.
    private static final int MAX_DECIMAL_PLACES = 3;

    // Money always has exactly 2 decimals, so callers get "5.00", not "5".
    private static final int MONEY_SCALE = 2;

    // ---------------------------------------------------------------- state

    private final List<WeightTier> weightTiers;

    // ---------------------------------------------------------------- construction

    private DeliveryFeeCalculator(List<WeightTier> weightTiers) {
        this.weightTiers = weightTiers;
    }

    public static DeliveryFeeCalculator withStandardPricing() {
        // Tiers are lower-inclusive, upper-exclusive: 20 kg belongs to the 20-50 tier,
        // not 10-20. A null high end means "no upper limit".
        // LEARNING: List.of(...) is immutable, so nobody can change the price table later.
        return new DeliveryFeeCalculator(
                List.of(
                        new WeightTier(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("5"), new BigDecimal("0")),
                        new WeightTier(new BigDecimal("20"), new BigDecimal("50"), new BigDecimal("12.50"), new BigDecimal("0")),
                        new WeightTier(new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("25.00"), new BigDecimal("0")),
                        new WeightTier(new BigDecimal("100"), null, new BigDecimal("40.00"), new BigDecimal("0.30"))
                )
        );
    }

    // ---------------------------------------------------------------- pricing

    /**
     * @throws RejectedException (checked) when the weight can't be priced. It is checked
     *         on purpose: bad customer input is an expected outcome, so the compiler
     *         makes every caller decide how to handle it.
     */
    public BigDecimal calculate(BigDecimal weight) throws RejectedException {
        validate(weight);

        WeightTier tier = findTier(weight);

        return feeFor(tier, weight);
    }

    /**
     * Checks run cheapest and most fundamental first. The order also decides which
     * reason the caller sees when several apply.
     */
    private void validate(BigDecimal weight) throws RejectedException {
        // LEARNING: the null check must come before any call on weight,
        // or we would hit a NullPointerException instead of our own exception.
        if (weight == null || weight.signum() <= 0) {
            throw new RejectedException(RejectionReason.INVALID_INPUT);
        }

        // LEARNING: compare BigDecimals with compareTo, never equals. equals also compares
        // scale, so new BigDecimal("10").equals(new BigDecimal("10.0")) is false.
        if (weight.compareTo(MIN_WEIGHT) < 0) {
            throw new RejectedException(RejectionReason.TOO_LIGHT);
        }

        if (weight.compareTo(MAX_WEIGHT) > 0) {
            throw new RejectedException(RejectionReason.EXCEEDS_MAXIMUM);
        }

        // LEARNING: stripTrailingZeros first, so "20.000" (scale 3) and "20" (scale 0)
        // are treated the same. Only real extra digits like 19.9999 are rejected.
        if (weight.stripTrailingZeros().scale() > MAX_DECIMAL_PLACES) {
            throw new RejectedException(RejectionReason.INVALID_PRECISION);
        }
    }

    private WeightTier findTier(BigDecimal weight) throws RejectedException {
        // The tier asks itself whether it contains the weight (see WeightTier.contains),
        // so the boundary rule lives in one place.
        Optional<WeightTier> match = weightTiers.stream()
                .filter(tier -> tier.contains(weight))
                .findFirst();

        // With a well-formed table this can't happen, since validate() keeps the weight
        // inside the covered range. It is a safety net if someone configures a table with a gap.
        return match.orElseThrow(() -> new RejectedException(RejectionReason.INVALID_WEIGHT));
    }

    private BigDecimal feeFor(WeightTier tier, BigDecimal weight) {
        BigDecimal deliveryFees = tier.getFees();

        if (tier.getCess().signum() > 0) {
            // Cess is billed per whole kg (or part thereof) over the tier's lower threshold,
            // so any fractional overage rounds UP to the next full kg before applying
            // the cess rate -- e.g. 0.2kg over bills as 1 full kg, not 0.2kg.
            // LEARNING: CEILING (not HALF_UP) because a started kilo is billed in full.
            BigDecimal overWeight = weight.subtract(tier.getLowEnd());
            BigDecimal billableOverWeight = overWeight.setScale(0, RoundingMode.CEILING);
            deliveryFees = deliveryFees.add(billableOverWeight.multiply(tier.getCess()));
        }

        // CEILING rounds in the business's favour; with the current rates the value is
        // already exact, so this mostly fixes the scale.
        return deliveryFees.setScale(MONEY_SCALE, RoundingMode.CEILING);
    }
}
