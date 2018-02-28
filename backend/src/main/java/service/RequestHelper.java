package service;

import web.command.*;

import java.util.HashMap;

public class RequestHelper {
    private static RequestHelper instance = null;
    private HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("/employeeList", new EmployeeListCommand());
        commands.put("/editDepartment", new EditDepartmentCommand());
        commands.put("/deleteDepartment", new DeleteDepartmentCommand());
        commands.put("/editEmployee", new EditEmployeeCommand());
        commands.put("/deleteEmployee", new DeleteEmployeeCommand());
        commands.put("/addEmployee", new AddUpdateEmployeeCommand());
        commands.put("/getEmployeeForm", new GetEmployeeFormCommand());
        commands.put("/addDepartment", new AddUpdateDepartmentCommand());
        commands.put("/getDepartmentForm", new GetDepartmentFormCommand());

    }

    public Command getCommand(String uri) {
        Command command = commands.get(uri);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }


    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
