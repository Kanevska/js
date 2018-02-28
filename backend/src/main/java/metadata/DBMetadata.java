package metadata;

public class DBMetadata {


    public abstract class TablesNames {

        public static final String DEPARTMENT = "department";
        public static final String EMPLOYEES = "employee";

    }


    public abstract class DepartmentsMetadata {

        public static final String DEPARTMENT_ID = TablesNames.DEPARTMENT + "." + "department_id";
        public static final String DEPARTMENT_NAME = TablesNames.DEPARTMENT + "." + "department_name";
        public static final String DESCRIPTION = TablesNames.DEPARTMENT + "." + "description";
        public static final String ADDRESS = TablesNames.DEPARTMENT + "." + "address";
        public static final String DEPARTMENT_ID_WITHOUT = "department_id";
        public static final String DEPARTMENT_NAME_WITHOUT = "department_name";
        public static final String DESCRIPTION_WITHOUT = "description";

    }

    public abstract class EmployeesMetadata {

        public static final String EMPLOYEE_ID = TablesNames.EMPLOYEES + "." + "employee_id";
        public static final String EMPLOYEE_ID_WITHOUT = "employee_id";
        public static final String FULL_NAME_WITHOUT = "full_name";
        public static final String FULL_NAME = TablesNames.EMPLOYEES + "." + "full_name";
        public static final String EMAIL_WITHOUT = "email";
        public static final String EMAIL = TablesNames.EMPLOYEES + "." + "email";
        public static final String BIRTHDAY_WITHOUT = "birthday";
        public static final String BIRTHDAY = TablesNames.EMPLOYEES + "." + "birthday";
        public static final String PHONE_NUMBER_WITHOUT = "phone_number";
        public static final String PHONE_NUMBER = TablesNames.EMPLOYEES + "." + "phone_number";
        public static final String SALARY_WITHOUT = "salary";
        public static final String SALARY = TablesNames.EMPLOYEES + "." + "salary";
        public static final String DEPARTMENT_ID_WITHOUT = "department_id";
        public static final String DEPARTMENT_ID = TablesNames.EMPLOYEES + "." + "department_id";

    }
}
