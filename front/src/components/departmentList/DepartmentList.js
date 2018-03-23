import {Components} from 'components/Component';
import {Services} from 'src/services/Services';


export class DepartmentList extends Components {

    constructor() {
        super('departmentList');
        this.service = new Services();
    }
    render(object) {
        let root = '#root';
        $(root).empty().append($('<h3/>').text('DEPARTMENTS'));
        $(root).append($('<a/>').attr('class', 'home').attr('href', '/').append($('<span>').html('&#x2302;')));
        $(root).append($('<div/>').attr('class', 'add').text('+').on('click',this.add.bind(this)));
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
                        .on('click', () => { this.delete(object['department_array'][i].id); }).text('x'))
                    .append($('<div/>').attr('class', 'addButton').attr('depId', object['department_array'][i].id)
                        .on('click', () => { this.update(object['department_array'][i].id); }).text('Edit'))
                    .append($('<div/>').attr('class', 'listButton').attr('depId', object['department_array'][i].id)
                        .on('click', () => { this.list(object['department_array'][i].id); }).text('Employee list'))
                ));
        }
    }
    list(depId){
        location.hash = `/employeeList/${depId}`;
        this.service.controller();
    }
    add(){
        location.hash = '/addDepartment';
        this.service.controller();
    }
    update(depId){
        location.hash = `/updateDepartment/${depId}`;
        this.service.controller();
    }
    delete(depId){
        this.service.deleteInformation(`/back/departments/deleteDepartment/${depId}`, '/departmentList');
    }
}