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

    public RejectedException(RejectionReason reason) {
        // The enum name doubles as the message, so stack traces stay readable.
        super(reason.name());
        this.reason = reason;
    }

    public RejectionReason getReason() {
        return reason;
    }
}
