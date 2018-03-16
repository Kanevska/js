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



        if (object === undefined) {
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
