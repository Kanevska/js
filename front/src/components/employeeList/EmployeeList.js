import {Components} from 'components/Component';
import {Services} from 'src/services/Services';


export class EmployeeList extends Components {


    constructor(depId) {
        super('employeeList');
        this.depId = depId;
        this.service = new Services();
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
        location.hash = '/addEmployee';
        this.service.controller();
    }
    update(){
        const empId = $(this).attr('empId');
        location.hash = `/updateEmployee/${empId}`;
        this.service.controller();
    }
    delete(){
        const empId = $(this).attr('empId');
        const depId = $(this).attr('depId');
        this.service.deleteInformation(`/back/employee/deleteEmployee/${empId}`,`/employeeList/${depId}`);
    }
}
