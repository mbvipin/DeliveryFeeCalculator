package practice.deliveryfee;

public class InvalidTierException extends RuntimeException{

    public InvalidTierException(InvalidReason reason )
    {
        super(reason.name());
    }
}
