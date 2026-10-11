package practice.deliveryfee;

/**
 * Thrown when the price table itself is configured wrongly.
 *
 * LEARNING: this one is unchecked (RuntimeException) while RejectedException is checked.
 * A bad weight from a customer is expected and must be handled. A bad price table is a
 * programming/configuration bug that should fail loudly at start-up, not be caught
 * and handled at every call site.
 */
public class InvalidTierException extends RuntimeException{

    public InvalidTierException(InvalidReason reason )
    {
        super(reason.name());
    }
}
