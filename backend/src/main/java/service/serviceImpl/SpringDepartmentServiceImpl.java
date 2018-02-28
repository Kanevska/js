package service.serviceImpl;

import bean.DepartmentFormBean;
import database.dao.DepartmentDao;
import entity.Department;
import exception.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.DepartmentService;
import util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static metadata.LoggerConst.SUCCESSFULLY_CREATE;

@Service("departmentService")
@Transactional
public class SpringDepartmentServiceImpl implements DepartmentService {

    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;

    private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void deleteDepartment(Integer departmentId) {
        departmentDao.deleteDepartment(departmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer departmentId) {
        System.out.println("get department id");
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void insertUpdateDepartment(DepartmentFormBean departmentFormBean) throws ValidationException {
        ValidationUtil validationUtil = new ValidationUtil();
        validationUtil.validate(departmentFormBean);
        departmentDao.insertUpdateDepartment(getDepartment(departmentFormBean));
        LOGGER.debug(String.format(SUCCESSFULLY_CREATE, "department", ""));
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentByName(String name){

        return departmentDao.getDepartmentByName(name);
    }

    @Override
    public DepartmentFormBean setDepartmentFormBean(HttpServletRequest request) {

        String departmentName = request.getParameter("departmentName");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        DepartmentFormBean departmentFormBean = new DepartmentFormBean();
        try {
            Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
            departmentFormBean.setId( departmentId);
        } catch (Exception e) {
            LOGGER.debug("Adding department");
        }

        if (!departmentName.equals("")) {
            departmentFormBean.setDepartmentName(departmentName);
        }
        if (!description.equals("")) {
            departmentFormBean.setDescription(description);
        }
        if (!address.equals("")) {
            departmentFormBean.setAddress(address);
        }
        return departmentFormBean;

    }

    private Department getDepartment(DepartmentFormBean departmentFormBean) {

        Department department = new Department();
        department.setDepartmentName(departmentFormBean.getDepartmentName());
        department.setDescription(departmentFormBean.getDescription());
        department.setId(departmentFormBean.getId());
        department.setAddress(departmentFormBean.getAddress());
        return department;
    }
}
