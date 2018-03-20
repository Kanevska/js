'use strict';

import {Util} from "../util/Util";
import {DepartmentList} from "../components/departmentList/DepartmentList";
import {DepartmentForm} from "../components/departmentForm/DepartmentForm";
import {EmployeeForm} from "../components/employeeForm/EmployeeForm";
import {EmployeeList} from "../components/employeeList/EmployeeList";

const routes = {
    'departmentForm': (component, object) => {
        (object == null) ? location.hash = 'departmentForm' : location.hash = `departmentForm/${object.id}`;
    },
    'employeeForm': (component, object) => {
        let id;
        try {
            id = object.id
        }
        catch (err) {
            id = null;
        }
        (id == null) ? location.hash = `departmentList/${component.depId}/employeeForm` : location.hash = `departmentList/${component.depId}/employeeForm/${object.id}`;


    },
    'departmentList': () => {
        location.hash = 'departmentList';
    },
    'employeeList': (component) => {
        location.hash = `departmentList/${component.depId}/employeeList`;
    },
};


const buildingAccordingUrl = {
    'departmentForm': () => {
        let url = null;
        let id = location.hash.substr(location.hash.lastIndexOf('/') + 1);
        if (isNaN(id)) {
            url = '/departments/editDepartment';
        }
        else {
            url = `/departments/editDepartment/${id}`;
        }
        new Util(new DepartmentForm(), url).render();
    },
    'employeeForm': () => {
        const hash = location.hash;
        let id = hash.substr(hash.lastIndexOf('/') + 1);
        let url = null;
        if (isNaN(id)) {
            url = '/employee/editEmployee';
        }
        else {
            url = `/employee/editEmployee/${id}`;
        }
        new Util(new EmployeeForm(hash.substr(hash.indexOf('/') + 1), hash.indexOf('/'), 2), url);

    },
    'departmentList': () => {
        new Util(new DepartmentList()).render();
    },
    'employeeList': () => {
        new Util(new EmployeeList(location.hash.substr(location.hash.lastIndexOf('/') + 1))).render();
    }
};


export class Router {

    constructor() {
    }

    route(component) {
        routes[component.name](component);
        component.render();

    }

    route(component, object) {
        routes[component.name](component, object);
        component.render(object);


    }

    currentPosition() {
        const hash = location.hash;
        let slashPosition = hash.lastIndexOf('/') + 1;
        if (slashPosition > 0) {
            const lastPart = hash.substr(slashPosition);
            if (isNaN(lastPart)) {
                return (lastPart);
            }
            else {
                let partTwo = hash.substr((hash.indexOf('#') + 1), (slashPosition - 2));
                if (partTwo.indexOf('/') > 0) {
                    return (partTwo.substr(partTwo.lastIndexOf('/') + 1));
                } else {
                    return (partTwo);
                }
            }
        } else {
            return (hash.substr(hash.lastIndexOf('#') + 1));
        }
    }
}
