'use strict';

import {DepartmentList} from '../components/departmentList/DepartmentList';

export class Router {

    constructor() {
    }

    route(object,...args) {
        const hash = location.hash;
        let end = hash.indexOf('/',2);
        if(end<0){
            end = hash.length;
        }
        const url = hash.substring(1,end);
        // Component.render(object);
        routes[url](object,...args);
    }

    /*
     * CurrentPosition() {
     * const hash = location.hash;
     * let slashPosition = hash.lastIndexOf('/') + 1;
     * if (slashPosition > 0) {
     * const lastPart = hash.substr(slashPosition);
     * if (isNaN(lastPart)) {
     * return (lastPart);
     * }
     * else {
     * let partTwo = hash.substr((hash.indexOf('#') + 1), (slashPosition - 2));
     * if (partTwo.indexOf('/') > 0) {
     * return (partTwo.substr(partTwo.lastIndexOf('/') + 1));
     * } else {
     * return (partTwo);
     * }
     * }
     * } else {
     * return (hash.substr(hash.lastIndexOf('#') + 1));
     * }
     * }
     * }
     * const routes = {
     * 'departmentForm': (component, object) => {
     * (object==null) ? location.hash = 'departmentForm' : location.hash = `departmentForm/${object.id}`;
     * },
     * 'employeeForm': (component, object) => {
     * let id;
     * try {
     * id = object.id;
     * }
     * catch (err) {
     * id = null;
     * }
     * (id == null) ? location.hash = `departmentList/${component.depId}/employeeForm` : location.hash = `departmentList/${component.depId}/employeeForm/${object.id}`;
     *
     *
     * },
     * 'departmentList': () => {
     * location.hash = 'departmentList';
     * },
     * 'employeeList': (component) => {
     * location.hash = `departmentList/${component.depId}/employeeList`;
     * },
     */
}
const routes = {
    '/employeeList': (object) => {
        alert(object);
    },
    '/departmentList': (object) => {
        new DepartmentList().render(object);
    }
};
