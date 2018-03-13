'use strict';

import {DepartmentList} from '../components/departmentList/DepartmentList';
import {DepartmentForm} from '../components/departmentForm/DepartmentForm';
import {EmployeeList} from '../components/employeeList/EmployeeList';
import {EmployeeForm} from '../components/employeeForm/EmployeeForm';
import {Services} from "services/Services";


const routes = {
    'employeeList/employeeForm[/\w+]?': (services) => {
        let employeeForm = new EmployeeForm();
        const path = window.location.hash;
        const res = path.match('[1-9]+');

        if (res != null) {
            services.getInformation(`/employee/editEmployee/${res}`, employeeForm);
        }
        else {
            services.getInformation('/departments/departmentList', employeeForm);
        }

    },
    'employeeList[/\w+]': (services) => {
        let employeeList = new EmployeeList();
        const path = window.location.hash;
        const res = path.match('[1-9]+');
        services.getInformation(`/departments/editDepartment/${res}`, employeeList);
    },
    'departmentForm[/\w+]?': (services) => {
        let departmentForm = new DepartmentForm();
        const path = window.location.hash;
        const res = path.match('[1-9]+');

        if (res != null) {
            services.getInformation(`/departments/editDepartment/${res}`, departmentForm);
        }
        else {
            departmentForm.render(null);
        }

    },
    'departments': (services) => {
        let departmentList = new DepartmentList();
        services.getInformation('/departments/departmentList', departmentList);
    }


};



export class Router {


    constructor() {
    }

    route(component){
        component.render();
    }

    route(component, object){
        component.render(object);
    }

}

export function routing() {
    const result = Object.keys(routes).find(() => {
        const pathname = window.location.hash;
        if (!pathname) {
            return true;
        }
        for (let str in routes) {
            const regexp = new RegExp(str);
            const matches = pathname.match(regexp);
            if (matches) {
                routes[str]();
                return true;
            }
        }
        return false;
    });

    if (!result) {
        alert('error');
    }
}