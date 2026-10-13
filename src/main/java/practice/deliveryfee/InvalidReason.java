package practice.deliveryfee;

/** Why a price table or tier was rejected. An enum gives a fixed, typo-proof set of reasons. */
public enum InvalidReason {

    INVALID_LOWEND,

    INVALID_HIGHEND,

    INVALID_FEES,

    INVALID_CESS,

    INVALID_EMPTY_TABLE,

    INVALID_GAP,

    INVALID_OVERLAP,

    INVALID_HIGHEND_UNBOUNDED_NOTLAST;
}
