import {Components} from "../Component";
import {routing} from "../../router/Router";


export class DepartmentList extends Components {

    constructor() {
        super();
    }

    render(object) {
        super.render();
        $('#root').empty();
        $('#root').append(`<h3> DEPARTMENTS </h3>
<a class="home" href="/"><span>&#x2302;</span></a>
<div class="add" style="margin-left: 50%;" onclick="onClickFunction(this)">+</div>`);

        for (let i = 0; i < object["department_array"].length; i++) {
            $('#root').append(`<div class="blocks"> 
                <table class="text">
                <tr>
                <td> <p class="name">${object["department_array"][i].departmentName}</p></td>
                <td> <p class="address"> Address: ${ object["department_array"][i].description}</p></td>
                <td> <p class="address"> Description: ${object["department_array"][i].address}</p></td>
                </tr> 
                </table>
                  
                 <div class="buttons">
                 <div class="deleteButton" depId = "${object["department_array"][i].id}">X</div>
                 <div class="addButton" depId = "${object["department_array"][i].id}" onclick="onClickFunction(this)">Edit</div>
                 <div class="listButton" depId = "${object["department_array"][i].id}" onclick="onClickFunction(this)">Employee list</div>
                 </div>
                 </div>`);

        }
    }
}

window.onClickFunction = function (object) {
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
}
;

