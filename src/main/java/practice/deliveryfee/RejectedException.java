package practice.deliveryfee;

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
