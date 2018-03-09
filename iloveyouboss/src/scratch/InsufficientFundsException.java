package scratch;

public class InsufficientFundsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InsufficientFundsException ( ){
        super();
    }

    public InsufficientFundsException (Exception e){
        super(e);
    }

    public InsufficientFundsException(String message){
        super(message);
    }
}
