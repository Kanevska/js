package entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class Employee extends Entity implements java.io.Serializable {

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("salary")
    private Float salary;

    @JsonProperty("departmentId")
    private Integer departmentId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Employee() {
    }

    public Employee(Integer id, String fullName, String email, Date birthday, String phoneNumber, Float salary, Integer departmentId) {
        this.setId(id);
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.departmentId = departmentId;
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

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

}
