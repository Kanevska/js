package bean;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;
import util.DepartmentCheck;

import static metadata.RegExpConst.ADDRESS_REGEXP;
import static metadata.RegExpConst.DEPARTMENT_NAME_REGEXP;

public class DepartmentFormBean {

    private Integer id;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @Length(max = 24, message = "Length of the description must be less than 25")
    @MatchPattern(pattern = DEPARTMENT_NAME_REGEXP, message = "Invalid department name.<br> It must start from Uppercase letter and contain lowercase one. ")
    @CheckWith(value = DepartmentCheck.class, message = "This name already exists!")
    private String departmentName;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @Length(max = 29, message = "Length of the description must be less than 30")
    private String description;

    @NotNull(errorCode = "field.notNull", message = "Can't be null")
    @Length(max = 29, message = "Length of the address must be less than 30")
    @MatchPattern(pattern = ADDRESS_REGEXP, message = "Invalid address.")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
