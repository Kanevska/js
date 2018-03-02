'use strict';

import {AjaxGetInformation} from "../services/Services";
import {DepartmentForm} from "../components/departmentForm/DepartmentForm";

const routes = {

    '/': () => {
        let departmentForm = new DepartmentForm();
        AjaxGetInformation("/departments/departmentList", departmentForm);
        // $('#root').append('<p>root</p>');
    },
    '/addDepartment': () => {

    }

};


export function routing() {
    console.log("from routing");
    const result = Object.keys(routes).find(str => {
        const pathname = window.location.pathname;
        if (!pathname) {
            return false;
        }
        routes[str]();
        return true;
    });

    if (!result) {
        alert("error");
    }
}
