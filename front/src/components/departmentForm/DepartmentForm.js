import {Components} from 'components/Component';
import {Services} from 'src/services/Services';
import {DepartmentList} from 'components/departmentList/DepartmentList';
import {Validator} from 'src/validation/Validator';


export class DepartmentForm extends Components {

    constructor() {
        super('departmentForm');
    }

    render(object) {
        super.render();
        let root = '#root';
        let form = '#departmentForm';
        $(root).empty().append($('<h3/>').text('DEPARTMENTS'));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'departmentBock')
            .append($('<form/>').attr('name', 'departmentForm').attr('id', 'departmentForm')));
        if (object != null) {
            $(form).append($('<input/>').attr('type', 'hidden').attr('name', 'id').val(`${object.id}`));
        }
        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'departmentName')
            .val(`${(object != null) ? object.departmentName : ''}`).attr('placeholder', 'Enter department name'));
        $(form).append($('<p/>').attr('id', 'depName'));
        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'description')
            .val(`${(object != null) ? object.description : ''}`).attr('placeholder', 'Enter department description'));

        $(form).append($('<input/>').attr('class', 'fields').attr('type', 'text').attr('name', 'address')
            .val(`${(object != null) ? object.address : ''}`).attr('placeholder', 'Enter department address'));
        if (object == null) {
            $(form).append($('<input/>').attr('class', 'addButton').attr('type', 'button').val('Add department').on('click', this.save));
        } else {
            $(form).append($('<input/>').attr('depId', `${object.id}`).attr('class', 'listButton').attr('type', 'button').val('Update department')
                .on('click', this.save));
        }

    }

    save() {
        const service = new Services();
        const validator = new Validator();
        if (validator.departmentValidate()) {
            service.sendInformation('#depName', 'departmentForm', '/departments/addDepartment', new DepartmentList());
        }


    }


}




