'use strict';

 import {Components} from "../components/Component";
import {AjaxGetInformation} from "../services/Services";

const routes = {

    '/': () => {
console.log("form /");
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

