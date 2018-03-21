import {Services} from 'services/Services';
import {EmployeeList} from 'components/components/employeeList/EmployeeList';
import {Router} from 'components/router/Router';
import {DepartmentList} from 'components/components/departmentList/DepartmentList';
import {DepartmentForm} from 'components/components/departmentForm/DepartmentForm';
import {EmployeeForm} from 'components/components/employeeForm/EmployeeForm';

export class Util {

    constructor(component, url) {
        this.component = component;
        this.url = url;
    }


    render() {
        if (this.url == null) {
            this.component.rendering();
        }
        else {
            const service = new Services();
            service.getInformation(this.url, this.component);
        }
    }

    build() {
        const router = new Router();
        buildingAccordingUrl[router.currentPosition()]();
    }
}

const buildingAccordingUrl = {
    'departmentForm': () => {
        let url = null;
        let id = location.hash.substr(location.hash.lastIndexOf('/') + 1);
        if (isNaN(id)) {
            const router = new Router();
            let departmentForm = new DepartmentForm();
            router.route(departmentForm);
        }
        else {
            url = `/departments/editDepartment/${id}`;

            new Util(new DepartmentForm(), url).render();
        }

    },
    'employeeForm': () => {
        const hash = location.hash;
        let id = hash.substr(hash.lastIndexOf('/') + 1);
        let url = null;
        if (isNaN(id)) {
            url = '/employee/editEmployee';
            new EmployeeForm(hash.substring(hash.indexOf('/') + 1), hash.indexOf('/'), 2, url).render();
        }
        else {
            url = `/employee/editEmployee/${id}`;
            new EmployeeForm(hash.substring(hash.indexOf('/') + 1), hash.indexOf('/'), 2, url).render();
        }


    },
    'departmentList': () => {
        new Util(new DepartmentList()).render();
    },
    'employeeList': () => {
        const url = location.hash;
        const depId = url.substring((url.indexOf('/') + 1), url.lastIndexOf('/'));
        const employeeList = new EmployeeList(depId);
        employeeList.rendering();

    }
};

