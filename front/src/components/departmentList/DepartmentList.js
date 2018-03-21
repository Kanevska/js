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

        super.render();
        let root = '#root';
        $(root).empty().append($('<h3/>').text('DEPARTMENTS'));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'add').text('+').on('click', this.onClickFunction));
        for (let i = 0; i < object['department_array'].length; i++) {
            $(root).append($('<div/>').attr('class', 'blocks')
                .append($('<table/>').attr('class', 'text')
                    .append($('<tr/>')
                        .append($('<td/>').append($('<p/>').attr('class','name').text(`${object['department_array'][i].departmentName}`)))
                        .append($('<td/>').append($('<p/>').attr('class','address').text(`Address: ${ object['department_array'][i].description}`)))
                        .append($('<td/>').append($('<p/>').attr('class','address').text(`Description: ${object['department_array'][i].address}`)))
                    )).append($('<div/>').attr('class','buttons')
                    .append($('<div/>').attr('class','deleteButton').attr('depId',`${object['department_array'][i].id}`).on('click', this.onClickFunction).text('x'))
                    .append($('<div/>').attr('class','addButton').attr('depId',`${object['department_array'][i].id}`).on('click', this.onClickFunction).text('Edit'))
                    .append($('<div/>').attr('class','listButton').attr('depId',`${object['department_array'][i].id}`).on('click', this.onClickFunction).text('Employee list'))
                ));
        }

    }

    onClickFunction() {
        const router = new Router();
        const service = new Services();

        switch ($(this).attr('class')) {
        case 'add': {
            let departmentForm = new DepartmentForm();
            router.route(departmentForm);
            break;
        }
        case 'addButton': {
            let depId = $(this).attr('depId');
            location.href = `#departmentForm/${depId}`;
            let departmentForm = new DepartmentForm();
            service.getInformation(`/departments/editDepartment/${depId}`,departmentForm,router);
            break;
        }
        case 'listButton': {
            let depId = $(this).attr('depId');
            let employeeList = new EmployeeList(depId);
            employeeList.rendering();
            break;
        }

        case 'deleteButton': {
            let depId = $(this).attr('depId');
            service.deleteInformation(`/departments/deleteDepartment/${depId}`, new DepartmentList());
            break;
        }
        }
    }
}