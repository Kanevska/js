import {Components} from 'components/Component';
import {Router} from 'src/router/Router';
import {Services} from 'src/services/Services';
import {DepartmentForm} from 'components/departmentForm/DepartmentForm';
import {EmployeeList} from 'components/employeeList/EmployeeList';

export class DepartmentList extends Components {

    constructor() {
        super('departmentList');
    }

    rendering() {
        const router = new Router();
        const service = new Services();
        service.getInformation('/departments/departmentList',this,router);
    }

    render(object) {
        let root = '#root';
        $(root).empty().append($('<h3/>').text('DEPARTMENTS'));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'add').text('+').on('click', this.add));
        for (let i = 0; i < object['department_array'].length; i++) {
            $(root).append($('<div/>').attr('class', 'blocks')
                .append($('<table/>').attr('class', 'text')
                    .append($('<tr/>')
                        .append($('<td/>').append($('<p/>').attr('class', 'name')
                            .text(object['department_array'][i].departmentName)))
                        .append($('<td/>').append($('<p/>').attr('class', 'address')
                            .text(`Address: ${ object['department_array'][i].description}`)))
                        .append($('<td/>').append($('<p/>').attr('class', 'address')
                            .text(`Description: ${object['department_array'][i].address}`)))
                    )).append($('<div/>').attr('class', 'buttons')
                    .append($('<div/>').attr('class', 'deleteButton').attr('depId', object['department_array'][i].id)
                        .on('click', this.delete).text('x'))
                    .append($('<div/>').attr('class', 'addButton').attr('depId', object['department_array'][i].id)
                        .on('click', this.update).text('Edit'))
                    .append($('<div/>').attr('class', 'listButton').attr('depId', object['department_array'][i].id)
                        .on('click', this.list).text('Employee list'))
                ));
        }
    }
    list(){
        let depId = $(this).attr('depId');
        let employeeList = new EmployeeList(depId);
        location.hash = `/employeeList/${depId}`;
        employeeList.rendering();
    }
    add(){
        const router = new Router();
        let departmentForm = new DepartmentForm();
        location.hash = '/addEmployee';
        router.route(departmentForm);
    }
    update(){
        const service = new Services();
        const router = new Router();
        let depId = $(this).attr('depId');
        let departmentForm = new DepartmentForm();
        location.hash = '/updateEmployee';
        service.getInformation(`/departments/editDepartment/${depId}`,departmentForm,router);
    }
    delete(){
        const service = new Services();
        let depId = $(this).attr('depId');
        service.deleteInformation(`/departments/deleteDepartment/${depId}`, new DepartmentList());
    }
}