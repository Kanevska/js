package service.serviceImpl;

import bean.DepartmentFormBean;
import database.dao.DepartmentDao;
import entity.Department;
import exception.ValidationException;
import metadata.AppConst;
import org.apache.log4j.Logger;
import service.DepartmentService;
import util.ValidationUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static metadata.LoggerConst.SUCCESSFULLY_CREATE;

public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(final ServletContext context) {

        this.departmentDao = (DepartmentDao) context.getAttribute(AppConst.DEPARTMENT_DAO);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();

    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
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
    public Department getDepartmentByName(String name) {
        return departmentDao.getDepartmentByName(name);
    }

    public DepartmentFormBean setDepartmentFormBean(HttpServletRequest request) {

        DepartmentFormBean departmentFormBean = new DepartmentFormBean();
        try {
            departmentFormBean.setId(Integer.parseInt(request.getParameter("departmentId")));
        } catch (Exception e) {
            LOGGER.debug("Adding department");
        }

        if (!request.getParameter("departmentName").equals("")) {
            departmentFormBean.setDepartmentName(request.getParameter("departmentName"));
        }
        if (!request.getParameter("description").equals("")) {
            departmentFormBean.setDescription(request.getParameter("description"));
        }
        if (!request.getParameter("address").equals("")) {
            departmentFormBean.setAddress(request.getParameter("address"));
        }

        return departmentFormBean;
    }

    public Department getDepartment(DepartmentFormBean departmentFormBean) {
        Department department = new Department();
        department.setId(departmentFormBean.getId());
        department.setDepartmentName(departmentFormBean.getDepartmentName());
        department.setDescription(departmentFormBean.getDescription());
        department.setAddress(departmentFormBean.getAddress());
        return department;
    }
}
