package metadata;

public final class RegExpConst {

    public static final String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String PHONE_REGEXP = "^[+]{0,1}[0-9]{10,12}$";
    public static final String NAME_REGEXP = "^[A-ZА-Я]{1}[a-zа-я]+[\\s-][A-ZА-Я]{1}[a-zа-я]+[\\s-][A-ZА-Я]{1}[a-zа-я]+";
    public static final String SALARY_REGEXP = "^([0-9]*[.])?[0-9]+";

    public static final String DEPARTMENT_NAME_REGEXP = "^[A-ZА-Я]{1}[a-zа-я\\d\\s]+";
    public static final String ADDRESS_REGEXP = "^[А-яA-z\\s\\.;]+\\d+";
}
