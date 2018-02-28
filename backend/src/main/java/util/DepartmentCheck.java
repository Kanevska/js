package util;

import bean.DepartmentFormBean;
import database.dao.DepartmentDao;
import database.dao.daoImpl.DepartmentDaoImpl;
import entity.Department;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import service.DepartmentService;
import service.serviceImpl.DepartmentServiceImpl;

@Component()
public class DepartmentCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        String name = ((DepartmentFormBean) o).getDepartmentName();
        Department department = departmentService.getDepartmentByName(name);
        if (department!=null && ((DepartmentFormBean) o).getId() != null) {
            return department.getId().equals(((DepartmentFormBean) o).getId());
        }
        return department == null;

    }
}