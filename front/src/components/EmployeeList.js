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
<div class="add" style="margin-left: 50%;" onclick="onClickFunction(this)">+</div>`);
        for (let i = 0; i < object["employees"].length; i++) {
            $('#root').append(`
             <div class="blocks">
        <table class="text" style="width:85%">
            <tr>
                <td><p class="name">${object["employees"][i].fullName}</p></td>
                <td><p class="address"> email: ${object["employees"][i].email}</p></td>
                <td><p class="address">birthday: ${object["employees"][i].birthday}</p></td>
                <td><p class="address">salary: ${object["employees"][i].phoneNumber}</p></td>
                <td><p class="address"> number: ${object["employees"][i].salary}</p></td>
                
            </tr>
        </table>
         <div class="buttons">
                 <div class="deleteButton" depId = "${object["department_array"][i].id}">X</div>
                 <div class="addButton" depId = "${object["department_array"][i].id}" onclick="onClickFunction(this)">Edit</div>
                 </div>
        </div>
            `);
        }
    }

}

window.onEmployeeClickFunction = function (object) {
    switch ($(object).attr('class')) {
        case "add": {
            history.pushState({}, '', '/departmentForm');
            routing();
            break;
        }
        case "addButton": {
            let depId = $(object).attr('depId');
            history.pushState({}, '', `/departmentForm/${depId}`);
            routing();
            break;
        }
        case "listButton": {
            let depId = $(object).attr('depId');
            history.pushState({}, '', `/employeeList/${depId}`);
            routing();
            break;
        }

        case "deleteButton": {

            break;
        }
    }
};