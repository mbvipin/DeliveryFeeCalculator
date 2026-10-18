package practice.deliveryfee;

/**
 * Why a customer's weight was rejected.
 *
 * LEARNING: an enum is a closed set of named constants. Callers can switch on it and the
 * compiler catches typos, which a plain String reason like "too light" would not.
 * Specific reasons beat a generic "invalid input" because the UI can show a helpful message.
 */
public enum RejectionReason {

    /** Weight is missing (null), zero or negative. */
    INVALID_INPUT,

    /** Weight is positive but below the minimum we deliver. */
    TOO_LIGHT,

    /** Weight is above the maximum we deliver. */
    EXCEEDS_MAXIMUM,

    /** Weight has more decimal places than we accept. */
    INVALID_PRECISION,

    /** Weight is in range but matches no tier. Only possible if the price table is misconfigured. */
    INVALID_WEIGHT
}
