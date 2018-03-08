import {Components} from "../Component";
import {sendInformation} from "../../services/Services";

export class DepartmentForm extends Components {

    constructor() {
        super();
    }

    render(object) {
        super.render();
        $('#root').empty();
        $('#root').append(`<h3>ADD/UPDATE DEPARTMENT</h3>
            <a class="home" href="/"><span>&#x2302;</span></a>
            <div class="departmentBock">
            <form id="departmentForm">
            ${(object!=undefined)?`<input type="hidden" name="id" value="${object.id}">`:''}
            <input class="fields" type="text" name="departmentName" value="${(object!=undefined)?object.departmentName:''}" placeholder="Enter department name">
            <input class="fields" type="text" name="description" value="${(object!=undefined)?object.description:''}" placeholder="Enter department description">
            <input class="fields" type="text" name="address" value="${(object!=undefined)?object.address:''}" placeholder="Enter department address">
           
           ${(object==undefined)?' <input class="addButton"value="Add department" onclick="onClickDepartment(this)" style="margin-left: 40%; text-align: center;">'
            :` <input depId="${object.id}" class="listButton"value="Update department" onclick="onClickDepartment(this)" style="margin-left: 40%; text-align: center;text-align: center; ">`}
            </form>
            </div>`);
    }
}
window.onClickDepartment = function () {
            sendInformation('','departmentForm','/departments/addDepartment','/');
};





