package bean;

import net.sf.oval.constraint.*;
import net.sf.oval.guard.Guarded;
import org.apache.log4j.Logger;
import util.EmailCheck;

import java.sql.Date;

import static metadata.RegExpConst.*;


public class EmployeeFormBean {
    private static final Logger LOGGER = Logger.getLogger(EmployeeFormBean.class);
    private Integer id;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @Length(max = 54, message = "Length must be less than 55")
    @MatchPattern(pattern = NAME_REGEXP, message = "Invalid name. It must look like \"Surname FirstName MiddleName\"")
    private String fullName;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @MatchPattern(pattern = EMAIL_REGEXP, message = "Invalid email. It must look like \"some.text@gmil.com\"")
    @Length(max = 29, message = "Length must be less than 30")
    @CheckWith(value = EmailCheck.class, message = "This email already exists!")
    private String email;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @DateRange(format = "yyyy-MM-dd", max = "2002-01-01", min = "1990-01-01", message = "Invalid date.")
    private Date birthday;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @Length(max = 54, message = "Length must be less than 55")
    @MatchPattern(pattern = PHONE_REGEXP, message = "Invalid phone.Phone must look like \"+380502233445\"")
    private String phoneNumber;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @MatchPattern(pattern = SALARY_REGEXP, message = "Salary must be a number")
    @NotNegative(message = "Salary must be positive number")
    private String salary;

    @NotNull
    private Integer departmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


}

