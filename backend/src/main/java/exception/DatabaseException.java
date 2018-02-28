package exception;

public class DatabaseException extends ApplicationException {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
    public DatabaseException(String message){super(message, new Exception() );}
}

