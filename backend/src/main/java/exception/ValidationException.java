package exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException extends Exception {

    public ValidationException(Map<String, List<String>> errotMap) {
        this.errotMap = errotMap;
    }

    public ValidationException(String message, Map<String, List<String>> errotMap) {
        super(message);
        this.errotMap = errotMap;
    }

    public ValidationException(String message, Throwable cause, Map<String, List<String>> errotMap) {
        super(message, cause);
        this.errotMap = errotMap;
    }

    private Map<String, List<String>> errotMap = new HashMap<>();

    public Map<String, List<String>> getErrotMap() {
        return errotMap;
    }

    public void setErrorMap(Map<String, List<String>> errotMap) {
        this.errotMap = errotMap;
    }
}
