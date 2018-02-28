package metadata;

public interface LoggerConst {

    String CONTROLLER_START = "Controller %s start";
    String LOG4J_INIT = "Log4j successfully init";
    String SERVICE_INIT = "%s service successfully init";
    String CONTEXT_INIT = "Context successfully init";
    String CONTEXT_DESTROY = "Context successfully destroyed";
    String CONNECTION_ERROR = "No connection available";
    String ERROR_FORWARD_TO = "Error forward to %s";
    String SUCCESSFULLY_CREATE = "%s with parameters %s successfully create";
    String SUCCESSFULLY_UPDATE = "%s with parameters %s successfully update";

    String IS_INVALID = "%s is invalid";

    String ERR_CAN_NOT_OBTAIN_FILE_WITH_DATABASE_PROPERTIES = "There are no access to database properties";

    String INVALID_DATE_ENTER = "Invalid enter date";
    String SUCCESSFULLY_ADD = "%s was created";
}
