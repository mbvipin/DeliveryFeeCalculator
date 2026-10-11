package practice.deliveryfee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class DeliveryFeeCalculator {

    private static final BigDecimal MIN_WEIGHT = new BigDecimal("10");
    private static final BigDecimal MAX_WEIGHT = new BigDecimal("500");
    private static final int MAX_DECIMAL_PLACES = 3;

    private final List<WeightTier> weightTiers;

    private DeliveryFeeCalculator(List<WeightTier> weightTiers) {
        this.weightTiers = weightTiers;
    }

    public static DeliveryFeeCalculator withStandardPricing() {

            return new DeliveryFeeCalculator(
                    List.of(
                            new WeightTier(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("5"), new BigDecimal("0")),
                            new WeightTier(new BigDecimal("20"), new BigDecimal("50"), new BigDecimal("12.50"), new BigDecimal("0")),
                            new WeightTier(new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("25.00"), new BigDecimal("0")),
                            new WeightTier(new BigDecimal("100"), null, new BigDecimal("40.00"), new BigDecimal("0.30"))
                    )
            );

    }

    public BigDecimal calculate(BigDecimal weight) throws RejectedException {
        if (weight == null || weight.signum() <= 0) {
            throw new RejectedException(RejectionReason.INVALID_INPUT);
        }

        if (weight.compareTo(MIN_WEIGHT) < 0) {
            throw new RejectedException(RejectionReason.TOO_LIGHT);
        }

        if (weight.compareTo(MAX_WEIGHT) > 0) {
            throw new RejectedException(RejectionReason.EXCEEDS_MAXIMUM);
        }

        if (weight.stripTrailingZeros().scale() > MAX_DECIMAL_PLACES) {
            throw new RejectedException(RejectionReason.INVALID_PRECISION);
        }

        Optional<WeightTier> config = weightTiers.stream().filter(entry -> entry.contains(weight)).findFirst();

        if (config.isEmpty()) {
            throw new RejectedException(RejectionReason.INVALID_WEIGHT);
        }

        WeightTier tier = config.get();
        BigDecimal deliveryFees = tier.getFees();

        if (tier.getCess().signum() > 0) {
            // Cess is billed per whole kg (or part thereof) over the tier's lower threshold,
            // so any fractional overage rounds UP to the next full kg before applying
            // the cess rate -- e.g. 0.2kg over bills as 1 full kg, not 0.2kg.
            BigDecimal overWeight = weight.subtract(tier.getLowEnd());
            BigDecimal billableOverWeight = overWeight.setScale(0, RoundingMode.CEILING);
            deliveryFees = deliveryFees.add(billableOverWeight.multiply(tier.getCess()));
        }

        return deliveryFees.setScale(2, RoundingMode.CEILING);
    }
}
