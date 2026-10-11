package practice.deliveryfee;

/** Why a customer's weight was rejected. Specific reasons beat a generic "invalid input". */
public enum RejectionReason {

    INVALID_INPUT,

    TOO_LIGHT,

    EXCEEDS_MAXIMUM,

    INVALID_PRECISION,

    INVALID_WEIGHT;
}
