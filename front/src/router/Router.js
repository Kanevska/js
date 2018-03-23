'use strict';

import {DepartmentList} from '../components/departmentList/DepartmentList';
import {EmployeeList} from '../components/employeeList/EmployeeList';
import {DepartmentForm} from '../components/departmentForm/DepartmentForm';
import {EmployeeForm} from '../components/employeeForm/EmployeeForm';
import {ErrorPage} from '../components/ErrorPage/errorPage';
import {Services} from '../services/Services';

export class Router {

    constructor() {
    }

    route(object) {
        const hash = location.hash;
        let end = hash.indexOf('/', 2);
        if (end < 0) {
            end = hash.length;
        }
        const url = hash.substring(1, end);
        routes[url](object);
    }
}

const routes = {
    '/employeeList': (object) => {
        new EmployeeList().render(object);
    },
    '/departmentList': (object) => {
        new DepartmentList().render(object);
    },
    '/addDepartment': () => {
        new DepartmentForm().render();
    },
    '/updateDepartment': (object) => {
        new DepartmentForm().render(object);
    },
    '/updateEmployee': (object) => {
        new EmployeeForm().render(object);
    },
    '/addEmployee': (object) => {
        new EmployeeForm().render(object);
    },
    default:() => {
        new ErrorPage().render();
    }
};
window.onpopstate =() => {
    new Services().controller();
};
