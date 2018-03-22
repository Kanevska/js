import {Components} from 'components/Component';
import {Router} from 'src/router/Router';
import {Services} from 'src/services/Services';
import {EmployeeForm} from 'components/employeeForm/EmployeeForm';


export class EmployeeList extends Components {


    constructor(depId) {
        super('employeeList');
        this.depId = depId;
    }


    rendering(){
        const router = new Router();
        const service = new Services();
        service.getInformation(`/departments/editDepartment/${this.depId}`,this,router);
    }




    render(object) {

        super.render();
        let root = '#root';
        $(root).empty().append($('<h3/>').text(`EMPLOYEE LIST OF ${object.departmentName} DEPARTMENT`));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'add').attr('depId',`${this.depId}`).text('+').on('click', this.add));
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
                    .append($('<div/>').attr('class','deleteButton').attr('depId',`${object['employees'][i].departmentId}`).attr('empId',`${object['employees'][i].id}`).on('click', this.delete).text('x'))
                    .append($('<div/>').attr('class','addButton').attr('depId',`${this.depId}`).attr('empId',`${object['employees'][i].id}`).on('click', this.update).text('Edit'))
                ));
        }

    }

    add(){
        const router = new Router();
        const service = new Services();
        let depId = $(this).attr('depId');
        let employeeForm = new EmployeeForm(depId);
        service.getInformation('/departments/departmentList',employeeForm,router);
        router.route(employeeForm);
    }
    update(){
        const router = new Router();
        const service = new Services();
        let empId = $(this).attr('empId');
        let depId = $(this).attr('depId');
        let employeeForm = new EmployeeForm(depId);
        service.getInformation(`/employee/editEmployee/${empId}`,employeeForm,router);
    }
    delete(){
        const service = new Services();
        let empId = $(this).attr('empId');
        let depId = $(this).attr('depId');
        service.deleteInformation(`/employee/deleteEmployee/${empId}`, new EmployeeList(depId));
    }
}
