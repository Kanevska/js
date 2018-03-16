import {Components} from '../Component';
import {Router, routing} from '../../router/Router';
import {deleteInformation, Services} from '../../services/Services';
import {DepartmentForm} from "../departmentForm/DepartmentForm";
import {DepartmentList} from "../departmentList/DepartmentList";
import {EmployeeForm} from "../employeeForm/EmployeeForm";


export class EmployeeList extends Components {


    constructor(depId) {
        super();
        this.depId = depId;
    }

    rendering(){
        const router = new Router();
        const service = new Services();
        service.getInformation(`/departments/editDepartment/${this.depId}`,this,router)
    }




    render(object) {

        super.render();
        let root = '#root';
        $(root).empty().append($('<h3/>').text(`EMPLOYEE LIST OF ${object.departmentName} DEPARTMENT`));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'add').text('+').on('click', this.onClickFunction));
        for (let i = 0; i < object['employees'].length; i++) {
            $(root).append($('<div/>').attr('class', 'blocks')
                .append($('<table/>').attr('class', 'text')
                    .append($('<tr/>')
                        .append($('<td/>').append($('<p/>').attr('class','name').text(`${object['employees'][i].fullName}`)))
                        .append($('<td/>').append($('<p/>').attr('class','address').text(`email: ${object['employees'][i].email}`)))
                        .append($('<td/>').append($('<p/>').attr('class','address').text(`birthday: ${object['employees'][i].birthday}`)))
                        .append($('<td/>').append($('<p/>').attr('class','address').text(`salary: ${object['employees'][i].salary}`)))
                        .append($('<td/>').append($('<p/>').attr('class','address').text(`number: ${object['employees'][i].phoneNumber}`)))
                    )).append($('<div/>').attr('class','buttons')
                    .append($('<div/>').attr('class','deleteButton').attr('empId',`${object['employees'][i].id}`).on('click', this.onClickFunction).text('x'))
                    .append($('<div/>').attr('class','addButton').attr('empId',`${object['employees'][i].id}`).on('click', this.onClickFunction).text('Edit'))
                ));
        }

    }

    onClickFunction() {
        const router = new Router();
        const service = new Services();

        switch ($(this).attr('class')) {
            case 'add': {
                let employeeForm = new EmployeeForm();
                service.getInformation(`/departments/departmentList`,employeeForm,router);
                router.route(employeeForm);
                break;
            }
            case 'addButton': {
                let empId = $(this).attr('empId');
                //location.href = `#departmentForm/${depId}`;
                let employeeForm = new EmployeeForm();
                service.getInformation(`/employee/editEmployee/${empId}`,employeeForm,router);
                break;
            }

            case 'deleteButton': {
                let empId = $(this).attr('empId');
                service.deleteInformation(`/employee/deleteEmployee/${empId}`, new EmployeeList(empId));
                break;
            }
        }
    }


}

window.onEmployeeClickFunction = function (object) {
    switch ($(object).attr('class')) {
        case 'add': {
            location.hash ='employeeList/employeeForm';
            routing();
            break;
        }
        case 'addButton': {
            const empId = $(object).attr('empId');
            location.hash =`employeeList/employeeForm/${empId}`;
            routing();
            break;
        }
        case 'deleteButton': {
            let empId = $(object).attr('empId');
            deleteInformation(`/employee/deleteEmployee/${empId}`, new EmployeeList(empId));
            break;
        }
    }
};