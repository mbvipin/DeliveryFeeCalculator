package practice.deliveryfee;

import java.math.BigDecimal;
import java.util.Objects;

final class WeightTier {
    private final BigDecimal lowEnd;
    private final BigDecimal highEnd;
    private  final BigDecimal fees;
    private  final BigDecimal cess;

    public WeightTier(BigDecimal lowEnd, BigDecimal highEnd, BigDecimal fees, BigDecimal cess) throws InvalidTierException {




        if (lowEnd == null || lowEnd.signum() <= 0) {
            throw new InvalidTierException(InvalidReason.INVALID_LOWEND);
        }

        if (highEnd != null && highEnd.compareTo(lowEnd) <= 0) {
            throw new InvalidTierException(InvalidReason.INVALID_HIGHEND);
        }

        if (fees == null || fees.signum() < 0) {
            throw new InvalidTierException(InvalidReason.INVALID_FEES);
        }

        if (cess != null && cess.signum() < 0) {
            throw new InvalidTierException(InvalidReason.INVALID_CESS);
        }

        this.lowEnd = lowEnd;
        this.highEnd = highEnd;
        this.fees = fees;
        this.cess = cess == null ? BigDecimal.ZERO : cess;
    }

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
