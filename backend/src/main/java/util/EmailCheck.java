package util;


import bean.EmployeeFormBean;
import database.dao.daoImpl.EmployeeDaoImpl;
import entity.Employee;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import service.EmployeeService;

public class EmailCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeService employeeService;
    @Override
    public boolean isSatisfied(Object o, Object o1) {

       Employee employee = employeeService.getEmployeeByEmail(((EmployeeFormBean) o).getEmail());
        if (employee!=null && ((EmployeeFormBean) o).getId() != null) {
            return employee.getId().equals(((EmployeeFormBean) o).getId());
        }
        return employee == null;

    }
}
