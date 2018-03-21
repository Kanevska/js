import {Components} from 'components/Component';
import {Services} from 'src/services/Services';
import {EmployeeList} from 'components/employeeList/EmployeeList';
import {Validator} from 'src/validation/Validator';

export class EmployeeForm extends Components {

    constructor(depId) {
        super('employeeForm');
        this.depId = depId;
    }

    render(object) {
        const root = '#root';
        const form = '#employeeForm';
        $(root).empty().append($('<h3/>').text('ADD/UPDATE EMPLOYEE'));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'blocksEmployee')
            .append($('<form/>').attr('name', 'employeeForm').attr('id', 'employeeForm')));


        $(form).append($('<input/>').attr('type', 'hidden').attr('name', 'id').val(object.id={}));


        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'fullName')
            .val(object.fullName={}).attr('placeholder', 'Enter your name'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'email')
            .val(object.email={}).attr('placeholder', 'Enter your email'));
        $(form).append($('<p/>').attr('id', 'empMail'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'date').attr('name', 'birthday')
            .val(object.birthday={}));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'phoneNumber')
            .val(object.phoneNumber={}).attr('placeholder', 'Enter your phoneNumber'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'salary')
            .val(object.salary).attr('placeholder', 'Enter your salary'));

        $(form).append($('<select/>').attr('class', 'fields').attr('id', 'department').attr('name', 'departmentId')
            .attr('placeholder', 'Enter your salary'));

        try {
            for (let i = 0; i < object['department_array'].length; i++) {
                $('#department').append($('<option/>').attr('value', object['department_array'][i].id)
                    .text(object['department_array'][i].departmentName));
            }
        }catch (err){
            $('#department').append('');
        }


        if (!object.id) {
            $(form).append($('<input/>').attr('class', 'addButton').attr('type', 'button').val('Add department').on('click', this.save));
        } else {
            $(form).append($('<input/>').attr('depId', `${object.id}`).attr('class', 'listButton').attr('type', 'button').val('Update department')
                .on('click', this.save));
        }
    }

    save() {
        const service = new Services();
        const depId = $('#department').val();
        const validator = new Validator();
        if (validator.employeeValidate()) {
            service.sendInformation('#empMail', 'employeeForm', '/employee/addEmployee', new EmployeeList(depId));
        }
    }

}
