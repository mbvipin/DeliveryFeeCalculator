package practice.deliveryfee;

/**
 * Thrown when a weight can't be priced.
 *
 * LEARNING: extend Exception (a checked exception), not Throwable. Throwable is the
 * root of Exception and Error, and normal catch (Exception e) blocks would miss it.
 * The reason is kept as an enum so callers and tests can react to it without
 * parsing the message text.
 */
public class RejectedException extends Exception {

    private final RejectionReason reason;

    public RejectedException(RejectionReason rejectionReason) {

        super(rejectionReason.name());
        this.reason=rejectionReason;
    }

    public RejectionReason getReason()
    {
        return reason;
    }
}
