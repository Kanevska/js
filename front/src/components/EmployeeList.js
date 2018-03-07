import {Components} from "./Component";
import {routing} from "../router/Router";

export class EmployeeList extends Components {

    constructor() {
        super();
    }

    render(object) {
        super.render();
        $('#root').empty().append(`<h3>EMPLOYEE LIST OF ${object.departmentName} DEPARTMENT</h3>
<a class="home" href="/"><span>&#x2302;</span></a>
<div class="add" style="margin-left: 50%;" onclick="onEmployeeClickFunction(this)">+</div>`);
        for (let i = 0; i < object["employees"].length; i++) {
            $('#root').append(`
             <div class="blocks">
        <table class="text" style="width:70%">
            <tr>
                <td><p class="name">${object["employees"][i].fullName}</p></td>
                <td><p class="address"> email: ${object["employees"][i].email}</p></td>
                <td><p class="address">birthday: ${object["employees"][i].birthday}</p></td>
                <td><p class="address">salary: ${object["employees"][i].phoneNumber}</p></td>
                <td><p class="address"> number: ${object["employees"][i].salary}</p></td>
                
            </tr>
        </table>
         <div class="buttons">
                 <div class="deleteButton" empId = "${object["employees"][i].id}">X</div>
                 <div class="addButton" empId = "${object["employees"][i].id}" onclick="onEmployeeClickFunction(this)">Edit</div>
                 </div>
        </div>
            `);
        }
    }

}

window.onEmployeeClickFunction = function (object) {
    switch ($(object).attr('class')) {
        case "add": {
            history.pushState({}, '', '/employeeList/employeeForm');
            routing();
            break;
        }
        case "addButton": {
            const empId = $(object).attr('empId');
            history.pushState({}, '', `/employeeList/employeeForm/${empId}`);
            routing();
            break;
        }
        case "deleteButton": {

            break;
        }
    }
};