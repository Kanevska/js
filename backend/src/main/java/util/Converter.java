package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Department;
import entity.Employee;


import java.io.IOException;
import java.util.List;


public class Converter {

    private <T> String entityListToJson(List<T> entities, String arrayName) {
        StringBuilder jsonInString = new StringBuilder();
        jsonInString.append(String.format("{\"%s\":[", arrayName));
        String divider = "";
        for (T entity : entities) {
            jsonInString.append(divider).append(entityToJson(entity));
            divider = ",";
        }
        jsonInString.append("]}");
        return jsonInString.toString();
    }

    private <T> String entityToJson(T entity) {
        StringBuilder jsonInString = new StringBuilder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString.append(mapper.writeValueAsString(entity));
        } catch (IOException ex) {
            //TODO LOGGER
        }
        return jsonInString.toString();
    }

    public String departmentListToJSON(List<Department> department) {
        return entityListToJson(department, "department_array");
    }

    public String employeeListToJSON(List<Employee> employeeList) {
        return entityListToJson(employeeList, "employee_array");
    }

    public String employeeToJSON(Employee employee) {
        return entityToJson(employee);
    }
    public String departmentToJSON(Department department) {
        return entityToJson(department);
    }


}
