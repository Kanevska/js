'use strict';

import {getInformation } from "../services/Services";
import {DepartmentList} from "../components/departmentList/DepartmentList";
import {DepartmentForm} from "../components/departmentForm/DepartmentForm";
import {EmployeeList} from "../components/EmployeeList";
import {EmployeeForm} from "../components/EmployeeForm";

const routes = {
    '/employeeList/employeeForm[/\w+]?': () => {
        let employeeForm = new EmployeeForm();
        const path = window.location.pathname;
        const res = path.match('[1-9]+');

        if (res != null) {
            getInformation(`/employee/editEmployee/${res}`, employeeForm);
        }
        else {
            console.log("front employeeForm");
            getInformation(`/departments/departmentList`, employeeForm);
        }

    },
    '/employeeList[/\w+]': () => {
        let employeeList = new EmployeeList();
        const path = window.location.pathname;
        const res = path.match('[1-9]+');
        getInformation(`/departments/editDepartment/${res}`, employeeList);
    },
    '/departmentForm[/\w+]?': () => {
        let departmentForm = new DepartmentForm();
        const path = window.location.pathname;
        const res = path.match('[1-9]+');

        if (res != null) {
            getInformation(`/departments/editDepartment/${res}`, departmentForm);
        }
        else {
            departmentForm.render(null);
        }

    },
    '/': () => {
        let departmentList = new DepartmentList();
        getInformation("/departments/departmentList", departmentList);
    }


};

export function routing() {
    console.log(window.location.pathname);
    const result = Object.keys(routes).find(() => {
        const pathname = window.location.pathname;
        if (!pathname) {
            return true;
        }
        for (let str in routes) {
            const regexp = new RegExp(str);
            console.log(regexp);
            const matches = pathname.match(regexp);
            if (matches) {
                routes[str]();
                return true;
            }
        }
        return false;
    });

    if (!result) {
        alert("error");
    }
}
window.onpopstate = function() {
    routing();
};