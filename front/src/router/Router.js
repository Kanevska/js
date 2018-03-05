'use strict';

import {AjaxGetInformation} from "../services/Services";
import {DepartmentList} from "../components/departmentList/DepartmentList";
import {DepartmentForm} from "../components/departmentForm/DepartmentForm";

const routes = {
    '/departmentForm': () => {
        let departmentForm = new DepartmentForm();
        departmentForm.render(null);
    },
    '/': () => {
        let departmentList = new DepartmentList();
        AjaxGetInformation("/departments/departmentList", departmentList);
    }


};

export function routing() {
    console.log(window.location.pathname);
    const result = Object.keys(routes).find(str => {
        const pathname = window.location.pathname;
        if (!pathname) {
            return true;
        }
        routes[pathname]();
        return true;
    });

    if (!result) {
        alert("error");
    }
}

