import {Components} from '../Component';
import {sendInformation, Services} from '../../services/Services';
import {EmployeeList} from "../employeeList/EmployeeList";

export class EmployeeForm extends Components {

    constructor() {
        super();
    }

    render(object) {

        let root = '#root';
        let form = '#employeeForm';
        $(root).empty().append($('<h3/>').text('ADD/UPDATE EMPLOYEE'));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'blocksEmployee')
            .append($('<form/>').attr('id', 'employeeForm')));
    
        if (object.id === undefined) {
            $(form).append($('<input/>').attr('type', 'hidden').attr('name', 'id').val(`${object.id}`));
        }
        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'fullName')
            .val(`${(object.id != null) ? object.fullName : ''}`).attr('placeholder', 'Enter your name'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'email')
            .val(`${(object.id != null) ? object.email : ''}`).attr('placeholder', 'Enter your email'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'date').attr('name', 'birthday')
            .val(`${(object.id != null) ? object.birthday : ''}`));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'phoneNumber')
            .val(`${(object.id != null) ? object.phoneNumber : ''}`).attr('placeholder', 'Enter your phoneNumber'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'salary')
            .val(`${(object.id != null) ? object.salary : ''}`).attr('placeholder', 'Enter your salary'));

        $(form).append($('<select/>').attr('class', 'fields').attr('id', 'department').attr('name', 'departmentId')
            .val(`${(object.id != null) ? object.salary : ''}`).attr('placeholder', 'Enter your salary'));

        for (let i = 0; i < object['department_array'].length; i++) {
            $('#department').append($('<option/>').attr('value', object['department_array'][i].id)
                .text(object['department_array'][i].departmentName));
        }


        if (object.id == null) {
            $(form).append($('<input/>').attr('class', 'addButton').attr('type', 'button').val('Add department').on('click', this.onClickFunction));
        } else {
            $(form).append($('<input/>').attr('depId', `${object.id}`).attr('class', 'listButton').attr('type', 'button').val('Update department')
                .on('click', this.onClickFunction));
        }
    }

    onClickFunction() {
        const service = new Services();
        const depId = $('#department').val();
        //const validator;
        //make validation
        service.sendInformation('', 'employeeForm', '/employee/addEmployee', new EmployeeList(depId));
    }

}
